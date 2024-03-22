package com.example.cliente.AplicaçãoCliente.domain.Cliente;

import org.hibernate.validator.constraints.Length;

import com.example.cliente.AplicaçãoCliente.domain.Empresa.Empresa;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "cliente")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor  
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// @DiscriminatorColumn(name = "tipo_cliente")
public abstract class Cliente {
    //Usar o protected para que as classes filhas também acessem os atributos diretamente
    @Id@GeneratedValue(strategy = GenerationType.UUID)
    protected String id;

    @NotBlank
    @Length(min = 3, max = 100)
    @Column(length = 100, nullable = false)
    protected String nome; 

    @NotBlank
    @Length(min = 3, max = 100)
    @Column(length = 100, nullable = false)
    protected String endereco;

    @NotNull
    @NotBlank
    @Length(min = 3, max = 100)
    @Column(length = 100, nullable = false)
    protected String email;

    @NotNull
    @Column(nullable = false)
    protected Boolean status;

    @NotBlank
    @Length(min = 3, max = 50)
    @Column(length = 50, nullable = false)
    protected String telefone;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    protected Empresa empresa;

}
