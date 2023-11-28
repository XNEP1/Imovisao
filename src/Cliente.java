import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

	private Carrinho carrinho;

	private List<Produto> favoritos;

	public Cliente(long id, String nome, String email, String telefone, String senha, String documento,
			Endereco endereco) {
		super(id, nome, email, telefone, senha, documento, endereco);
		this.carrinho = new Carrinho();
		this.favoritos = new ArrayList<Produto>();
	}

	public Carrinho getCarrinho() {
		return this.carrinho;
	}

	// busca carrinho cliente pelo id
	public Carrinho getCarrinhoId(long id) {
		if (this.getId() == id) {
			return this.carrinho;
		}
		return null;
	}

	public List<Produto> getFavoritos() {
		return this.favoritos;
	}

}
