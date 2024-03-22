package com.example.cliente.AplicaçãoCliente.domain.Contato;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cliente.AplicaçãoCliente.domain.Cliente.Juridico.Juridico;

public interface ContatoRepository extends JpaRepository<Contato, String>{

    List<Contato> findContatoByJuridico(Juridico juridico);
}
