public class Main {
    public static void main(String[] args) {
        Entrada entrada = Entrada.getInstance();
        Sistema sistema = new Sistema();
        int opcao;
        long idProduto;
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

        if (isCliente) {
            System.out.println("Bem-vindo, Cliente " + usuario.getNome() + "!");
            do {
                System.out.println("Menu:");
                System.out.println("1 - Ver produto");
                System.out.println("2 - Adicionar ao carrinho");
                System.out.println("3 - Finalizar compra");
                System.out.println("9 - Sair");
                opcao = entrada.leInt("Opção");
                switch (opcao) {
                    case 1:
                        idProduto = entrada.leLong("ID do produto que deseja visualizar");
                        sistema.visualizarProduto(idProduto);
                        break;
                    case 2:
                        idProduto = entrada.leLong("ID do produto");
                        int quant = entrada.leInt("Quantidade");
                        sistema.adicionarCarrinho(idProduto, quant);
                        break;
                    case 3:
                        sistema.finalizarCompra();
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
        } else {
            System.out.println("Bem-vindo, Anunciante " + usuario.getNome() + "!");
            System.out.println("Voce não tem sistema ainda :)");
            System.out.println("Saindo...");
        }
    }
}
