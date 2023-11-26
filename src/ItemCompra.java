public class ItemCompra {

	private int quantidade;

	private Produto produto;

	public ItemCompra(int quantidade, Produto produto) {
		this.quantidade = quantidade;
		this.produto = produto;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
