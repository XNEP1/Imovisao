public class Sistema {

    private Camera camera;

    private ProdutoDAO produtoDAO;

    private UsuarioDAO usuarioDAO;

    private CompraDAO compraDAO;

    public Sistema() {
        this.camera = new Camera();
        this.usuarioDAO = new TxtUsuarioDAO();
        this.produtoDAO = new TxtProdutoDAO(this.usuarioDAO);
        this.compraDAO = new TxtCompraDAO();
    }

    public void listarProdutos() {

    }

    public void inserirProduto(String nome, double preco, String descricao, Categoria categoria, Modelo3D modelo3d,
            Anunciante anunciante) {
        Produto prod = new Produto(nome, preco, descricao, categoria, modelo3d, anunciante);
        this.produtoDAO.cadastrarProduto(prod);
        System.out.println("Produto inserido com sucesso!");
    }

    public void inserirProduto(Produto prod) {
        if (prod == null) {
            System.out.println("Erro: Produto não pode ser nulo.");
            return;
        }
        this.produtoDAO.cadastrarProduto(prod);
        System.out.println("Produto inserido com sucesso!");
    }

    public void editarProduto(int id, Produto newProduto) {
        Produto prod = this.produtoDAO.buscaProduto(id);
        if (prod == null) {
            System.out.println("Erro: Produto com ID " + id + " não existe.");
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

    public void excluirProduto(int id) {
        Produto prod = this.produtoDAO.buscaProduto(id);
        if (prod == null) {
            System.out.println("Erro: Produto com ID " + id + " não existe.");
            return;
        }
        this.produtoDAO.removerProduto(prod);
        System.out.println("Produto removido com sucesso!");
    }

    public void visualizarProduto(int id) {
        Produto prod = this.produtoDAO.buscaProduto(id);
        if (prod == null) {
            System.out.println("Erro: Produto com ID " + id + " não existe.");
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
    }

    public void visualizarProduto3D(int id) {
        Produto prod = this.produtoDAO.buscaProduto(id);
        if (prod == null) {
            System.out.println("Erro: Produto com ID " + id + " não existe.");
            return;
        }
        Modelo3D mod = prod.getModelo3D();
        this.camera.verModelo3D(mod);
    }
}
