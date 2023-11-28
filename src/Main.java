/*
  _____                               _                       
 |_   _|                             (_)                      
   | |    _ __ ___     ___   __   __  _   ___    __ _    ___  
   | |   | '_ ` _ \   / _ \  \ \ / / | | / __|  / _` |  / _ \ 
  _| |_  | | | | | | | (_) |  \ V /  | | \__ \ | (_| | | (_) |
 |_____| |_| |_| |_|  \___/    \_/   |_| |___/  \__,_|  \___/ 


De
    Bruno Krügel
    Bruno Dal Pontte
    Henrique Colini
    Lucas Araujo
    Vitor Igami
    Victor Ribeiro Garcia

 */                                               

public class Main {
    public static void main(String[] args) {
        Entrada entrada = Entrada.getInstance();
        Sistema sistema = new Sistema();
        int opcao;
        long idProduto;

        // Imprime o txt com a logo do app
        // path: modelos/logo.txt
        Modelo3D logo = new Modelo3D("modelos/logo.txt", 1, 1, 1);
        System.out.println(logo.getRenderizacao());
        
        System.out.println("+====================+");
        System.out.println("Bem-vindo ao Imovisão!");
        System.out.println("+====================+");
        System.out.println();

        // Exigir login inicial
        System.out.println("Você precisa fazer login para continuar.");
        if (!sistema.garanteLogin()) {
            System.out.println("Erro: Você não fez login.");
            return;
        }
        System.out.println();
        Usuario usuario = sistema.getUsuarioLogado();

        // Define o tipo de usuario
        boolean isCliente = sistema.isLogadoCliente();

        if (isCliente) { // Menu do cliente
            System.out.println("Bem-vindo, Cliente " + usuario.getNome() + "!");
            do {
                System.out.println("Menu Cliente:");
                System.out.println("1 - Ver produto");
                System.out.println("2 - Adicionar ao carrinho");
                System.out.println("3 - Finalizar compra");
                System.out.println("4 - Ver minhas compras");
                System.out.println("5 - Favoritar produto");
                System.out.println("9 - Sair");
                opcao = entrada.leInt("Opção");
                switch (opcao) {
                    case 1:
                        System.out.println("Menu Ver Produto:");
                        System.out.println("1 - Ver todos os produtos");
                        System.out.println("2 - Ver produtos por ID");
                        System.out.println("3 - Ver produtos favoritos");
                        System.out.println("9 - Voltar");
                        opcao = entrada.leInt("Opção");
                        switch (opcao) {
                            case 1:
                                sistema.visualizarProdutos();
                                break;
                            case 2:
                                sistema.visualizarProduto(entrada.leLong("ID do produto"));
                                break;
                            case 3:
                                sistema.visualizarFavoritos();
                                break;
                            case 9:
                                opcao = 0;
                                break;
                            default:
                                System.out.println("Opcao invalida.");
                                break;
                        }
                        break;
                    case 2:
                        idProduto = entrada.leLong("ID do produto");
                        int quant = entrada.leInt("Quantidade");
                        sistema.adicionarCarrinho(idProduto, quant);
                        break;
                    case 3:
                        sistema.finalizarCompra();
                        break;
                    case 4:
                        int qntItens = sistema.visualizarCompras();
                        System.out.println("Menu Opções em Minhas Compras:");
                        System.out.println("1 - Enviar Feedback");
                        System.out.println("9 - Voltar");
                        opcao = entrada.leInt("Opção");
                        switch (opcao) {
                            case 1:
                                int compraSelecionado = entrada.leInt("Selecione uma compra");
                                if (compraSelecionado < 0 || compraSelecionado > qntItens) {
                                    System.out.println("Opcao invalida.");
                                    break;
                                }
                                String feedbackTexto = entrada.leString("Dê um texto de feedback");
                                sistema.enviarFeedback(compraSelecionado, feedbackTexto);
                                break;
                            case 9:
                                opcao = 0;
                                break;
                            default:
                                System.out.println("Opcao invalida.");
                                break;
                        }
                        break;
                    case 5:
                        idProduto = entrada.leLong("ID do produto");
                        sistema.favoritarProduto(idProduto);
                        break;
                    case 9:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opcao invalida.");
                        break;
                }
                System.out.println("-------------------");
            } while (opcao != 9);
        } else { // Menu do anunciante
            System.out.println("Bem-vindo, Anunciante " + usuario.getNome() + "!");
            do {
                System.out.println("Menu Anunciante:");
                System.out.println("1 - Ver produto");
                System.out.println("2 - Adicionar produto");
                System.out.println("3 - Editar produto");
                System.out.println("4 - Remover produto");
                System.out.println("9 - Sair");
                opcao = entrada.leInt("Opção");
                switch (opcao) {
                    case 1: // Ver produto
                        System.out.println("Menu Ver Produto:");
                        System.out.println("1 - Ver todos os produtos");
                        System.out.println("2 - Ver produtos por ID");
                        System.out.println("9 - Voltar");
                        opcao = entrada.leInt("Opção");
                        switch (opcao) {
                            case 1:
                                sistema.visualizarProdutos();
                                break;
                            case 2:
                                sistema.visualizarProduto(entrada.leLong("ID do produto"));
                                break;
                            case 9:
                                opcao = 0;
                                break;
                            default:
                                System.out.println("Opcao invalida.");
                                break;
                        }
                        break;
                    case 2: // Adicionar produto
                        sistema.adicionarProduto();
                        break;
                    case 3: // Editar produto
                        idProduto = entrada.leLong("ID do produto que deseja editar");
                        sistema.editarProduto(idProduto);
                        break;
                    case 4: // Remover produto
                        idProduto = entrada.leLong("ID do produto que deseja remover");
                        sistema.removerProduto(idProduto);
                        break;
                    case 9:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opcao invalida.");
                        break;
                }
                System.out.println("-------------------");
            } while (opcao != 9);
        }
    }
}
