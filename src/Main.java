public class Main {
    public static void main(String[] args) {
        Entrada entrada = Entrada.getInstance();
        Sistema sistema = new Sistema();
        Cliente cliente = null;
        int opcao;
        System.out.println("+====================+");
        System.out.println("Bem-vindo ao Imovisão!");
        System.out.println("+====================+");
        {
            String nome, email, telefone, documento, endereco, senha1, senha2;
            nome = entrada.leString("Digite seu nome:");
            email = entrada.leString("Digite seu email:");
            telefone = entrada.leString("Digite seu telefone:");
            endereco = entrada.leString("Digite seu endereco:");
            documento = entrada.leString("Digite seu documento (RG/CPF):");
            do {
                senha1 = entrada.leString("Digite sua senha:");
                senha2 = entrada.leString("Confirme sua senha:");
            } while (senha1 == null || senha2 == null || !senha1.equals(senha2) );
            cliente = new Cliente(1, nome, email, telefone, senha1, documento,
			new Endereco(endereco, 0));
        }
        do {
            System.out.println("Menu:");
            System.out.println("1 - Ver produto");
            System.out.println("2 - Ver produto 3D");
            System.out.println("3 - Sair");
            opcao = entrada.leInt("Opção");
            switch (opcao) {
                case 1:
                long idProduto = entrada.leLong("ID do produto");
                sistema.verProduto(idProduto);
                break;
                case 2:
                idProduto = entrada.leLong("ID do produto");
                sistema.verProduto3d(idProduto);
                break;
                case 3:
                System.out.println("Saindo...");
                break;
                case 99:
                {
                    Produto prod;
                    int motivo;
                    String mensagem, outro;
                    idProduto = entrada.leLong("ID do produto");
                    motivo = entrada.leInt("Motivo da denuncia");
                    mensagem = entrada.leString("Mensagem da denuncia");
                    outro = entrada.leString("Outro comentario (opcional)");
                    prod = sistema.buscarProduto(idProduto);
                    if (outro == "") {
                        outro = null;
                    }
                    cliente.reportarAnuncio(prod, motivo, mensagem, outro);
                    System.out.println("Denuncia registrada com sucesso.");
                }
                break;
                default:
                System.out.println("Opcao invalida.");
                break;
            }
            System.out.println("-------------------");
        } while (opcao != 3);
    }
}
