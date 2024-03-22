package com.example.cliente.AplicaçãoCliente.domain.Cliente.Juridico.DTO;

import com.example.cliente.AplicaçãoCliente.domain.Empresa.Empresa;

public record RequestJuridicoDTO(
    String id,
    String nome,
    String endereco,
    String email,
    Boolean status,
    String telefone,
    Empresa empresa,
    String cnpj
) {
    
}
