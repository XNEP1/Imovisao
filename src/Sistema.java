import java.util.Scanner;

public class Sistema {

    private Camera camera;

    private ProdutoDAO produtoDAO;

    private UsuarioDAO usuarioDAO;

    private CompraDAO compraDAO;

    private Usuario usuarioLogado;

    public Sistema() {
        this.camera = new Camera();
        this.usuarioDAO = new TxtUsuarioDAO();
        this.produtoDAO = new TxtProdutoDAO(this.usuarioDAO);
        this.compraDAO = new TxtCompraDAO(this.usuarioDAO);
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void listarProdutos() {

    }

    private void fazerLogin() {
        if (this.usuarioLogado != null) {
            System.out.println("Erro: Já existe um usuário logado.");
            return;
        }

        Entrada entrada = Entrada.getInstance();

        System.out.println("Digite seu id:");
        long id = entrada.leLong("ID");

        System.out.println("Digite sua senha:");
        String senha = entrada.leString("Senha");

        Usuario usuarioPotencial = this.usuarioDAO.buscaUsuario(id);
        if (usuarioPotencial == null) {
            System.out.println("Erro: Usuário com ID " + id + " não existe.");
            return;
        } else if (!usuarioPotencial.getSenha().equals(senha)) {
            System.out.println("Erro: Senha incorreta.");
            return;
        }
        this.usuarioLogado = usuarioPotencial;
        System.out.println("-------------------");
        System.out.println("Login realizado com sucesso!");
    }

    public boolean garanteLogin() {
        int cont = 0;
        while (true) {
            fazerLogin();

            if (this.usuarioLogado != null) {
                return true;
            }

            cont++;
            if (cont == 3) {
                System.out.println("Erro: Você excedeu o número de tentativas de login.");
                break;
            }
        }
        return false;
    }

    public boolean isLogadoCliente() {
        if (this.usuarioLogado instanceof Cliente) {
            return true;
        }
        return false;
    }

    public void finalizarCompra() {
        if (!isLogadoCliente()) {
            System.out.println("Erro: Apenas clientes podem finalizar compras.");
            return;
        }

        Cliente usuarioLogado = (Cliente) this.usuarioLogado;

        if (!garanteLogin()) {
            return;
        }

        long idCompra = this.compraDAO.getIdUnico();
        Compra compra = new Compra(idCompra, usuarioLogado, usuarioLogado.getCarrinho().getItens());

        this.compraDAO.cadastrarCompra(compra);
        System.out.println("-------------------");
        System.out.println("Compra #" + idCompra + " realizada com sucesso!");
    }

    public void adicionarCarrinho(long idProduto, int quant) {
        if (!isLogadoCliente()) {
            System.out.println("Erro: Apenas clientes podem adicionar produtos ao carrinho.");
            return;
        }

        if (!garanteLogin()) {
            return;
        }

        Produto prod = this.produtoDAO.buscaProduto(idProduto);
        if (prod == null) {
            System.out.println("Erro: Produto com ID " + idProduto + " não existe.");
            return;
        }

        if (quant <= 0) {
            System.out.println("Erro: Quantidade inválida.");
            return;
        }

        Cliente cliente = (Cliente) this.usuarioLogado;
        cliente.getCarrinho().adicionaItemCompra(prod, quant);
        System.out.println("-------------------");
        System.out.println("Produto adicionado ao carrinho.");
    }

    public void adicionarProduto() {
        if (isLogadoCliente()) {
            System.out.println("Erro: Apenas anunciantes podem inserir produtos.");
            return;
        }

        // Ler informacoes do produto
        Entrada entrada = Entrada.getInstance();
        String nome = entrada.leString("Nome");
        double preco = entrada.leDouble("Preço");
        String descricao = entrada.leString("Descrição");
        String strCategoria = entrada.leString("Categoria");
        Categoria categoria = new Categoria(strCategoria);

        Modelo3D modelo3d = null;
        Anunciante anunciante = (Anunciante) this.usuarioLogado;

        Produto prod = new Produto(this.produtoDAO.getIdUnico(), preco, nome, descricao, 0, categoria, modelo3d,
            anunciante);
        this.produtoDAO.cadastrarProduto(prod);
        
        // Exibe ID e nome do produto
        System.out.println("-------------------");
        System.out.println("Produto inserido com sucesso!");
        System.out.println("ID: " + prod.getId());
        System.out.println("Nome: " + prod.getNome());
    }

    public void editarProduto(long idProduto) {
        if (isLogadoCliente()) {
            System.out.println("Erro: Apenas anunciantes podem editar produtos.");
            return;
        }

        // Verifica se o produto existe
        Produto prod = this.produtoDAO.buscaProduto(idProduto);
        if (prod == null) {
            System.out.println("Erro: Produto com ID " + idProduto + " não existe.");
            return;
        }

        // Verifica se o produto pertence ao anunciante logado
        Anunciante Anunciante = (Anunciante) this.usuarioLogado;
        if (prod.getAnunciante().getId() != Anunciante.getId()) {
            System.out.println("Erro: Você não pode editar um produto que não é seu.");
            return;
        }

        // Ler informacoes do produto
        Entrada entrada = Entrada.getInstance();

        System.out.println("Digite as novas informações do produto:");
        System.out.println("Deixe em branco para manter o valor atual.");
        System.out.println("Nome atual: " + prod.getNome());
        String Nome = entrada.leString("Nome");
        if (Nome.equals("")) {
            Nome = prod.getNome();
        }

        System.out.println("Preço atual: " + prod.getPreco());
        double Preco = entrada.leDouble("Preço");
        if (Preco == 0) {
            Preco = prod.getPreco();
        }

        System.out.println("Descrição atual: " + prod.getDescricao());
        String Descricao = entrada.leString("Descrição");
        if (Descricao.equals("")) {
            Descricao = prod.getDescricao();
        }

        System.out.println("Categoria atual: " + prod.getCategoria().getNome());
        String strCategoria = entrada.leString("Categoria");
        Categoria Categoria = prod.getCategoria();
        if (!strCategoria.equals("")) {
            Categoria = new Categoria(strCategoria);
        }

        Modelo3D Modelo3D = prod.getModelo3D();
        System.out.println("Deseja alterar o modelo 3D?");
        if (entrada.leBoolean("Opção")) {
            System.out.println("Digite o caminho para o novo modelo 3D:");
            String caminho = entrada.leString("Caminho");
            // vai ficar querendo mudar
            System.out.println("Erro: Não implementado.");
        }

        prod.setNome(Nome);
        prod.setPreco(Preco);
        prod.setDescricao(Descricao);
        prod.setCategoria(Categoria);
        prod.setModelo3D(Modelo3D);

        System.out.println("-------------------");
        System.out.println("Produto editado com sucesso!");
    }

    public void removerProduto(long idProduto) {
        if (isLogadoCliente()) {
            System.out.println("Erro: Apenas anunciantes podem excluir produtos.");
            return;
        }

        Produto prod = this.produtoDAO.buscaProduto(idProduto);
        if (prod == null) {
            System.out.println("Erro: Produto com ID " + idProduto + " não existe.");
            return;
        }

        // Verifica se o produto pertence ao anunciante logado
        Anunciante Anunciante = (Anunciante) this.usuarioLogado;
        if (prod.getAnunciante().getId() != Anunciante.getId()) {
            System.out.println("Erro: Você não pode excluir um produto que não é seu.");
            return;
        }

        this.produtoDAO.removerProduto(prod);

        System.out.println("-------------------");
        System.out.println("Produto removido com sucesso!");
    }

    public void visualizarProduto(long idProduto) {
        Entrada entrada = Entrada.getInstance();
        Produto prod = this.produtoDAO.buscaProduto(idProduto);
        if (prod == null) {
            System.out.println("Erro: Produto com ID " + idProduto + " não existe.");
            return;
        }
        System.out.println("-------------------");
        System.out.println(prod.getNome() + " #" + prod.getId() + "(" + prod.getCategoria() + ")");
        System.out.println("R$ " + prod.getPreco());
        System.out.println(prod.getDescricao());
        System.out.println("Avaliação: " + prod.getAvaliacao());
        System.out.println("Anunciante: " + prod.getAnunciante().getNome());
        if (prod.getDenuncias().size() > 0) {
            System.out.println("Denúncias:");
            for (Denuncia denuncia : prod.getDenuncias()) {
                System.out.println("\t" + denuncia.getMensagem());
            }
        }
        System.out.println("Deseja visualizar o produto em 3D?");
        if (entrada.leBoolean("Opção")) {
            Modelo3D mod = prod.getModelo3D();
            this.camera.verModelo3D(mod);
        }
    }
}
