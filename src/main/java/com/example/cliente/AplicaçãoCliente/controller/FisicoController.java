package com.example.cliente.AplicaçãoCliente.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cliente.AplicaçãoCliente.domain.Cliente.Fisico.Fisico;
import com.example.cliente.AplicaçãoCliente.domain.Cliente.Fisico.FisicoRepository;
import com.example.cliente.AplicaçãoCliente.domain.Cliente.Fisico.DTO.RequestFisicoDTO;
import com.example.cliente.AplicaçãoCliente.exception.RecordNotFoundException;

@RestController
@RequestMapping("/api/V1/fisico")
public class FisicoController {
    @Autowired
    private FisicoRepository repository;

    @GetMapping("/getAll")
    public ResponseEntity<List<Fisico>> getFisico(){
        var fisico = repository.findAll();
        
        return ResponseEntity.status(HttpStatus.OK).body(fisico) ;
        
    }

    @PostMapping("/post")
    public ResponseEntity<Fisico> postFisico(@RequestBody RequestFisicoDTO data){

        
        Fisico newFisico = new Fisico(data);

        repository.save(newFisico);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(newFisico);
        
    }

    
    
    @DeleteMapping("/delete")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteFisico(@PathVariable String id){  
        repository.delete(repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
    // @DeleteMapping("/delete")
    // public ResponseEntity deleteFisico(@PathVariable String id){  
        
    //     Optional<Fisico> fisico = repository.findById(id);
    
    //     if(fisico.isPresent()){
    //         repository.delete(fisico.get());
    //         return ResponseEntity.noContent().build();
    //     }
    //     return ResponseEntity.notFound().build();
    // }
}
