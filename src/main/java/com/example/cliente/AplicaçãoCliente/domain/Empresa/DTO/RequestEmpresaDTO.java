package com.example.cliente.AplicaçãoCliente.domain.Empresa.DTO;

public record RequestEmpresaDTO(
    String id, 
    String nome_fantasia,
    String cnpj,
    String razao_social,
    String email,
    String telefone,
    String endereco ) {
    
}
