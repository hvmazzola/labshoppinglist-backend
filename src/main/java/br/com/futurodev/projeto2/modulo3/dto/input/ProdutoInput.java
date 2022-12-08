package br.com.futurodev.projeto2.modulo3.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {

    private boolean statusProduto;

    private Long idProduto;

    private Long idCategoria;

    private String nomeProduto;

    private double valorProduto;


}
