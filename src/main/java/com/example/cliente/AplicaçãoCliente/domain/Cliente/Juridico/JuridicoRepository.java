package com.example.cliente.AplicaçãoCliente.domain.Cliente.Juridico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JuridicoRepository extends JpaRepository<Juridico, String> {
    @Query(value = "SELECT DISTINCT j.* FROM juridico j INNER JOIN contato c ON j.id = c.id_juridico WHERE c.status = true", nativeQuery = true)
    List<Juridico> findAllByContatoStatusTrue();

}
