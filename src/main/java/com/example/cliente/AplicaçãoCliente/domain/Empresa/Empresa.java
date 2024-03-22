package com.example.cliente.AplicaçãoCliente.domain.Empresa;

import org.hibernate.validator.constraints.Length;

import com.example.cliente.AplicaçãoCliente.domain.Empresa.DTO.RequestEmpresaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "empresa")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Empresa {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @NotBlank
    @NotNull
    @Length(min = 3, max = 100)
    @Column(length = 100, nullable = false)
    private String nome_fantasia;

    @NotBlank
    @NotNull
    @Length(min = 3, max = 100)
    @Column(length = 100, nullable = false)
    private String cnpj;

    @NotBlank
    @NotNull
    @Length(min = 3, max = 100)
    @Column(length = 100, nullable = false)
    private String razao_social;

    @NotBlank
    @NotNull
    @Length(min = 3, max = 100)
    @Column(length = 100, nullable = false)
    private String email;

    @NotBlank
    @Length(min = 10, max = 100)
    @Column(length = 100, nullable = false)
    private String telefone;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String endereco;


    public Empresa(RequestEmpresaDTO requesteEmpresaDTO){
        this.cnpj = requesteEmpresaDTO.cnpj();
        this.telefone = requesteEmpresaDTO.telefone();
        this.endereco = requesteEmpresaDTO.endereco();
        this.nome_fantasia = requesteEmpresaDTO.nome_fantasia();
        this.email = requesteEmpresaDTO.email();
        this.razao_social = requesteEmpresaDTO.razao_social();
    }

}
