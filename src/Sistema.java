public class Sistema {

	private Camera camera;

	private ProdutoDAO produtoDAO;

	private UsuarioDAO usuarioDAO;

	private CompraDAO compraDAO;

	public Sistema () {
		this.camera = new Camera();
		this.usuarioDAO = new TxtUsuarioDAO();
		this.produtoDAO = new TxtProdutoDAO(this.usuarioDAO);
		this.compraDAO = new TxtCompraDAO();
	}

	public void listarProdutos () {

	}

	public void verProduto (long idProduto) {

	}

}
