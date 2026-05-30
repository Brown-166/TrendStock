package com.mycompany.trendstock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;


public class TrendStock {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<Produto> produtosCadastrados = new ArrayList<>();
        ArrayList<Colecao> colecoesCadastradas = new ArrayList<>();
        ArrayList<Pedido> pedidosRealizados = new ArrayList<>();
        
        //Produtos e coleções cadastrados para demonstração
            Produto blusa = null, calca = null, calca2;

            Colecao colecao1 = null, colecao2 = null;

            try {
                blusa = new Produto("0001", "Blusa One Piece", 19.50, 49.99, 
                        20, new String[]{"M"}, "blusa");

                calca = new Produto("0002", "Calça Jeans Preta", 12.50, 39.99, 
                        30, new String[]{"M"}, "calça");

                calca2 = new Produto("0003", "Calça Jeans Cinza", 9.50, 34.99, 
                        25, new String[]{"G"}, "calça");

                produtosCadastrados.add(blusa);
                produtosCadastrados.add(calca);
                produtosCadastrados.add(calca2);

                colecao1 = new Colecao("Coleção One Piece", 2025, "verão");
                colecao1.adicionarProduto(blusa);

                colecao2 = new Colecao("Coleção Calças Jeans", 2020, "outono");
                colecao2.adicionarProduto(calca);
                colecao2.adicionarProduto(calca2);

                colecoesCadastradas.add(colecao1);
                colecoesCadastradas.add(colecao2);
            } 
            catch (EntradaInvalidaException e) {
                System.out.println(e.getMessage());
            }

            ItemPedido itemBlusa = new ItemPedido(blusa, 4);
            ItemPedido itemCalca = new ItemPedido(calca, 5);

            ArrayList<ItemPedido> itens1 = new ArrayList<>();
            itens1.add(itemBlusa);

            ArrayList<ItemPedido> itens2 = new ArrayList<>();
            itens2.add(itemCalca);

            

            Pedido pedido1 = new Pedido(itens1);

            Pedido pedido2 = new Pedido(itens2);

            pedidosRealizados.add(pedido1);
            pedidosRealizados.add(pedido2);
        
        
        String usuario = "";
        int opcao = -1;
        
        do{
            System.out.println("-------------------TrendStock-------------------");
            System.out.println("Selecione o usuário:");
            System.out.println("1 - Administrador");
            System.out.println("2 - Estoquista");
            System.out.println("3 - Vendedor");
            System.out.println("0 - Encerrar o programa");
            
            try{
                opcao = scanner.nextInt();
            }
            catch(java.util.InputMismatchException e){
                System.out.println("!!!Digite um número!!!");
                opcao = -1;
                scanner.nextLine();
            }
            catch(java.util.NoSuchElementException e){
                System.out.println("!!!Digite um número!!!");
                opcao = -1;
                scanner.nextLine();
            }
            
            switch (opcao){
                case 0 -> System.out.println("Encerrando programa...");
                case 1 -> usuario = "administrador";
                case 2 -> usuario = "estoquista";
                case 3 -> usuario = "vendedor";
                default -> System.out.println("!!!Opção inválida!!!");
            }
            
            if(usuario.equals("administrador")){
                menuAdministrador(scanner, produtosCadastrados, 
                        colecoesCadastradas, pedidosRealizados);
                usuario = "";
                opcao = -1;
            }
            
            if(usuario.equals("estoquista")){
                menuEstoquista(scanner, produtosCadastrados);
                usuario = "";
                opcao = -1;
            }
            if(usuario.equals("vendedor")){
                menuVendedor(scanner, produtosCadastrados, 
                        colecoesCadastradas, pedidosRealizados);
                usuario = "";
                opcao = -1;
            }
        } while(opcao != 0);
        
