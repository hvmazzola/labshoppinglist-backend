package br.com.futurodev.projeto2.modulo3.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoOutput {

    private Long idProduto;

    private Long idCategoria;

    private String nomeProduto;

    private double valorProduto;

    private boolean statusProduto;
}
