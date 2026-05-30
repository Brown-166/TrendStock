package com.mycompany.trendstock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Pedido {
    private String numeroPedido = UUID.randomUUID().toString();;
    private LocalDateTime dataDoPedido = LocalDateTime.now();
    private double valorTotal = 0;
    private ArrayList<ItemPedido> itens = new ArrayList<>();

    
    public String getNumeroPedido() {
        return numeroPedido;
    }

    public LocalDateTime getDataDoPedido() {
        return dataDoPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItemPedido> itens) {
        for (ItemPedido item : itens){
            adicionarItem(item);
        }
    }

    public Pedido() {
    }

    public Pedido(ArrayList<ItemPedido> itens) {
        setItens(itens);
    }
    
    
    public void adicionarItem(ItemPedido item){
        this.itens.add(item);
    }
    
    public void calcularTotal(){
        for (ItemPedido item : this.itens){
            this.valorTotal += item.calcularSubtotal();
        }
    }
    
    public boolean validarPedido(){
        if (!itens.isEmpty()) return true;
        
        System.out.println("!!!O pedido não possui produtos!!!");
        return false;
    }

}
