import java.util.List;

public class Carrinho {

	private List<ItemCompra> itemCompra;

	public Carrinho() {
	}

	public Carrinho(List<ItemCompra> itemCompra) {
		this.itemCompra = itemCompra;
	}

	public List<ItemCompra> getItemCompra() {
		return this.itemCompra;
	}

	public void adicionaCarrinho(Produto produto, int quantidade) {
		ItemCompra itemCompra = new ItemCompra(quantidade, produto);
		this.itemCompra.add(itemCompra);
	}

	public void removeItemCompra(ItemCompra itemCompra) {
		this.itemCompra.remove(itemCompra);
	}

	public void esvaziaCarrinho() {
		this.itemCompra.clear();
	}

	public void comprar(long id, Cliente cliente) {
		Compra compra = new Compra(id, cliente, this.itemCompra);
		compra.pagarCompra();
	}
}
