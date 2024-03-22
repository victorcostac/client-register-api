package com.example.cliente.AplicaçãoCliente.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cliente.AplicaçãoCliente.domain.Empresa.Empresa;
import com.example.cliente.AplicaçãoCliente.domain.Empresa.EmpresaRepository;
import com.example.cliente.AplicaçãoCliente.domain.Empresa.DTO.RequestEmpresaDTO;

@RestController
@RequestMapping("/api/V1/empresa")
public class EmpresaController {


    @Autowired 
    private EmpresaRepository repository;


    @GetMapping("/getAll")
    public ResponseEntity<List<Empresa>> getEmpresa(){
        var empresa = repository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(empresa);
    }

    @PostMapping("/post")
    public ResponseEntity<Empresa> postEmpresa(@RequestBody RequestEmpresaDTO data){

        Empresa newEmpresa = new Empresa(data);

        repository.save(newEmpresa);

        return ResponseEntity.status(HttpStatus.CREATED).body(newEmpresa);

    }
}
