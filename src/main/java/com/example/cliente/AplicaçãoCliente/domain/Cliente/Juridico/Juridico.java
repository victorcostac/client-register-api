package com.example.cliente.AplicaçãoCliente.domain.Cliente.Juridico;

import java.util.ArrayList;
import java.util.List;

import com.example.cliente.AplicaçãoCliente.domain.Cliente.Cliente;
import com.example.cliente.AplicaçãoCliente.domain.Cliente.Juridico.DTO.RequestJuridicoDTO;
import com.example.cliente.AplicaçãoCliente.domain.Contato.Contato;
import com.example.cliente.AplicaçãoCliente.domain.Empresa.Empresa;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@Table(name = "juridico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Juridico extends Cliente {

    @NotBlank
    @NotNull
    private String cnpj;

    public Juridico(String id, String nome, String endereco, String email, Boolean status, String telefone, String cnpj, Empresa empresa) {
        super(id, nome, endereco, email, status, telefone, empresa);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    
    @OneToMany(mappedBy = "juridico", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private List<Contato> contatos = new ArrayList<>();

    public Juridico(RequestJuridicoDTO requestJuridicoDTO){
        this.nome = requestJuridicoDTO.nome();
        this.status = true;
        this.cnpj = requestJuridicoDTO.cnpj();
        this.telefone = requestJuridicoDTO.telefone();
        this.endereco = requestJuridicoDTO.endereco();
        this.email = requestJuridicoDTO.email();
        this.empresa = requestJuridicoDTO.empresa(); 
        
    }

}
