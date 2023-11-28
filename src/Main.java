public class Main {
    public static void main(String[] args) {
        Entrada entrada = Entrada.getInstance();
        Sistema sistema = new Sistema();
        int opcao;
        long idProduto;
        System.out.println("+====================+");
        System.out.println("Bem-vindo ao Imovisão!");
        System.out.println("+====================+");
        do {
            System.out.println("Menu:");
            System.out.println("1 - Ver produto");
            System.out.println("2 - Ver produto 3D");
            System.out.println("3 - Sair");
            opcao = entrada.leInt("Opção");
            switch (opcao) {
                case 1:
                    idProduto = entrada.leLong("ID do produto");
                    sistema.visualizarProduto(idProduto);
                    break;
                case 2:
                    idProduto = entrada.leLong("ID do produto");
                    sistema.visualizarProduto3D(idProduto);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
            System.out.println("-------------------");
        } while (opcao != 3);
    }
}
