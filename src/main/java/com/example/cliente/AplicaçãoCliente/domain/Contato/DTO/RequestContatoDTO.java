package com.example.cliente.AplicaçãoCliente.domain.Contato.DTO;

import com.example.cliente.AplicaçãoCliente.domain.Cliente.Juridico.Juridico;

public record RequestContatoDTO(
    String id,
    String descricao,
    String numero,
    Boolean status,
    Juridico juridico
) {
    
}
