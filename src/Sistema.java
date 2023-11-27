import java.util.Scanner;

public class Sistema {

	private Camera camera;

	private ProdutoDAO produtoDAO;

	private UsuarioDAO usuarioDAO;

	private CompraDAO compraDAO;

	private Cliente clienteLogado;

	public Sistema() {
		this.camera = new Camera();
		this.usuarioDAO = new TxtUsuarioDAO();
		this.produtoDAO = new TxtProdutoDAO(this.usuarioDAO);
		this.compraDAO = new TxtCompraDAO(this.usuarioDAO);
	}

	public void listarProdutos() {

	}

	public Usuario fazerLogin() {
		Scanner leitor = new Scanner(System.in);
		System.out.println("Digite seu id:");
		long id = leitor.nextLong();
		Usuario usuario = this.usuarioDAO.buscaUsuario(id);
		System.out.println("Digite sua senha:");
		String senha = leitor.next();
		leitor.close();
		if (usuario == null) {
			System.out.println("Erro: Usuário com ID " + id + " não existe.");
			return null;
		} else if (!usuario.getSenha().equals(senha)) {
			System.out.println("Erro: Senha incorreta.");
			return null;
		} else {
			return usuario;
		}
	}

	public void comprar() {
		while (clienteLogado == null) {
			fazerLogin();
		}
		Compra compra = new Compra(0, clienteLogado, clienteLogado.getCarrinho().getItemCompra());
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

}
