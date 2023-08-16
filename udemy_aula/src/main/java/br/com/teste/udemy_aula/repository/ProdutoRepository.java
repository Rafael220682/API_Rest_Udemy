package br.com.teste.udemy_aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teste.udemy_aula.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
