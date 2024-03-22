package com.example.cliente.AplicaçãoCliente.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cliente.AplicaçãoCliente.domain.Cliente.Juridico.Juridico;
import com.example.cliente.AplicaçãoCliente.domain.Cliente.Juridico.JuridicoRepository;
import com.example.cliente.AplicaçãoCliente.domain.Cliente.Juridico.DTO.RequestJuridicoDTO;
import com.example.cliente.AplicaçãoCliente.domain.Contato.Contato;
import com.example.cliente.AplicaçãoCliente.domain.Contato.ContatoRepository;
import com.example.cliente.AplicaçãoCliente.domain.Contato.DTO.RequestContatoDTO;
import com.example.cliente.AplicaçãoCliente.exception.RecordNotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/V1/juridico")
public class JuridicoController {
    @Autowired
    private JuridicoRepository repository;
    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<Juridico>> getJuridico() {
        var Juridico = repository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(Juridico);

    }

    @GetMapping("/getunique/{id}")
    public ResponseEntity<Juridico> getUniqueJuridico(@PathVariable String id) {
        Juridico Juridico = repository.findById(id).map(recordFound -> {
            return recordFound;
        }).orElseThrow(() -> new RecordNotFoundException(id));
        return ResponseEntity.status(HttpStatus.OK).body(Juridico);
    }
    @GetMapping("/getalljuridicocontatotrue")
    public ResponseEntity<List<Juridico>> getjuridicocontatotrue() {
        var Juridico = repository.findAllByContatoStatusTrue();

        return ResponseEntity.status(HttpStatus.OK).body(Juridico);
    }
    @PostMapping("/post")
    public ResponseEntity<Juridico> postFisico(@RequestBody RequestJuridicoDTO data) {
        Juridico newJuridico = new Juridico(data);
        repository.save(newJuridico);
        return ResponseEntity.status(HttpStatus.CREATED).body(newJuridico);
    }
    @PutMapping("/put/{id}")
    @Transactional
    public ResponseEntity<Juridico> putJuridico(@PathVariable String id, @RequestBody RequestJuridicoDTO data) {
        return ResponseEntity.status(HttpStatus.OK).body(
                repository.findById(id).map(recordFound -> {
                    recordFound.setNome(data.nome());
                    recordFound.setCnpj(data.cnpj());
                    recordFound.setEmail(data.email());
                    recordFound.setEndereco(data.endereco());
                    recordFound.setTelefone(data.telefone());
                    recordFound.setStatus(data.status());

                    return recordFound;
                }).orElseThrow(() -> new RecordNotFoundException(id)));

    }
    @DeleteMapping("/softdelete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Transactional
    public void softDeleteJuridico(@PathVariable String id) {

        repository.findById(id)
                .map(foundJuridico -> {
                    List<Contato> contatos = contatoRepository.findContatoByJuridico(foundJuridico)
                            .stream()
                            .map(contato -> {
                                contato.setStatus(false);
                                return contato;
                            })
                            .collect(Collectors.toList());
                    foundJuridico.setStatus(false);
                    return foundJuridico;
                });

    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteJuridico(@PathVariable @NotNull String id){

        Optional<Juridico> juridico = repository.findById(id);
        if(juridico.isPresent()){
            Juridico foundJuridico = juridico.get();
            List<Contato> contatos = contatoRepository.findContatoByJuridico(foundJuridico);
            if(contatos.size()>0){
                
                return ResponseEntity.badRequest().build();
            }else{
                repository.delete(foundJuridico);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
        


    }

}
