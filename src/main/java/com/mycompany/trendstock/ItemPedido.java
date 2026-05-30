package com.mycompany.trendstock;


public class ItemPedido {
    private Produto produto = null;
    private int quantidade = 0;
    private double subTotal;

    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        if (produto.validarProduto())
            this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 1)
            System.out.println("!!!Quantidade inválida!!!");
        else
            this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return subTotal;
    }

    public ItemPedido() {
    }

    public ItemPedido(Produto produto, int quantidade) {
        setProduto(produto);
        setQuantidade(quantidade);
    }
    
    public double calcularSubtotal(){
        this.subTotal = this.produto.getPrecoVenda() * this.quantidade;
        return this.subTotal;
    }
    
    public boolean validarItem(){
        if (produto != null){
            if (quantidade > 0){
                return true;
            }
            else System.out.println("!!!O item do pedido não possui uma quantidade válida!!!");
        }
        else System.out.println("!!!O item do pedido não possui um produto!!!");
        
        return false;
    }

}
