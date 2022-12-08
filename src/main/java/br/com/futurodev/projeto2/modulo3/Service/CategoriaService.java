package br.com.futurodev.projeto2.modulo3.Service;

import br.com.futurodev.projeto2.modulo3.model.Categoria;
import br.com.futurodev.projeto2.modulo3.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria getCategoriaById(Long idCategoria){
        return categoriaRepository.findById(idCategoria).get();
    }

    public List<Categoria> getCategorias() {
        return (List<Categoria>) categoriaRepository.findAll();
    }

    @Transactional
    public Categoria salvarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public void deleteById(Long idCategoria) {
        categoriaRepository.deleteById(idCategoria);
    }

}
