package com.example.cliente.AplicaçãoCliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cliente.AplicaçãoCliente.domain.Contato.Contato;
import com.example.cliente.AplicaçãoCliente.domain.Contato.ContatoRepository;
import com.example.cliente.AplicaçãoCliente.domain.Contato.DTO.RequestContatoDTO;
import com.example.cliente.AplicaçãoCliente.exception.RecordNotFoundException;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/V1/contato")
public class ContatoController {
    @Autowired
    private ContatoRepository repository;

    @GetMapping("/getAll")
    public ResponseEntity<List<Contato>> getContato(){
        var contato = repository.findAll();

        return ResponseEntity.ok().body(contato);
    }

    @PostMapping("/post")
    public ResponseEntity<Contato> postContato(@RequestBody RequestContatoDTO data){
        Contato newContato = new Contato(data);
        repository.save(newContato);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(newContato);
    }

    @PutMapping("/put/{id}")
    @Transactional
    public ResponseEntity<Contato> putContato(@PathVariable String id, @RequestBody RequestContatoDTO data){
        return ResponseEntity.status(HttpStatus.OK).body(
            repository.findById(id).map(recordFound -> {
                recordFound.setDescricao(data.descricao());
                recordFound.setNumero(data.numero());
                recordFound.setStatus(data.status());
                return recordFound;
            }).orElseThrow(() -> new RecordNotFoundException(id)));
            
            
        
    }
}
