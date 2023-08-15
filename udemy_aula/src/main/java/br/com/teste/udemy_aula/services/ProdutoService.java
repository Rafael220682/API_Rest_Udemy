package br.com.teste.udemy_aula.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.udemy_aula.model.Produto;
import br.com.teste.udemy_aula.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> obterTodos(){
        return produtoRepository.obterTodos();
    }

    public Optional<Produto> obterPorId(Long id){
        return produtoRepository.obterPorId(id);
    }

    public Produto adicionar(Produto produto){
         return produtoRepository.adicionar(produto);

    }

    public void deletar (Long id){
        produtoRepository.deletar(id);
    }

    public Produto atualizar(Long id, Produto produto){

        produto.setId(id);

        return produtoRepository.atualizar(produto);
        
    }
}
