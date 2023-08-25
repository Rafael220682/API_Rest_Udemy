package br.com.teste.udemy_aula.view.model;

public class ProdutoResponse {
    
    
    private Long id;
    private String nome;
    private Integer quantidade;
    private Double valor;
    //Observação não  retornará na resposta ao usuário
    //private String observacao;
   
   
   
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    
}
