package br.com.teste.udemy_aula.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.udemy_aula.model.Produto;
import br.com.teste.udemy_aula.model.exception.ResourceNotFoundException;
import br.com.teste.udemy_aula.repository.ProdutoRepository;
import br.com.teste.udemy_aula.shared.ProdutoDto;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDto> obterTodos(){

        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
            .map(produto -> new ModelMapper().map(produto, ProdutoDto.class))
            .collect(Collectors.toList());
    }

    public Optional<ProdutoDto> obterPorId(Long id){

        // Obtendo Optional de produto pelo id
        Optional<Produto> produto = produtoRepository.findById(id);
        // Se não encontrar lança exception
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Produto com id: " + id + " não encontrado");
        }
        // Convertendo Optional de Produto em um produtoDto
        ProdutoDto dto = new ModelMapper().map(produto.get(), ProdutoDto.class);
        // Criando e retornando um optional de produto Dto
        return Optional.of(dto);

    }

    public ProdutoDto adicionar(ProdutoDto produtoDto){
        //Removendo o Id para conseguir fazer o cadastro
        produtoDto.setId(null);
               // Criar um objeto de acoplamento
        ModelMapper mapper = new ModelMapper();
        // Converter o nosso produtoDto em um produto
        Produto produto = mapper.map(produtoDto, Produto.class);
     
        // Salvar produto no banco
        produto = produtoRepository.save(produto);
        produtoDto.setId(produto.getId());
        // Retornar produtoDto atualizado 
        return produtoDto;
        

    }

    public void deletar (Long id){

        // verificar se o produto existe
        Optional<Produto> produto = produtoRepository.findById(id);

        // se não existir lança uma exception
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível deletar o produto com o id: " 
                                                                + id + " - pois produto não existe!");
        }

        // deleta produto do repositório (banco de dados)
        produtoRepository.deleteById(id);
       
    }

    public ProdutoDto atualizar(Long id, ProdutoDto produtoDto){

        // verificar se o produto existe
        Optional<Produto> produtos = produtoRepository.findById(id);

        // se não existir lança uma exception
        if(produtos.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível alterar o produto com o id: " 
                                                                + id + " - Produto não existe!");
        }
        
       // Passar o id para o produtoDto
        produtoDto.setId(id);
       // Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();
       // Converter o Dto em um produto
        Produto produto = mapper.map(produtoDto, Produto.class);
       // atualizar o produto no banco de dados
        produtoRepository.save(produto);
       // Retornar o produtoDto atualizado
        return produtoDto;
    }
}
