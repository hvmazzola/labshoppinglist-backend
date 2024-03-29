package br.com.futurodev.projeto2.modulo3.controllers;


import br.com.futurodev.projeto2.modulo3.Service.CategoriaService;
import br.com.futurodev.projeto2.modulo3.Service.ProdutoService;
import br.com.futurodev.projeto2.modulo3.dto.input.CategoriaInput;
import br.com.futurodev.projeto2.modulo3.dto.output.CategoriaOutput;
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

@Api(tags = "Categorias")
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProdutoService produtoService;

    @ApiOperation("Salvar uma categoria")
    @PostMapping
    public ResponseEntity<CategoriaOutput> cadastrar(@RequestBody CategoriaInput categoriaInput) {
        Categoria categoria = toDomainObject(categoriaInput);
        categoriaService.salvarCategoria(categoria);
        return new ResponseEntity<CategoriaOutput>(toModel(categoria), HttpStatus.CREATED);
    }

    @ApiOperation("Atualiza uma categoria")
    @PutMapping
    public ResponseEntity<CategoriaOutput> atualizar(@RequestBody CategoriaInput categoriaInput) {
        Categoria categoria = categoriaService.salvarCategoria(toDomainObject(categoriaInput));
        return new ResponseEntity<CategoriaOutput>(toModel(categoria), HttpStatus.OK);
    }


    @ApiOperation("Deletar uma categoria caso ela não tenha nenhum produto associado")
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<String> delete(@ApiParam(value = "ID da categoria", example = "1") @RequestParam Long idCategoria) {

        int contador = 0;
        List<Produto> produtos = produtoService.getProdutos();

        for (long i = 1; i <= produtos.size(); i++) {

            if ((produtoService.getProdutoById(i).getCategoria().getId()) == idCategoria){
                contador += 1;
            }

        }

        if (contador == 0) {
            categoriaService.deleteById(idCategoria);
            return new ResponseEntity<String>("Categoria ID: " + idCategoria + " deletado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Categoria ID: " + idCategoria + " não deletada. A categoria possui produtos cadastrados.", HttpStatus.OK);
        }

    }


    @ApiOperation("Listar Categorias")
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<CategoriaOutput>> getCategorias() {

        List<Categoria> categorias = categoriaService.getCategorias();

        List<CategoriaOutput> categoriaOutputs = toCollectionModel(categorias);

        return new ResponseEntity<List<CategoriaOutput>>(categoriaOutputs, HttpStatus.OK);
    }


    private Categoria toDomainObject(CategoriaInput categoriaInput) {
        Categoria categoria = new Categoria();
        categoria.setId(categoriaInput.getIdCategoria());
        categoria.setDescricaoCategoria(categoriaInput.getDescricaoCategoria());
        categoria.setNomeCategoria(categoriaInput.getNomeCategoria());
        return categoria;
    }

    private CategoriaOutput toModel(Categoria categoria) {
        CategoriaOutput categoriaOutput = new CategoriaOutput();
        categoriaOutput.setIdCategoria(categoria.getId());
        categoriaOutput.setDescricaoCategoria(categoria.getDescricaoCategoria());
        categoriaOutput.setNomeCategoria(categoria.getNomeCategoria());
        return categoriaOutput;
    }

    private List<CategoriaOutput> toCollectionModel(List<Categoria> categorias) {
        return categorias.stream()
                .map(Categoria -> toModel(Categoria))
                .collect(Collectors.toList());

    }
}
