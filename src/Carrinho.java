import java.util.ArrayList;
import java.util.List;

public class Carrinho {

	private List<ItemCompra> itens;

	public Carrinho() {
		this.itens = new ArrayList<>();
	}

	public List<ItemCompra> getItens() {
		return this.itens;
	}

	public void adicionaItemCompra(Produto produto, int quantidade) {
		ItemCompra itemCompra = new ItemCompra(quantidade, produto);
		this.itens.add(itemCompra);
	}

	public void removeItemCompra(ItemCompra itemCompra) {
		this.itens.remove(itemCompra);
	}

	public void esvaziaCarrinho() {
		this.itens.clear();
	}

	public void comprar(long id, Cliente cliente) {
		Compra compra = new Compra(id, cliente, this.itens);
		compra.pagarCompra();
	}
}
