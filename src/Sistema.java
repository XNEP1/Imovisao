import java.util.List;

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

        String id_string = entrada.leString("Email ou ID do Usuario");
        long id_long;
        Usuario usuarioPotencial;
        try {
            id_long = Integer.parseInt(id_string);
            usuarioPotencial = this.usuarioDAO.buscaUsuario(id_long);
        } catch (NumberFormatException e) {
            usuarioPotencial = this.usuarioDAO.buscaUsuario(id_string);
        }

        String senha = entrada.leString("Senha");

        if (usuarioPotencial == null) {
            System.out.println("Erro: Usuário com ID " + id_string + " não existe.");
            return;
        } else if (!usuarioPotencial.getSenha().equals(senha)) {
            System.out.println("Erro: Senha incorreta.");
            return;
        }
        this.usuarioLogado = usuarioPotencial;
        System.out.println("Login realizado com sucesso!");
    }

    public boolean garanteLogin() {
        if (this.usuarioLogado != null) {
            return true;
        }

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

        if(usuarioLogado.getCarrinho().getItens().size() == 0){
            System.out.println("Erro: Você não pode finalizar uma compra com o carrinho vazio.");
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

    public void adicionarProduto() {
        if (isLogadoCliente()) {
            System.out.println("Erro: Apenas anunciantes podem inserir produtos.");
            return;
        }

        // Ler informacoes do produto
        Entrada entrada = Entrada.getInstance();
        String nome = entrada.leString("Nome");
        double preco;
        do {
            preco = entrada.leDouble("Preço");

        } while (preco <= 0);
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
        Anunciante anunciante = (Anunciante) this.usuarioLogado;
        if (prod.getAnunciante().getId() != anunciante.getId()) {
            System.out.println("Erro: Você não pode editar um produto que não é seu.");
            return;
        }

        // Ler informacoes do produto
        Entrada entrada = Entrada.getInstance();

        System.out.println("Digite as novas informações do produto:");
        System.out.println("Deixe em branco para manter o valor atual.");
        System.out.println("Nome atual: " + prod.getNome());
        String nome = entrada.leString("Nome");
        if (nome.equals("")) {
            nome = prod.getNome();
        }

        System.out.println("Preço atual: " + prod.getPreco());
        double preco = entrada.leDouble("Preço");
        if (preco == 0) {
            preco = prod.getPreco();
        }

        System.out.println("Descrição atual: " + prod.getDescricao());
        String descricao = entrada.leString("Descrição");
        if (descricao.equals("")) {
            descricao = prod.getDescricao();
        }

        System.out.println("Categoria atual: " + prod.getCategoria().getNome());
        String strCategoria = entrada.leString("Categoria");
        Categoria categoria = prod.getCategoria();
        if (!strCategoria.equals("")) {
            categoria = new Categoria(strCategoria);
        }

        System.out.println("Modelo 3D atual: " + prod.getModelo3D().getArquivoModelo());
        String strModelo = entrada.leString("Modelo");
        Modelo3D modelo = prod.getModelo3D();
        if (!strModelo.equals("")) {
            modelo = new Modelo3D(strModelo, 1, 1, 1);
        }

        prod.setNome(nome);
        prod.setPreco(preco);
        prod.setDescricao(descricao);
        prod.setCategoria(categoria);
        prod.setModelo3D(modelo);

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
        Produto prod = this.produtoDAO.buscaProduto(idProduto);
        if (prod == null) {
            System.out.println("Erro: Produto com ID " + idProduto + " não existe.");
            return;
        }
        System.out.println(prod.getVisualizacao());
        System.out.println("Deseja visualizar o produto em 3D?");
        if (Entrada.getInstance().leBoolean("Opção")) {
            Modelo3D mod = prod.getModelo3D();
            this.camera.verModelo3D(mod);
        }
    }

    public void visualizarProdutos() {

        List<Produto> prods = this.produtoDAO.listarProdutos();
        Entrada entrada = Entrada.getInstance();
        int index = 0;
        int start = 0;
        int num = 5;

        while (true) {
            for (int j = start; j < start + num && j < prods.size(); j++) {
                Produto prod = prods.get(j);
                System.out.println(prod.getVisualizacao());
                index++;
            }
            System.out.printf("\n  Páginas: %d - ", start / num + 1);
            for (int k = 1; k <= prods.size() / num + 1; k++)
                System.out.printf("Atual: %d ", k);
            System.out.printf("\n");

            System.out.printf("------------Menu------------\n");
            System.out.printf(" 1 - Próxima página\n");
            System.out.printf(" 2 - Mudar página\n");
            if (this.isLogadoCliente())
                System.out.printf(" 3 - Favoritar ou desfavoritar produto\n");
            System.out.printf(" 9 - Voltar\n");
            int opcao = entrada.leInt("Opção");
            switch (opcao) {
                case 1:
                    if (start + num < prods.size())
                        start += num;

                    break;
                case 2:
                    start = entrada.leInt("Página");
                    start--;
                    System.out.println(start + " " + prods.size());
                    if (start > prods.size() / num)
                        start = prods.size() / num;
                    else if (start < 0)
                        start = 0;

                    start *= num;

                    break;
                case 3:
                    if (!this.isLogadoCliente()) {
                        System.out.println("Opção inválida.");
                        return;
                    }

                    Cliente cliente = (Cliente) this.usuarioLogado;
                    long idProduto = entrada.leLong("ID do produto");
                    Produto prodFavorito = produtoDAO.buscaProduto(idProduto);
                    if (prodFavorito == null) {
                        System.out.println("Produto " + idProduto + " não existe.");
                        return;
                    } else {
                        cliente.favoritarProduto(prodFavorito);
                        System.out.println("Produto " + index + " favoritado!");
                    }
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Opção inválida.");
                    return;
            }
            index = 0;
        }

    }

    public boolean favoritarProduto(long idProduto) {
        if (!isLogadoCliente()) {
            System.out.println("Erro: Apenas clientes podem favoritar produtos.");
            return false;
        }

        Cliente cliente = (Cliente) this.usuarioLogado;

        Produto prod = this.produtoDAO.buscaProduto(idProduto);
        if (prod == null) {
            System.out.println("Erro: Produto com ID " + idProduto + " não existe.");
            return false;
        }

        cliente.favoritarProduto(prod);
        System.out.println("Produto favoritado!");
        return true;
    }

    public void visualizarFavoritos() {
        if (!isLogadoCliente()) {
            System.out.println("Erro: Apenas clientes podem visualizar seus favoritos.");
            return;
        }

        Cliente cliente = (Cliente) this.usuarioLogado;

        System.out.println("-------------------");
        System.out.println("Produtos Favoritos:");
        for (Produto produto : cliente.getFavoritos()) {
            System.out.println(produto.getVisualizacao());
        }
    }

    public int visualizarCompras() {
        if (!isLogadoCliente()) {
            System.out.println("Erro: Apenas clientes podem adicionar produtos ao carrinho.");
            return 0;
        }

        Cliente cliente = (Cliente) this.usuarioLogado;
        int i = 0;

        System.out.println("-------------------");
        System.out.println("Produtos Comprados:");
        for (ItemCompra item : cliente.getItensComprados()) {
            System.out.println("Compra " + i);
            System.out.println("Quantidade: " + item.getQuantidade());
            System.out.println("Produto:\n" + item.getProduto().getVisualizacao());
            System.out.println();
            i += 1;
        }

        return i;
    }

    public void enviarFeedback(int indiceProdutoComprado, String texto) {
        if (!isLogadoCliente()) {
            System.out.println("Erro: Apenas clientes podem adicionar produtos ao carrinho.");
            return;
        }

        Cliente cliente = (Cliente) this.usuarioLogado;

        ItemCompra item = cliente.getItensComprados().get(indiceProdutoComprado);
        cliente.enviarFeedback(item.getProduto(), texto);
    }

}
