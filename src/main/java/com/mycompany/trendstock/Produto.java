package com.mycompany.trendstock;

public class Produto {
    private String sku;
    private String nome;
    private double precoCusto;
    private double precoVenda;
    private int quantidadeEstoque;
    private String[] tamanhos;
    private String categoria;

    
    public String getSKU() {
        return sku;
    }

    public void setSKU(String sku) throws EntradaInvalidaException{
        if (sku == null || sku.isBlank())
            throw new EntradaInvalidaException("!!!O SKU do produto não pode ser vazio!!!");
        else
            this.sku = sku;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws EntradaInvalidaException{
        if (nome == null || nome.isBlank())
            throw new EntradaInvalidaException("!!!O nome do produto não pode ser vazio!!!");
        else
            this.nome = nome;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) throws EntradaInvalidaException{
        if (precoCusto < 0)
            throw new EntradaInvalidaException("!!!O preço do custo do produto deve ser maior ou igual a 0!!!");
        else
            this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) throws EntradaInvalidaException{
        if (precoVenda < 0)
            throw new EntradaInvalidaException("!!!O preço de venda do produto deve ser maior ou igual a 0!!!");
        else
            this.precoVenda = precoVenda;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) throws EntradaInvalidaException{
        if (quantidadeEstoque < 0)
            throw new EntradaInvalidaException("!!!A quantidade de estoque deve ser maior ou igual a 0!!!");
        else
            this.quantidadeEstoque = quantidadeEstoque;
    }

    public String[] getTamanhos() {
        return tamanhos;
    }

    public void setTamanhos(String[] tamanhos) throws EntradaInvalidaException{
        if (tamanhos == null || tamanhos.length == 0 || 
                tamanhos[0] == null || tamanhos[0].isBlank())
            throw new EntradaInvalidaException("!!!O produto deve ter pelo menos 1 tamanho!!!");
        else
            this.tamanhos = tamanhos;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) throws EntradaInvalidaException{
        if (categoria == null || categoria.isBlank())
            throw new EntradaInvalidaException("!!!O produto deve ter uma categoria!!!");
        else
            this.categoria = categoria;
    }

    public Produto() {
    }

    public Produto(String sku, String nome, double precoCusto, double precoVenda, int quantidadeEstoque, String[] tamanhos, String categoria) throws EntradaInvalidaException{
        setSKU(sku);
        setNome(nome);
        setPrecoCusto(precoCusto);
        setPrecoVenda(precoVenda);
        setQuantidadeEstoque(quantidadeEstoque);
        setTamanhos(tamanhos);
        setCategoria(categoria);
    }
        
    public double calcularMarkup(){
        return ((precoVenda - precoCusto) / precoCusto) * 100;
    }
    
    public void atualizarEstoque(int qtd) throws EntradaInvalidaException{
        setQuantidadeEstoque(qtd);
    }
    
    public boolean verificarDisponibilidade(){
        if (this.quantidadeEstoque <= 0)
            return false;
        else
            return true;
    }
    
    public boolean validarProduto(){
        if (this.sku != null && !this.sku.isBlank()){
            if (this.nome != null && !this.nome.isBlank()){
                if (this.precoCusto >= 0){
                    if (this.precoVenda >= 0){
                        if (this.quantidadeEstoque >= 0){
                            if (this.tamanhos != null && 
                                    this.tamanhos.length > 0 && 
                                    this.tamanhos[0] != null && 
                                    !this.tamanhos[0].isBlank()){
                                if (this.categoria != null && !this.categoria.isBlank()){
                                    return true;
                                }
                                else
                                    System.out.println("!!!O produto " + this.sku + " não possui uma categoria!!!");
                            }
                            else
                                System.out.println("!!!O produto " + this.sku + " não possui um tamanho!!!");
                        }
                        else
                            System.out.println("!!!O produto " + this.sku + " não possui um quantidade de estoque válida!!!");
                    }
                    else
                        System.out.println("!!!O produto " + this.sku + " não possui um preço de venda válido!!!");
                }
                else
                    System.out.println("!!!O produto " + this.sku + " não possui um preço de custo válido!!!");
            }
            else
                System.out.println("!!!O produto " + this.sku + " não possui um nome!!!");
        }
        else
            System.out.println("!!!O produto não possui um SKU!!!");
        return false;
    }
    
}
