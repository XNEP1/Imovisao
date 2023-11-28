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

	public void fazerLogin() {
		Entrada entrada = Entrada.getInstance();
		System.out.println("Digite seu id:");
		long id = entrada.leLong("ID");
		System.out.println("Digite sua senha:");
		String senha = entrada.leString("Senha");
		Cliente clientePotencial = this.usuarioDAO.buscaCliente(id);
		this.clienteLogado = null;
		if (clientePotencial == null) {
			System.out.println("Erro: Usuário com ID " + id + " não existe.");
			return;
		} else if (!clientePotencial.getSenha().equals(senha)) {
			System.out.println("Erro: Senha incorreta.");
			return;
		}
		this.clienteLogado = clientePotencial;
		System.out.println("Login realizado com sucesso!");
	}

	private boolean garanteLogin() {
		int cont = 0;
		while (clienteLogado == null) {
			fazerLogin();
			cont++;
			if (cont > 3) {
				System.out.println("Erro: Você excedeu o número de tentativas de login.");
				return false;
			}
		}
		return true;
	}

	public void verProduto(long idProduto) {
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

	public void finalizarCompra() {
		if (!garanteLogin()) {
			return;
		}
		long id = this.compraDAO.getIdUnico();
		Compra compra = new Compra(id, clienteLogado, clienteLogado.getCarrinho().getItens());
		this.compraDAO.cadastrarCompra(compra);
		System.out.println("Compra #" + id + " realizada com sucesso!");
	}

	public void adicionarCarrinho(long idProduto, int quant) {
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
		clienteLogado.getCarrinho().adicionaItemCompra(prod, quant);
		System.out.println("Produto adicionado ao carrinho.");
	}

}
