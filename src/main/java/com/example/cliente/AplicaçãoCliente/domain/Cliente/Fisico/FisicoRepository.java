package com.example.cliente.AplicaçãoCliente.domain.Cliente.Fisico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FisicoRepository extends JpaRepository<Fisico, String> {
    
}
