public class Main {
    public static void main(String[] args) {
        Entrada entrada = Entrada.getInstance();
        Sistema sistema = new Sistema();
        int opcao;
        System.out.println("+====================+");
        System.out.println("Bem-vindo ao Imovisão!");
        System.out.println("+====================+");
        do {
            System.out.println("Menu:");
            System.out.println("0 - Fazer Login");
            System.out.println("1 - Ver produto");
            System.out.println("2 - Adicionar ao carrinho");
            System.out.println("3 - Finalizar compra");
            System.out.println("9 - Sair");
            opcao = entrada.leInt("Opção");
            switch (opcao) {
                case 0:
                    sistema.fazerLogin();
                    break;
                case 1:
                    long idProduto = entrada.leLong("ID do produto");
                    sistema.verProduto(idProduto);
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
    }
}
