package br.com.futurodev.projeto2.modulo3.repository;

import br.com.futurodev.projeto2.modulo3.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
