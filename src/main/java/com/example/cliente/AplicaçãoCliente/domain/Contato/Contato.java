package com.example.cliente.AplicaçãoCliente.domain.Contato;

import com.example.cliente.AplicaçãoCliente.domain.Cliente.Juridico.Juridico;
import com.example.cliente.AplicaçãoCliente.domain.Contato.DTO.RequestContatoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "contato")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Contato {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(length = 100)
    private String descricao;

    @Column(length = 50)
    private String numero;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_juridico", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Juridico juridico;

    public Contato(RequestContatoDTO RequestContatoDTO){
        this.descricao = RequestContatoDTO.descricao();
        this.numero = RequestContatoDTO.numero();
        this.status = true;
        this.juridico = RequestContatoDTO.juridico();
    }
}
