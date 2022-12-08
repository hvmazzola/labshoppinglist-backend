package br.com.futurodev.projeto2.modulo3.controllers;

import br.com.futurodev.projeto2.modulo3.Service.CategoriaService;
import br.com.futurodev.projeto2.modulo3.Service.ProdutoService;
import br.com.futurodev.projeto2.modulo3.dto.input.CategoriaInput;
import br.com.futurodev.projeto2.modulo3.dto.input.ProdutoInput;
import br.com.futurodev.projeto2.modulo3.dto.output.CategoriaOutput;
import br.com.futurodev.projeto2.modulo3.dto.output.ProdutoOutput;
import br.com.futurodev.projeto2.modulo3.model.Categoria;
import br.com.futurodev.projeto2.modulo3.model.Produto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Produtos")
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @ApiOperation("Salvar um produto")
    @PostMapping
    public ResponseEntity<ProdutoOutput> cadastrar(@RequestBody ProdutoInput produtoInput) {
        Produto produto = toDomainObject(produtoInput);
        produtoService.salvarProduto(produto);
        return new ResponseEntity<ProdutoOutput>(toModel(produto), HttpStatus.CREATED);
    }

    @ApiOperation("Atualiza um produto")
    @PutMapping
    public ResponseEntity<ProdutoOutput> atualizar(@RequestBody ProdutoInput produtoInput) {
        Produto produto = produtoService.salvarProduto(toDomainObject(produtoInput));
        return new ResponseEntity<ProdutoOutput>(toModel(produto), HttpStatus.OK);
    }

    @ApiOperation("Deleta um produto")
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<String> delete(@ApiParam(value = "ID do produto", example = "1") @RequestParam Long idProduto) {
        produtoService.deleteById(idProduto);
        return new ResponseEntity<String>("Produto ID: " + idProduto + ": deletado com sucesso!", HttpStatus.OK);
    }

    @ApiOperation("Listar Produtos")
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProdutoOutput>> getProdutos() {

        List<Produto> produtos = produtoService.getProdutos();

        List<ProdutoOutput> produtoOutputs = toCollectionModel(produtos);

        return new ResponseEntity<List<ProdutoOutput>>(produtoOutputs, HttpStatus.OK);
    }

    @ApiOperation("Informar Valor Total de Produtos Comprados")
    @GetMapping(value = "/totalcomprado")
    @ResponseBody
    public ResponseEntity<String> getValorTotal() {
        double soma = 0;
        List<Produto> produtos = produtoService.getProdutos();

        for (long i = 1; i <= produtos.size(); i++) {

            if ((produtoService.getProdutoById(i).isStatusProduto()) == true){
                soma += (produtoService.getProdutoById(i).getValorProduto());
            }

        }
    return new ResponseEntity<String> ("Valor Total de Produtos Comprados: " + soma + " reais.", HttpStatus.OK);
    }




    private Produto toDomainObject(ProdutoInput produtoInput) {
        Produto produto = new Produto();
        produto.setId(produtoInput.getIdProduto());
        produto.setNomeProduto(produtoInput.getNomeProduto());
        produto.setValorProduto(produtoInput.getValorProduto());
        produto.setStatusProduto(produtoInput.isStatusProduto());
        produto.setCategoria(categoriaService.getCategoriaById(produtoInput.getIdCategoria()));
        return produto;
    }

    private ProdutoOutput toModel(Produto produto) {
        ProdutoOutput produtoOutput = new ProdutoOutput();
        produtoOutput.setIdProduto(produto.getId());
        produtoOutput.setNomeProduto(produto.getNomeProduto());
        produtoOutput.setValorProduto(produto.getValorProduto());
        produtoOutput.setStatusProduto(produto.isStatusProduto());
        produtoOutput.setIdCategoria(produto.getCategoria().getId());
        return produtoOutput;
    }

    private List<ProdutoOutput> toCollectionModel(List<Produto> produtos) {
        return produtos.stream()
                .map(Produto -> toModel(Produto))
                .collect(Collectors.toList());

    }



}
