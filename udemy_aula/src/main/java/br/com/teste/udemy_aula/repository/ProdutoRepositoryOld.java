package br.com.teste.udemy_aula.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.teste.udemy_aula.model.Produto;
import br.com.teste.udemy_aula.model.exception.ResourceNotFoundException;


@Repository
public class ProdutoRepositoryOld {
    
    private List<Produto> produtos = new ArrayList<Produto>();
    private Long ultimoId = (long) 0;

    public List<Produto> obterTodos(){
        return produtos;
    }

    public Optional<Produto> obterPorId(Long id){
        return produtos
                .stream()
                .filter(produto -> produto.getId() == id)
                .findFirst();
    }

    public Produto adicionar(Produto produto){
        
        ultimoId++;

        produto.setId(ultimoId);
        produtos.add(produto);

        return produto;

    }

    public void deletar (Long id){
        produtos.removeIf(produto -> produto.getId() == id);
    }

    public Produto atualizar(Produto produto){

        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());

        if(produtoEncontrado.isEmpty()){
            throw new ResourceNotFoundException("Produto não pode ser atualizado pois não existe");
        }

        deletar(produto.getId());

        produtos.add(produto);

        return produto;
        
}

}
