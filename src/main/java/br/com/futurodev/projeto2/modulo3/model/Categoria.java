package br.com.futurodev.projeto2.modulo3.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String nomeCategoria;

    private String descricaoCategoria;


}
