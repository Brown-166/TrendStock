package com.mycompany.trendstock;

import java.util.ArrayList;

public class Colecao {
    private String nome;
    private int ano;
    private String estacao;
    private ArrayList<Produto> produtos;
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome)  throws EntradaInvalidaException{
        if (nome == null || nome.isBlank())
            throw new EntradaInvalidaException("!!!O nome do produto não pode ser vazio!!!");
        else
            this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano)  throws EntradaInvalidaException{
        if (ano < 1900)
            throw new EntradaInvalidaException("!!!O ano da coleção não pode ser anterior a 1900!!!");
        else
            this.ano = ano;
    }

    public String getEstacao() {
        return estacao;
    }

    public void setEstacao(String estacao)  throws EntradaInvalidaException{
        if (estacao == null || estacao.isBlank())
            throw new EntradaInvalidaException("!!!A estação do produto não pode ser vazia!!!");
        else
            this.estacao = estacao;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        if (produtos != null){
            for (Produto p : produtos){
                adicionarProduto(p);
            }
        }
    }

    public Colecao() {
        this.produtos = new ArrayList<>();
    }
    
    public Colecao(String nome, int ano, String estacao)  throws EntradaInvalidaException{
        setNome(nome);
        setAno(ano);
        setEstacao(estacao);
        this.produtos = new ArrayList<>();
    }

    public Colecao(String nome, int ano, String estacao, ArrayList<Produto> produtos)  throws EntradaInvalidaException{
        setNome(nome);
        setAno(ano);
        setEstacao(estacao);
        setProdutos(produtos);
    }
    
    public void adicionarProduto(Produto produto){
        if (produto.validarProduto()){
            this.produtos.add(produto);
            System.out.println("O produto " + produto.getSKU() + " foi adicionado a coleção.");
        }                
    }
    
    public void listarProdutos(){
        for (Produto p : this.produtos){
            System.out.println(String.format("SKU: %s", p.getSKU()));
            System.out.println(String.format("Nome: %s", p.getNome()));
            System.out.println(String.format("Categoria: %s", p.getCategoria()));
            System.out.print("Tamanhos: "); 
            for (String tamanho : p.getTamanhos()){
                System.out.print(tamanho + " "); 
            }
            System.out.println(String.format("Preço: R$ %.2f", p.getPrecoVenda()));
        }
    }
    
    public boolean validarColecao(){
        if (this.nome != null && !this.nome.isBlank()){
            if (this.ano >= 1900){
                if (this.estacao != null && !this.estacao.isBlank()){
                    if (!this.produtos.isEmpty())
                        return true;
                    else
                        System.out.println("!!!A coleção " + this.nome + " não possui produtos cadastrados!!!");
                }
                else
                    System.out.println("!!!A coleção " + this.nome + " não possui uma estação válida!!!");           
            }
            else
                System.out.println("!!!A coleção " + this.nome + " não possui um ano válido!!!");
        }
        else
            System.out.println("!!!A coleção não possui um nome válido!!!");
        
        return false;
    }
    
}
