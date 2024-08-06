import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Listas para armazenar categorias, produtos, clientes e pedidos
    private static List<Categoria> listaCategorias = new ArrayList<>();
    private static List<Produto> listaProdutos = new ArrayList<>();
    private static List<Cliente> listaClientes = new ArrayList<>();
    private static List<Pedido> listaPedidosRealizados = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);
    
    // Variáveis para cliente logado, carrinho e incremento automático de IDs
    private static Cliente clienteLogado;
    private static Pedido carrinho;
    private static int autoIncremente = 0;

    public static void main(String[] args) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("Escolha uma opção:");
            System.out.println("1- Cadastrar nova categorias");
            System.out.println("2- Cadastrar nova subcategorias");
            System.out.println("3- Gerenciar produtos");
            System.out.println("4- Cadastrar clientes");
            System.out.println("5- Menu do cliente");
            System.out.println("6- Gerenciar Pedidos");
            System.out.println("7- Sair");

            // Lê a opção escolhida pelo usuário
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer de entrada

            switch (opcao) {
                case 1:
                    cadastrarCategoria();
                    break;
                case 2:
                    cadastrarSubcategoria();
                    break;
                case 3:
                    gerenciarProdutos();
                    break;
                case 4:
                    cadastrarCliente();
                    break;
                case 5:
                    logarCliente("carrinho");
                    break;
                case 6:
                    logarCliente("pedidos");
                    break;
                case 7:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção não válida!");
            }
        }
    }

    private static void cadastrarCategoria() {
        System.out.println("Digite o nome da categoria que deseja cadastrar:");
        String nomeCategoria = scanner.nextLine();

        Categoria categoria = new Categoria(nomeCategoria);
        // Cria e adiciona a nova categoria à lista
        listaCategorias.add(categoria);
        System.out.println("Categoria adicionada com sucesso!");
    }

    private static void exibirCategorias() {
        // Verifica se há categorias cadastradas
        if (listaCategorias.isEmpty()) {
            System.out.println("Nenhuma categoria encontrada.");
            return;
        }

        for (Categoria categoria : listaCategorias) {
            System.out.println(categoria.getNomeCategoria());

        }
    }

    private static Categoria buscarCategoria(String nome) {
        // Busca uma categoria pelo nome
        for (Categoria categoria : listaCategorias) {
            if (categoria.getNomeCategoria().equalsIgnoreCase(nome)) {
                return categoria;
            }
        }
        return null;
    }

    private static void cadastrarSubcategoria() {
        // Solicita o nome da categoria associada à nova subcategoria
        System.out.println("A qual categoria esta associada sua nova subcategoria? ");
        exibirCategorias();
        String nomeCategoria = scanner.nextLine();
        Categoria categoriaBuscada = new Categoria(); 
        categoriaBuscada= buscarCategoria(nomeCategoria);
	    if(categoriaBuscada == null) 
	    {
            // Oferece a opção de criar uma nova categoria se a buscada não for encontrada
	    	System.out.println("Categoria não encontrada. Deseja criar uma nova com esse nome? S/N");
	    	String respostaNovaCategoria = scanner.nextLine();
	    	if (!respostaNovaCategoria.equals("S")) {
                return;
            }
	    	categoriaBuscada = criarNovaCategoria(nomeCategoria); 	
	    }
        // Solicita o nome da nova subcategoria e cria a subcategoria
        System.out.println("Digite o nome da subcategoria que deseja cadastrar:");
        String nomeSubcategoria = scanner.nextLine();
	    Subcategoria novaSubcategoria = new Subcategoria(nomeSubcategoria,categoriaBuscada);

        categoriaBuscada.adicionarSubcategoria(novaSubcategoria);
}

    private static Categoria criarNovaCategoria(String nomeCategoria) {
        // Cria e adiciona uma nova categoria à lista
        Categoria categoria = new Categoria(nomeCategoria);
        listaCategorias.add(categoria);
        System.out.println("Categoria adicionada com sucesso!");
        return categoria;
    }

    private static void cadastrarCliente() {
        // Solicita informações para cadastro de um novo cliente
        System.out.println("Digite o nome do cliente que deseja cadastrar: ");
        String nomeCliente = scanner.nextLine();
        System.out.println("Digite o ID do cliente que deseja cadastrar: ");
        int idCliente= scanner.nextInt();
        scanner.nextLine();
        Cliente novoCliente= new Cliente(idCliente, nomeCliente);
        listaClientes.add(novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void gerenciarProdutos() {
        boolean escolha = true;
        while (escolha) {
            System.out.println("Escolha uma opção:");
            System.out.println("1- Cadastrar produto");
            System.out.println("2- Exibir produtos disponiveis");
            System.out.println("3- Exibir produtos indisponiveis");
            System.out.println("4- Repor estoque");
            System.out.println("5- Voltar ao menu inicial");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    exibirProdutosDisponiveis();
                    break;
                case 3:  
                    exibirProdutosIndisponiveis();
                    break;
                case 4:
                    reporEstoque();
                    break;
                case 5:
                    escolha= false;
                    break;
                default:
                    System.out.println("Opção não válida!");
            }
        }
    }

    private static void cadastrarProduto() {
        // Solicita informações para cadastro de um novo produto
        System.out.println("Qual o nome do produto que deseja adicionar?");
        String nomeProduto = scanner.nextLine();
        System.out.println("Qual a categoria do produto? Digite o nome da categoria:");
        String nomeCategoria = scanner.nextLine();
        System.out.println("Qual o código do produto?");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Qual o preço do produto que deseja acionar? ");
        float precoProduto = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Qual a quantidade em estoque?");
        int qtdEstoque = scanner.nextInt();
        scanner.nextLine();
        
        Categoria categoriaProduto= buscarCategoria(nomeCategoria); 
        if (categoriaProduto == null) {
            System.out.println("Categoria não foi encontrada!");
            return;
        }

        // Cria e adiciona o novo produto à lista
        Produto produto = new Produto(codigo, nomeProduto, precoProduto, categoriaProduto);
        produto.setQtdEstoque(qtdEstoque);
        listaProdutos.add(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void exibirProdutosDisponiveis() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        // Exibe o nome de produtos que têm estoque disponível
        for (Produto produto : listaProdutos) {
            if (produto.getQtdEstoque() > 0) {
                System.out.println(produto.getNomeProduto());   
            }
        }
    }

        private static void exibirProdutosIndisponiveis() {
        boolean todosDisponiveis = true;

        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Produto produto : listaProdutos) {
            if (produto.getQtdEstoque() == 0) {
                todosDisponiveis= false;
                System.out.println(produto.getNomeProduto());   
            }
        }
        if (todosDisponiveis) {
            System.out.println("Todos os produtos estão disponiveis");
            
        }
    }
    
    private static Produto buscarProdutoNome(String nome) {
        for (Produto produto : listaProdutos) {
            if (produto.getNomeProduto().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        return null;
    }

    private static void reporEstoque(){
        System.out.println("Qual produto quer adicionar ao estoque?");
        String nomeProduto = scanner.nextLine();
        Produto produtoBuscado = buscarProdutoNome(nomeProduto);
        if (produtoBuscado == null) {
            System.out.println("Produto não encontrado");
            return;
        }
        System.out.println("Qual a quantidade deseja adicionar? ");
        int quantidadeSelecionada = scanner.nextInt();
        scanner.nextLine();
        // Atualiza a quantidade em estoque do produto
        int quantidadeAtual = produtoBuscado.getQtdEstoque();
        produtoBuscado.setQtdEstoque(quantidadeSelecionada + quantidadeAtual);

    }

    private static void logarCliente(String opcao){
        System.out.println("Por favor digite seu ID: ");
        int idBuscado = scanner.nextInt();
        scanner.nextLine();
        clienteLogado = obterCliente(idBuscado);
        if (clienteLogado == null) {
            System.out.println("Cliente não encontrado");
            return;
        }
        if (opcao.equals("pedidos")) {
            gerenciarPedidos();
        }
        else menuCliente();
    }

    private static Cliente obterCliente(int ID) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getIdCliente() == (ID)) {
                return cliente;
            }
        }
        return null;
    }
    
    private static void menuCliente(){
        boolean escolha = true;
        carrinho = new Pedido();
        while (escolha) {
            System.out.println("Escolha uma opção:");
            System.out.println("1- Buscar produto");
            System.out.println("2- Remover do carrinho");
            System.out.println("3- Exibir produtos disponiveis");
            System.out.println("4- Ver carrinho");
            System.out.println("5- Limpar carrinho");
            System.out.println("6- Realizar pedido");
            System.out.println("7- Voltar ao menu inicial");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    buscarProduto();
                    break;
                case 2:
                    removerDoCarrinho();
                    break;
                case 3:
                    exibirProdutosDisponiveis();
                    break;
                case 4:
                    carrinho.verTodosProdutos();;
                    break;
                case 5:
                    carrinho.limparPedido();
                    break;
                case 6:
                    realizarPedido();
                    break;
                case 7:
                    escolha= false;
                    clienteLogado= null;
                    break;
                default:
                    System.out.println("Opção não válida!");
            }
        }
    }

    private static Produto lerProduto() {
        System.out.println("Digite o nome, código ou categoria do produto que deseja buscar:");
        String nomeProdutoBuscado = scanner.nextLine();
        return obterProdutoGeral(nomeProdutoBuscado);
    }

    private static Produto obterProdutoGeral(String nome) {
        // Tenta converter o nome para um código numérico, se possível
        Integer codigoBuscado = null;
        try {
            codigoBuscado = Integer.parseInt(nome);
        } catch (NumberFormatException e) {
            // Não faz nada, o códigoBuscado permanece null
        }

        for (Produto produto : listaProdutos) {
            if (produto.getNomeProduto().equalsIgnoreCase(nome)) {
                return produto;
            }

            if (codigoBuscado != null && produto.getCodigoProduto() == codigoBuscado) {
                return produto;
            }

            String nomeCategoria = produto.getCategoriaProduto().getNomeCategoria();
            if (nomeCategoria.equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        return null;
    }

            private static void buscarProduto(){
                Produto produtoBuscado = new Produto();
                System.out.println("Digite o nome, código ou categoria do produto que deseja buscar:");
                String nomeProdutoBuscado = scanner.nextLine();
                produtoBuscado = obterProdutoGeral(nomeProdutoBuscado);
                if (produtoBuscado == null) {
                    System.out.println("Produto não encontrado");
                    return;
                }
                if (carrinho.verificarRepeticao(produtoBuscado)) {
                    System.out.println("Produto já adicionado ao carrinho");
                    buscarProduto();
                    return;
                }
                int quantidadeDisponivel = produtoBuscado.getQtdEstoque();
                if (quantidadeDisponivel == 0) {
                    System.out.println("Produto indisponivel");
                    return;
                }
                System.out.println("Há " + quantidadeDisponivel + " disponiveis no momento!" );
                System.out.println("Deseja adicionar ao carrinho? Digite (S/N)");
                String respostaAddCarrinho = scanner.nextLine();

                if (respostaAddCarrinho.equals("S")) {
                    System.out.println("Qual quantidade deseja adicionar?");
                    int quantidadeAddCarrinho = scanner.nextInt();
                    scanner.nextLine();
                    if (quantidadeAddCarrinho <= quantidadeDisponivel) {
                        carrinho.adicionarProduto(produtoBuscado, quantidadeAddCarrinho);
                    }
                }

                System.out.println("Deseja buscar outro produto ao carrinho? Digite (S/N)");
                String continuarCompra = scanner.nextLine();
                if (continuarCompra.equals("S")) {
                    buscarProduto();
                }
            }
        
            private static void removerDoCarrinho() {
                Produto produtoBuscado = lerProduto();
                carrinho.removerProduto(produtoBuscado);
                System.out.println("Produto removido com sucesso! ");
            }

            private static void realizarPedido(){
                clienteLogado.adicionarPedido(carrinho, autoIncremente);
                System.out.println("Pedido com ID " +autoIncremente+ " realizado com sucesso! ");
                autoIncremente++; // Incrementa o ID do próximo pedido
                diminuirEstoque();// Atualiza o estoque com base no pedido realizado
            }
            private static void diminuirEstoque() {
                // Atualiza a quantidade em estoque de cada produto no carrinho
                for (Produto produto : carrinho.retornarLista()) {
                    int valor = produto.getQtdEstoque()- produto.getQtdVendido();
                    alterarEstoque(produto,valor);
                }
            }

            private static void alterarEstoque(Produto produto, int valor){ 
                for (Produto p : listaProdutos) {
                    if (p.equals(produto)) {
                        p.setQtdEstoque(valor); // Atualiza a quantidade em estoque
                        return; // Sai do método após encontrar e atualizar o produto
                    }
                }
            }

            private static void gerenciarPedidos() {
                boolean escolha = true;
                while (escolha) {
                    System.out.println("1- Consultar um pedido"); // Consultar um pedido (exibir os produtos e o valor do pedido)
                    System.out.println("2- Listar todos os pedidos realizados");// Listar todos os pedidos de um cliente.
                    System.out.println("3- Voltar ao menu inicial");

                    int opcao = scanner.nextInt();
                    scanner.nextLine();
        
                    switch (opcao) {
                        case 1:
                            consultarPedido();
                            break;
                        case 2:
                            clienteLogado.exibirTodosPedidos();;
                            break;
                        case 3:  
                            escolha= false;
                            break;
                        default:
                            System.out.println("Opção não válida!");
                    }
                }
            }

            private static void consultarPedido(){
                // Solicita o ID do pedido para consulta
                System.out.println("Por favor insira o ID do pedido que deseja consultar: ");
                int idBuscado = scanner.nextInt();
                scanner.nextLine();
                Pedido pedidoBuscado = obterPedido(idBuscado);
                if (pedidoBuscado == null) {
                    System.out.println("Pedido inexistente!");
                }
                pedidoBuscado.verTodosProdutos();
                System.out.println("O valor total do pedido foi R$" + pedidoBuscado.getValorTotal() + "!!");
            }

            private static Pedido obterPedido(int ID) {
                listaPedidosRealizados = clienteLogado.retornarLista();
                for (Pedido pedido : listaPedidosRealizados) {
                    if (pedido.getIdPedido() == (ID)) {
                        return pedido;
                    }
                }
                return null;
            }
} 