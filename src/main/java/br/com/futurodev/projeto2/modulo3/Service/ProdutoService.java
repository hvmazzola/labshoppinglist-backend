package br.com.futurodev.projeto2.modulo3.Service;

import br.com.futurodev.projeto2.modulo3.model.Categoria;
import br.com.futurodev.projeto2.modulo3.model.Produto;
import br.com.futurodev.projeto2.modulo3.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto getProdutoById(Long idProduto){
        return produtoRepository.findById(idProduto).get();
    }

    public List<Produto> getProdutos() {
        return (List<Produto>) produtoRepository.findAll();
    }

    @Transactional
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Transactional
    public void deleteById(Long idProduto) {
        produtoRepository.deleteById(idProduto);
    }


}
