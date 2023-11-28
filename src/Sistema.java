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
        System.out.println("Login realizado com sucesso!");
    }

    public boolean garanteLogin() {
        int cont = 1;
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

        for (ItemCompra item : usuarioLogado.getCarrinho().getItens()) {
            usuarioLogado.registrarCompra(item);
        }

        this.compraDAO.cadastrarCompra(compra);
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
        System.out.println("Produto adicionado ao carrinho.");
    }

    public void inserirProduto(String nome, double preco, String descricao, Categoria categoria, Modelo3D modelo3d,
            Anunciante anunciante) {
        if (isLogadoCliente()) {
            System.out.println("Erro: Apenas anunciantes podem inserir produtos.");
            return;
        }

        Produto prod = new Produto(nome, preco, descricao, categoria, modelo3d, anunciante);
        this.produtoDAO.cadastrarProduto(prod);
        System.out.println("Produto inserido com sucesso!");
    }

    public void inserirProduto(Produto prod) {
        if (isLogadoCliente()) {
            System.out.println("Erro: Apenas anunciantes podem inserir produtos.");
            return;
        }

        if (prod == null) {
            System.out.println("Erro: Produto não pode ser nulo.");
            return;
        }
        this.produtoDAO.cadastrarProduto(prod);
        System.out.println("Produto inserido com sucesso!");
    }

    public void editarProduto(long idProduto, Produto newProduto) {
        if (isLogadoCliente()) {
            System.out.println("Erro: Apenas anunciantes podem editar produtos.");
            return;
        }

        Produto prod = this.produtoDAO.buscaProduto(idProduto);
        if (prod == null) {
            System.out.println("Erro: Produto com ID " + idProduto + " não existe.");
            return;
        }

        prod.setNome(newProduto.getNome());
        prod.setPreco(newProduto.getPreco());
        prod.setDescricao(newProduto.getDescricao());
        prod.setCategoria(newProduto.getCategoria());
        prod.setModelo3D(newProduto.getModelo3D());
        prod.setAnunciante(newProduto.getAnunciante());

        System.out.println("Produto editado com sucesso!");
    }

    public void excluirProduto(long idProduto) {
        if (isLogadoCliente()) {
            System.out.println("Erro: Apenas anunciantes podem excluir produtos.");
            return;
        }

        Produto prod = this.produtoDAO.buscaProduto(idProduto);
        if (prod == null) {
            System.out.println("Erro: Produto com ID " + idProduto + " não existe.");
            return;
        }

        this.produtoDAO.removerProduto(prod);
        System.out.println("Produto removido com sucesso!");
    }

    public void visualizarProduto(long idProduto) {
        Entrada entrada = Entrada.getInstance();
        Produto prod = this.produtoDAO.buscaProduto(idProduto);
        if (prod == null) {
            System.out.println("Erro: Produto com ID " + idProduto + " não existe.");
            return;
        }
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
