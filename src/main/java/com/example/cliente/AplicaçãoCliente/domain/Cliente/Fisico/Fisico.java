package com.example.cliente.AplicaçãoCliente.domain.Cliente.Fisico;

import com.example.cliente.AplicaçãoCliente.domain.Cliente.Cliente;
import com.example.cliente.AplicaçãoCliente.domain.Cliente.Fisico.DTO.RequestFisicoDTO;
import com.example.cliente.AplicaçãoCliente.domain.Empresa.Empresa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "fisico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Fisico extends Cliente{
    
    @NotBlank
    @NotNull
    private String cpf;

    public Fisico(String id, String nome, String endereco, String email, Boolean status, String telefone, String cpf, Empresa empresa){
        super(id, nome, endereco, email, status, telefone, empresa);
        this.cpf = cpf;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public Fisico(RequestFisicoDTO requestFisicoDTO){
        this.nome = requestFisicoDTO.nome();
        this.status = true;
        this.email = requestFisicoDTO.email();
        this.cpf = requestFisicoDTO.cpf();
        this.endereco = requestFisicoDTO.endereco();
        this.telefone = requestFisicoDTO.telefone(); 
        this.empresa = requestFisicoDTO.empresa();

    }
}