        scanner.close();
    }

    
    
    
    
    public static void menuAdministrador (Scanner scanner, 
            ArrayList<Produto> produtosCadastrados, 
            ArrayList<Colecao> colecoesCadastradas,
            ArrayList<Pedido> pedidosRealizados){
        int opcao = -1; 
        
        do{
            System.out.println("-------------------TrendStock-------------------");
            System.out.println("Usuario: Administrador");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Cadastrar coleção");
            System.out.println("3 - Gerar relatório");
            System.out.println("4 - Calcular markup");
            System.out.println("0 - Sair para o menu");
            
            try{
                opcao = scanner.nextInt();
            }
            catch(java.util.InputMismatchException e){
                System.out.println("!!!Digite um número!!!");
                opcao = -1;
                scanner.nextLine();
            }
            catch(java.util.NoSuchElementException e){
                System.out.println("!!!Digite um número!!!");
                opcao = -1;
                scanner.nextLine();
            }

            switch(opcao){
                case 0 -> System.out.println("Saindo para o menu...");
                
                case 1 -> {
                    Produto produto = cadastrarProduto(scanner, 
                            produtosCadastrados);
                    if (produto.validarProduto()){
                        produtosCadastrados.add(produto);
                        System.out.println("Produto cadastrado com sucesso");
                    }
                    else
                        System.out.println("!!!O produto não foi cadastrado!!!");
                }
                case 2 -> {
                    if (produtosCadastrados.isEmpty()){
                        System.out.println("!!!Não existem produtos cadastrados "
                                + "para serem adicionados a uma coleção!!!");
                        break;
                    }

                    if (!colecoesCadastradas.isEmpty()){
                        int produtosValidos = 0;
                        for (Produto produto : produtosCadastrados){
                            for (Colecao colecaoLista : colecoesCadastradas){
                                if (!colecaoLista.getProdutos().contains(produto))
                                    produtosValidos += 1;
                                
                            }
                        }

                        if (produtosValidos == 0){
                            System.out.println("!!!Não existem produtos válidos "
                                    + "para serem adicionados a uma coleção!!!");
                            break;
                        }
                    }

                    Colecao colecao = cadastrarColecao(scanner, 
                            produtosCadastrados, 
                            colecoesCadastradas);
                    if (colecao.validarColecao()){
                        colecoesCadastradas.add(colecao);
                        System.out.println("Coleção cadastrada com sucesso");
                    }
                    else
                        System.out.println("!!!A coleção não foi cadastrada!!!");
                }
                case 3 -> {
                    gerarRelatorio(scanner, pedidosRealizados, produtosCadastrados);
                }
                case 4 -> {
                    if (produtosCadastrados.isEmpty()){
                        System.out.println("!!!Não existem produtos cadastrados "
                                + "para calcular o markup!!!");
                        break;
                    }
                    
                    calcularMarkup(produtosCadastrados);
                }
                default -> System.out.println("!!!Opção inválida!!!");
            }
        } while(opcao != 0);
        scanner.nextLine();
    }
    
    
   public static Produto cadastrarProduto(Scanner scanner, 
           ArrayList<Produto> produtosCadastrados){
       
        Produto produto = new Produto();
                            
        String dadoProduto;
        
        try{
            scanner.nextLine();
            System.out.println("Digite o SKU: ");
            dadoProduto = scanner.nextLine();
            boolean skuCadastrado = false;
            for (Produto p : produtosCadastrados){
                if (p.getSKU().equals(dadoProduto)){
                    skuCadastrado = true;
                    System.out.println("!!!Esse SKU já foi cadastrado!!!");
                    return produto;
                }
            }
            if (!skuCadastrado) produto.setSKU(dadoProduto);

            System.out.println("Digite a categoria: ");
            dadoProduto = scanner.nextLine();
            produto.setCategoria(dadoProduto);

            System.out.println("Digite o nome: ");
            dadoProduto = scanner.nextLine();
            produto.setNome(dadoProduto);

            System.out.println("Digite os tamanhos: \n(ex: M G GG)");
            dadoProduto = scanner.nextLine();
            
            String[] tamanhos = dadoProduto.split(" ");
            
            produto.setTamanhos(tamanhos);

            System.out.println("Digite o preço do custo: ");
            dadoProduto = scanner.nextLine();
            produto.setPrecoCusto(Double.parseDouble(dadoProduto));

            System.out.println("Digite o preço de venda: ");
            dadoProduto = scanner.nextLine();
            produto.setPrecoVenda(Double.parseDouble(dadoProduto));

            System.out.println("Digite a quantidade em estoque: ");
            dadoProduto = scanner.nextLine();
            produto.setQuantidadeEstoque(Integer.parseInt(dadoProduto));
 
        }
        catch(java.util.InputMismatchException e){
            System.out.println("!!!Entrada inválida!!!");
        }
        catch(java.util.NoSuchElementException e){
            System.out.println("!!!Entrada inválida!!!");
        }
        catch(EntradaInvalidaException e){
            System.out.println(e.getMessage());
        }
        
        
        return produto;
   }
   
   
   public static Colecao cadastrarColecao(Scanner scanner, 
           ArrayList<Produto> produtosCadastrados, 
           ArrayList<Colecao> colecoesCadastradas){
       
        Colecao colecao = new Colecao();
                            
        String dadoColecao;

        try{
            scanner.nextLine();
            System.out.println("Digite o nome: ");
            dadoColecao = scanner.nextLine();

            boolean nomeCadastrado = false;
            for (Colecao c : colecoesCadastradas){
                if (c.getNome().equals(dadoColecao)){
                    nomeCadastrado = true;
                    System.out.println("!!!Já existe uma coleção com esse nome!!!");
                    return colecao;
                }
            }
            if (!nomeCadastrado) 
                colecao.setNome(dadoColecao);


            System.out.println("Digite o ano: ");
            dadoColecao = scanner.nextLine();
            colecao.setAno(Integer.parseInt(dadoColecao));

            System.out.println("Digite a estação: ");
            dadoColecao = scanner.nextLine();
            colecao.setEstacao(dadoColecao);

            System.out.println("Digite o SKU dos produtos que serão adicionados a coleção: ");
            do{
                for (Produto produto : produtosCadastrados){
                    boolean produtoEmColecao = false;
                    for (Colecao colecaoLista : colecoesCadastradas){
                        if (colecaoLista.getProdutos().contains(produto))
                            produtoEmColecao = true;
                    }
                    if (!produtoEmColecao){
                        System.out.println("SKU: " + produto.getSKU());
                        System.out.println("Nome: " + produto.getNome());
                        System.out.println("--------------------------");
                    }
                }

                dadoColecao = scanner.nextLine();

                boolean skuValido = false;
                boolean produtoEmColecao = false;
                Produto produtoParaColecao = new Produto();

                for (Produto produto : produtosCadastrados){
                    if (produto.getSKU().equals(dadoColecao)){
                        skuValido = true;
                        produtoParaColecao = produto;
                        for (Colecao colecaoLista : colecoesCadastradas){
                            if (colecaoLista.getProdutos().contains(produto)){
                                produtoEmColecao = true;
                                System.out.println("!!!Esse produto já está em o"
                                        + "utra coleção!!!");
                            }
                        }
                        
                        if(colecao.getProdutos().contains(produto)){
                            produtoEmColecao = true;
                            System.out.println("!!!Esse produto já foi "
                                    + "adicionado a essa coleção!!!");
                        }
                    }
                }

                if (skuValido && !produtoEmColecao){
                    colecao.adicionarProduto(produtoParaColecao);
                    System.out.println("Produto adicionado a coleção");
                } else 
                    System.out.println("!!!SKU ínválido!!!");

                do{
                    System.out.println("Continuar adicionando produtos a coleção?");
                    System.out.println("0 - Não");
                    System.out.println("1 - Sim");
                    dadoColecao = scanner.nextLine();

                    if (Integer.parseInt(dadoColecao) != 0 && Integer.parseInt(dadoColecao) != 1){
                        System.out.println("!!!Opção inválida!!!");
                    } else System.out.println("\n\n");
                }while(Integer.parseInt(dadoColecao) != 0 && Integer.parseInt(dadoColecao) != 1);

            } while(Integer.parseInt(dadoColecao) != 0);
        }
        catch(java.util.InputMismatchException e){
            System.out.println("!!!Entrada inválida!!!");
        }
        catch(java.util.NoSuchElementException e){
            System.out.println("!!!Entrada inválida!!!");
        }
        catch(EntradaInvalidaException e){
            System.out.println(e.getMessage());
        }
        
        
        return colecao;  
   }
   
   
   public static void gerarRelatorio(Scanner scanner, 
           ArrayList<Pedido> pedidosRealizados, 
           ArrayList<Produto> produtosCadastrados){
        Relatorio relatorio = new Relatorio();
        
        int opcao = -1;
        
        do{
            System.out.println("1 - Gerar relatório de vendas");
            System.out.println("2 - Gerar relatório de estoque");
            try{
                opcao = scanner.nextInt();
            }
            catch(java.util.InputMismatchException e){
                System.out.println("!!!Digite um número!!!");
                opcao = -1;
                scanner.nextLine();
            }
            catch(java.util.NoSuchElementException e){
                System.out.println("!!!Digite um número!!!");
                opcao = -1;
                scanner.nextLine();
            }

            switch(opcao){
                case 1 -> {
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String lerData = "";
                    scanner.nextLine();

                    LocalDate dataInicial = null, dataFinal = null;
                    
                    do{
                        System.out.println("Digite a data de início "
                                + "do relatório dd/mm/aaaa");
                        try{
                            lerData = scanner.nextLine();
                            dataInicial = LocalDate.parse(lerData, formato);
                        }
                        catch(java.util.InputMismatchException e){
                            System.out.println("!!!Data inválida!!!");
                            lerData = "";
                            scanner.nextLine();
                        }
                        catch(java.util.NoSuchElementException | 
                                java.time.format.DateTimeParseException e){
                            System.out.println("!!!Data inválida!!!");
                            lerData = "";
                        }
                    } while (lerData.equals(""));
                    

                    do{
                        System.out.println("Digite a data de fim do "
                                + "relatório dd/mm/aaaa");
                        try{
                            lerData = scanner.nextLine();
                            dataFinal = LocalDate.parse(lerData, formato);
                        }
                        catch(java.util.InputMismatchException e){
                            System.out.println("!!!Data inválida!!!");
                            lerData = "";
                            scanner.nextLine();
                        }
                        catch(java.util.NoSuchElementException | 
                                java.time.format.DateTimeParseException e){
                            System.out.println("!!!Data inválida!!!");
                            lerData = "";
                        }
                    } while (lerData.equals(""));                 
                    
                    
                    relatorio.setInicioPeriodo(dataInicial);
                    relatorio.setFimPeriodo(dataFinal);
                    

                    if (!relatorio.verificarPeriodo()){
                        System.out.println("!!!A data de início do relatório "
                                + "deve ser antes ou igual a data de fim!!!");
                        break;
                    }

                    relatorio.gerarRelatorioVendas(pedidosRealizados);
                    break;
                }
                case 2 -> {
                    relatorio.gerarRelatorioEstoque(produtosCadastrados);
                }
                default -> System.out.println("!!!Opção inválida!!!");
            } 
        } while (opcao != 1 && opcao != 2);
   }

   
   public static void calcularMarkup(ArrayList<Produto> produtosCadastrados){
       System.out.println("========== MARKUP ==========");
       for (Produto produto : produtosCadastrados){
           System.out.println("SKU: " + produto.getSKU());
           System.out.println("Nome: " + produto.getNome());
           System.out.println("Markup: " + String.format("%.2f", 
                   produto.calcularMarkup()) + "%");
           System.out.println("----------------------------");
       }
   }
   
   
   
   
   
   public static void menuEstoquista(Scanner scanner, 
           ArrayList<Produto> produtosCadastrados){
        int opcao = -1; 
        
        do{
            System.out.println("-------------------TrendStock-------------------");
            System.out.println("Usuario: Estoquista");
            System.out.println("1 - Consultar estoque por SKU");
            System.out.println("2 - Atualizar estoque");
            System.out.println("3 - Verificar produtos parados");
            System.out.println("0 - Sair para o menu");
            
            try{
                opcao = scanner.nextInt();
            }
            catch(java.util.InputMismatchException e){
                System.out.println("!!!Digite um número!!!");
                opcao = -1;
                scanner.nextLine();
            }
            catch(java.util.NoSuchElementException e){
                System.out.println("!!!Digite um número!!!");
                opcao = -1;
                scanner.nextLine();
            }

            switch(opcao){
                case 0 -> System.out.println("Saindo para o menu...");
                
                case 1 -> {
                    if (produtosCadastrados.isEmpty()){
                        System.out.println("!!!Não existem produtos cadastrados "
                                + "para serem consultados!!!");
                        break;
                    }
                    consultarEstoque(scanner, produtosCadastrados);
                }
                case 2 -> {
                    if (produtosCadastrados.isEmpty()){
                        System.out.println("!!!Não existem produtos cadastrados "
                                + "para serem atualizados!!!");
                        break;
                    }
                    atualizarEstoque(scanner, produtosCadastrados);
                }
                case 3 -> {
                    if (produtosCadastrados.isEmpty()){
                        System.out.println("!!!Não existem produtos cadastrados "
                                + "para serem verificados!!!");
                        break;
                    }
                    verificarEstoque(produtosCadastrados);
                }
                default -> System.out.println("!!!Opção inválida!!!");
            }
        } while(opcao != 0);
        scanner.nextLine();
   }
   
   
    public static void consultarEstoque(Scanner scanner,
            ArrayList<Produto> produtosCadastrados){

        try{
            scanner.nextLine();
            System.out.println("Digite o SKU: ");
            String sku = scanner.nextLine();

            Relatorio relatorio = new Relatorio();

            relatorio.gerarRelatorioEstoque(produtosCadastrados, sku);
        }
        catch(java.util.InputMismatchException e){
            System.out.println("!!!Entrada inválida!!!");
        }
        catch(java.util.NoSuchElementException e){
            System.out.println("!!!Entrada inválida!!!");
        }
    }

   
    public static void atualizarEstoque(Scanner scanner,
            ArrayList<Produto> produtosCadastrados){

        try{
            scanner.nextLine();
            System.out.println("Digite o SKU: ");
            String sku = scanner.nextLine();

           boolean skuEncontrado = false;

           for (Produto produto : produtosCadastrados){
               if (produto.getSKU().equals(sku)){
                   skuEncontrado = true;
                }
            }
           if (!skuEncontrado) System.out.println("!!!Produto não encontrado!!!");
           else{
               System.out.println("Digite a nova quantidade do estoque: ");
               int novaQuantidade = scanner.nextInt();

               for (Produto produto : produtosCadastrados){
                   if (produto.getSKU().equals(sku)){
                       produto.atualizarEstoque(novaQuantidade);
                    }
                }
                  System.out.println("Estoque atualizado");
            }
        }
        catch(java.util.InputMismatchException e){
            System.out.println("!!!Entrada inválida!!!");
        }
        catch(java.util.NoSuchElementException e){
            System.out.println("!!!Entrada inválida!!!");
        }
        catch(EntradaInvalidaException e){
            System.out.println(e.getMessage());
        }
    }
    
    
    public static void verificarEstoque(ArrayList<Produto> produtosCadastrados){
        Relatorio relatorio = new Relatorio();
        relatorio.listarProdutosParados(produtosCadastrados);
    }
    
    
    
    
    
    public static void menuVendedor (Scanner scanner, 
            ArrayList<Produto> produtosCadastrados, 
            ArrayList<Colecao> colecoesCadastradas,
            ArrayList<Pedido> pedidosRealizados){
        int opcao = -1; 
        
        do{
            System.out.println("-------------------TrendStock-------------------");
            System.out.println("Usuario: Vendedor");
            System.out.println("1 - Consultar produtos");
            System.out.println("2 - Consultar coleções");
            System.out.println("3 - Realizar pedido");
            System.out.println("0 - Sair para o menu");
            
            try{
                opcao = scanner.nextInt();
            }
            catch(java.util.InputMismatchException e){
                System.out.println("!!!Digite um número!!!");
                opcao = -1;
                scanner.nextLine();
            }
            catch(java.util.NoSuchElementException e){
                System.out.println("!!!Digite um número!!!");
                opcao = -1;
                scanner.nextLine();
            }

            switch(opcao){
                case 0 -> System.out.println("Saindo para o menu...");
                
                case 1 -> {
                    if (produtosCadastrados.isEmpty()){
                        System.out.println("!!!Não existem produtos cadastrados "
                                + "para serem consultados!!!");
                        break;
                    }
                    consultarProdutos(produtosCadastrados);
                }
                case 2 -> {
                    if (colecoesCadastradas.isEmpty()){
                        System.out.println("!!!Não existem coleções cadastradas "
                                + "para serem consultadas!!!");
                        break;
                    }
                    consultarColecoes(colecoesCadastradas);
                }
                case 3 -> {
                    if (produtosCadastrados.isEmpty() && colecoesCadastradas.isEmpty()){
                        System.out.println("!!!Não existem produtos ou coleções "
                                + "cadastradas!!!");
                        break;
                    }
                    Pedido pedido = realizarPedido(scanner, produtosCadastrados,
                            colecoesCadastradas);
                    
                    if (pedido.validarPedido()){
                        
                        double valorTotal = 0;

                        System.out.println("============PEDIDO============");
                        for (ItemPedido item : pedido.getItens()){

                            double subTotal = item.calcularSubtotal();
                            valorTotal += subTotal;

                            System.out.println("SKU: " + item.getProduto().getSKU());
                            System.out.println("Nome: " + item.getProduto().getNome());
                            System.out.println("Preço: " + String.format("%.2f", 
                                    item.getProduto().getPrecoVenda()));
                            System.out.println("Quantidade: " + item.getQuantidade());
                            System.out.println("Sub Total: R$" + String.format("%.2f", subTotal));
                            System.out.println("-------------------------------");
                        }

                        System.out.println("Valor Total: R$" + String.format("%.2f", valorTotal));
                        System.out.println("==============================");

                        pedidosRealizados.add(pedido);
                        System.out.println("O pedido foi realizado");
                    }
                    else System.out.println("!!!O pedido não foi realizado!!!");
                }
                default -> System.out.println("!!!Opção inválida!!!");
            }
        } while(opcao != 0);
        scanner.nextLine();
    }
    
    
    public static void consultarProdutos(ArrayList<Produto> produtosCadastrados){
        System.out.println("======== PRODUTOS ========");
        for (Produto produto : produtosCadastrados){
            System.out.println("SKU: " + produto.getSKU());
            System.out.println("Categoria: " + produto.getCategoria());
            System.out.println("Nome: " + produto.getNome());
            System.out.print("Tamanhos: "); 
            for (String tamanho : produto.getTamanhos()){
                System.out.print(tamanho + " "); 
            }
            System.out.println("Preço: " + String.format("%.2f", produto.getPrecoVenda()));
            System.out.println("--------------------------");
        }
    }
    
    
    public static void consultarColecoes(ArrayList<Colecao> colecoesCadastradas){
        System.out.println("======== COLEÇÕES ========");
        for (Colecao colecao : colecoesCadastradas){
            System.out.println("Nome: " + colecao.getNome());
            System.out.println("Ano: " + colecao.getAno());
            System.out.println("Estação: " + colecao.getEstacao());
            System.out.println("Produtos: ");
            for (Produto produto : colecao.getProdutos()){
                System.out.println("---SKU: " + produto.getSKU());
                System.out.println("---Nome: " + produto.getNome());
                System.out.println("--------------------------");
            }
            System.out.println("--------------------------");
        }
    }
    
    
    public static Pedido realizarPedido (Scanner scanner, 
            ArrayList<Produto> produtosCadastrados, 
            ArrayList<Colecao> colecoesCadastradas){
        
        Pedido pedido = new Pedido();
        
        int opcao = -1;
        
        do{
            System.out.println("1 - Adicionar produto ao pedido");
            System.out.println("2 - Adicionar coleção ao pedido");
            System.out.println("0 - Finalizar pedido");
            
            try{
                opcao = scanner.nextInt();
            }
            catch(java.util.InputMismatchException e){
                System.out.println("!!!Digite um número!!!");
                opcao = -1;
                scanner.nextLine();
            }
            catch(java.util.NoSuchElementException e){
                System.out.println("!!!Digite um número!!!");
                opcao = -1;
                scanner.nextLine();
            }

            switch(opcao){
                case 0 -> System.out.println("Finalizando pedido...");
                    
                case 1 -> {
                    String sku = "";
                    int quantidade = 0;
                    boolean skuEncontrado = false;
                    
                    try{
                        scanner.nextLine();
                        System.out.println("Digite o SKU: ");
                        sku = scanner.nextLine();

                        for (Produto produto : produtosCadastrados){
                            if (produto.getSKU().equals(sku)){
                                skuEncontrado = true;

                                System.out.println("Digite a quantidade: ");

                                quantidade = scanner.nextInt();
                            }
                        }
                        if (!skuEncontrado) {
                            System.out.println("!!!Produto não encontrado!!!");
                            break;
                        }
                        
                        ItemPedido item = adicionarProduto(scanner, produtosCadastrados, sku, quantidade);
                        
                        if (!item.validarItem()){
                            System.out.println("!!!O produto não foi adicionado ao pedido!!!");
                            break;
                        }
                        
                        boolean itemNoPedido = false;
                        
                        for (ItemPedido i : pedido.getItens()){
                            if (i.getProduto().equals(item.getProduto())){
                                itemNoPedido = true;
                                i.setQuantidade(i.getQuantidade() + item.getQuantidade());
                                System.out.println("O produto foi adicionado ao pedido");
                            }
                        }
                        
                        if (!itemNoPedido){
                            pedido.adicionarItem(item);
                            System.out.println("O produto foi adicionado ao pedido");
                        }
                    }
                    catch(java.util.InputMismatchException e){
                        System.out.println("!!!Entrada inválida!!!");
                    }
                    catch(java.util.NoSuchElementException e){
                        System.out.println("!!!Entrada inválida!!!");
                    } 
                }
                case 2 -> {
                    if (colecoesCadastradas.isEmpty()){
                        System.out.println("!!!Não existem coleções cadastradas!!!");
                        break;
                    }
                    
                    String nome = ""; 
                    boolean colecaoEncontrada = false;
                    
                    try{
                        scanner.nextLine();
                        System.out.println("Digite o nome da coleção: ");
                        nome = scanner.nextLine();

                        for (Colecao colecao : colecoesCadastradas){
                            if (colecao.getNome().equals(nome)){
                                colecaoEncontrada = true;
                            }
                        }
                        if (!colecaoEncontrada) {
                            System.out.println("!!!Coleção não encontrada!!!");
                            break;
                        }
                        
                        ArrayList<ItemPedido> itens = adicionarColecao(scanner,
                                colecoesCadastradas, nome);
                        
                        if (itens == null){
                            System.out.println("!!!Os produtos da coleção não foram adicionados ao pedido!!!");
                            break;
                        }
                        
                        for (ItemPedido item : itens){
                            boolean itemNoPedido = false;
                        
                            for (ItemPedido i : pedido.getItens()){
                                if (i.getProduto().equals(item.getProduto())){
                                    i.setQuantidade(i.getQuantidade() + item.getQuantidade());
                                    itemNoPedido = true;                                }
                            }

                            if (!itemNoPedido){
                                pedido.adicionarItem(item);
                            }
                        }
                        System.out.println("Os produtos da coleção foram adicionados ao pedido");
                    }
                    catch(java.util.InputMismatchException e){
                        System.out.println("!!!Entrada inválida!!!");
                    }
                    catch(java.util.NoSuchElementException e){
                        System.out.println("!!!Entrada inválida!!!");
                    } 
                }
            }
        } while(opcao != 0);
        
        return pedido;
    }
    
    
    public static ItemPedido adicionarProduto(Scanner scanner,
            ArrayList<Produto> produtosCadastrados, 
            String sku, int quantidade){
        
        ItemPedido item = new ItemPedido();
        
        if (quantidade == 0){
            System.out.println("Nenhum produto adicionado");
            return item;
        } 
        else if(quantidade < 0){
            System.out.println("!!!A quantidade não pode ser menor do que 0!!!");
            return item;
        }
        
        
        for (Produto produto : produtosCadastrados){
            if (produto.getSKU().equals(sku)){
                
                try {
                    int novaQuantidade = produto.getQuantidadeEstoque() - quantidade;
                    
                    if (novaQuantidade < 0){
                        System.out.println("!!!A quantidade digitada é maior do que o estoque disponível!!!");
                        return item;
                    }
                    
                    produto.atualizarEstoque(novaQuantidade);
                    
                    item.setProduto(produto);
                    item.setQuantidade(quantidade);
                    
                } 
                catch (EntradaInvalidaException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return item;
    }


    public static ArrayList<ItemPedido> adicionarColecao(Scanner scanner,
            ArrayList<Colecao> colecoesCadastradas, String nome){
        
        ArrayList<ItemPedido> itemColecao = new ArrayList<>();
        
        
        for (Colecao colecao : colecoesCadastradas){
            if (colecao.getNome().equals(nome)){
                for (Produto produto : colecao.getProdutos()){
                    try {
                        int novaQuantidade = produto.getQuantidadeEstoque() - 1;

                        if (novaQuantidade < 0){
                            System.out.println("!!!Alguns produtos dessa coleção estão sem estoque!!!");
                            itemColecao = null;
                            return itemColecao;
                        }

                        produto.atualizarEstoque(novaQuantidade);
                        
                        ItemPedido item = new ItemPedido(produto, 1);
                        
                        itemColecao.add(item);
                    } 
                    catch (EntradaInvalidaException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        
        return itemColecao;
    }
}