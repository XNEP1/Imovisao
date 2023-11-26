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

	public void verProduto(long idProduto) {
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
	}

	public void verProduto3d(long idProduto) {
		Produto prod = this.produtoDAO.buscaProduto(idProduto);
		if (prod == null) {
			System.out.println("Erro: Produto com ID " + idProduto + " não existe.");
			return;
		}
		Modelo3D mod = prod.getModelo3D();
		this.camera.verModelo3D(mod);
	}

	public Produto buscarProduto(long idProduto) {
		Produto prod = this.produtoDAO.buscaProduto(idProduto);
		if (prod == null) {
			return null;
		}
		return prod;
	}

}
