package br.com.teste.udemy_aula.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.udemy_aula.services.ProdutoService;
import br.com.teste.udemy_aula.shared.ProdutoDto;
import br.com.teste.udemy_aula.view.model.ProdutoRequest;
import br.com.teste.udemy_aula.view.model.ProdutoResponse;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterTodos(){
        List<ProdutoDto> produtos =  produtoService.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<ProdutoResponse> resposta = produtos.stream()
          .map(produtoDto -> mapper.map(produtoDto, ProdutoResponse.class))
          .collect(Collectors.toList());

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProdutoResponse>> obterPorId(@PathVariable Long id){
       
        
        Optional<ProdutoDto> dto =  produtoService.obterPorId(id);

        ProdutoResponse produto = new ModelMapper().map(dto.get(), ProdutoResponse.class);

        return new ResponseEntity<>(Optional.of(produto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionar (@RequestBody ProdutoRequest ProdutoReq){

        ModelMapper mapper = new ModelMapper();
        ProdutoDto produtoDto = mapper.map(ProdutoReq, ProdutoDto.class);

        produtoDto = produtoService.adicionar(produtoDto);
    
        return new ResponseEntity<>(mapper.map(produtoDto, ProdutoResponse.class), HttpStatus.CREATED);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar (@PathVariable Long id){
      
       produtoService.deletar(id);
        
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Long id,  
                                                        @RequestBody ProdutoRequest produtoReq){
        
        ModelMapper mapper = new ModelMapper();

        ProdutoDto produtoDto = mapper.map(produtoReq, ProdutoDto.class);
        
        produtoDto =  produtoService.atualizar(id, produtoDto);

        return new ResponseEntity<>(mapper.map(produtoDto, ProdutoResponse.class),
                    HttpStatus.OK);
       
        
    }
}
