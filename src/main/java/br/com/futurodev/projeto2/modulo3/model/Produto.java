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
@Table(name = "produto")
public class Produto implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nomeProduto;

    @NotNull
    private double valorProduto;

    private boolean statusProduto;

    @ManyToOne
    @JoinColumn(name = "id_categoria", foreignKey = @ForeignKey(name = "fk_categoria"))
    private Categoria categoria;
}
