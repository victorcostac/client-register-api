package com.example.cliente.AplicaçãoCliente.domain.Cliente.Fisico.DTO;

import com.example.cliente.AplicaçãoCliente.domain.Empresa.Empresa;

public record RequestFisicoDTO(
    String id,
    String nome,
    String endereco,
    String email,
    String telefone,
    String cpf,
    Empresa empresa
) {
    
}
