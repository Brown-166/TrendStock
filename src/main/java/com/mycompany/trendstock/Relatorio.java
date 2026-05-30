package com.mycompany.trendstock;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;


public class Relatorio {
    private LocalDate inicioPeriodo;
    private LocalDate fimPeriodo;


    public LocalDate getInicioPeriodo() {
        return inicioPeriodo;
    }

    public void setInicioPeriodo(LocalDate inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }

    public LocalDate getFimPeriodo() {
        return fimPeriodo;
    }

    public void setFimPeriodo(LocalDate fimPeriodo) {
        this.fimPeriodo = fimPeriodo;
    }

    public Relatorio() {
    }

    public Relatorio(LocalDate inicioPeriodo, LocalDate fimPeriodo) {
        setInicioPeriodo(inicioPeriodo);
        setFimPeriodo(fimPeriodo);
    }
    
    public boolean verificarPeriodo(){
        if (this.inicioPeriodo.isBefore(fimPeriodo) || this.inicioPeriodo.isEqual(fimPeriodo))
            return true;
        return false;
    }
    
    public void gerarRelatorioVendas(ArrayList<Pedido> pedidos) {
        
        pedidos.sort(Comparator.comparing(Pedido::getDataDoPedido));
        
        double totalVendas = 0;
        
        System.out.println("===== RELATÓRIO DE VENDAS =====");
        for (Pedido pedido : pedidos) {
            LocalDate dataPedido = pedido.getDataDoPedido().toLocalDate();
            
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

            String dataFormatada = pedido.getDataDoPedido().format(formato);
            
            if (!dataPedido.isBefore(this.inicioPeriodo) && !dataPedido.isAfter(this.fimPeriodo)) {
                pedido.calcularTotal();
                
                System.out.println("Pedido: " + pedido.getNumeroPedido());
                System.out.println("Data: " + dataFormatada);
                System.out.println("Valor: R$ " + String.format("%.2f", pedido.getValorTotal()));

                totalVendas += pedido.getValorTotal();
                System.out.println("--------------------------");
            } 
        }
        System.out.println("Total vendido: R$ " + String.format("%.2f", totalVendas));
    }
    
    public void gerarRelatorioEstoque(ArrayList<Produto> produtos) {
        System.out.println("===== RELATÓRIO DE ESTOQUE =====");
        for (Produto produto : produtos) {
            System.out.println("SKU: " + produto.getSKU());
            System.out.println("Produto: " + produto.getNome());
            System.out.println("Quantidade: " + produto.getQuantidadeEstoque());
            System.out.println("--------------------------");
        }
    }
    
    public void gerarRelatorioEstoque(ArrayList<Produto> produtos, String sku) {
        boolean skuEncontrado = false;

        for (Produto p : produtos){
            if (p.getSKU().equals(sku)){
                skuEncontrado = true;
                System.out.println("SKU: " + p.getSKU());
                System.out.println("Produto: " + p.getNome());
                System.out.println("Quantidade: " + p.getQuantidadeEstoque());
            }
        }
        if (!skuEncontrado) System.out.println("!!!Produto não encontrado!!!");
    }
    
     public void listarProdutosParados(ArrayList<Produto> produtos) {
        boolean produtosParados = false;
        
        System.out.println("===== PRODUTOS PARADOS =====");
        for (Produto produto : produtos) {
            if (produto.getQuantidadeEstoque() > 50) {
                produtosParados = true;
                System.out.println("SKU: " + produto.getSKU());
                System.out.println("Produto: " + produto.getNome());
                System.out.println("Estoque: " + produto.getQuantidadeEstoque());
                System.out.println("--------------------------");
            }
        }
        
        if (!produtosParados) System.out.println("Não existem produtos parados");
    }

}
