import java.util.List;

public class Compra {

	private String status;

	private long id;

	private Cliente cliente;

	private List<ItemCompra> itens;

	public Compra(long id, Cliente cliente, List<ItemCompra> itens) {
		this.id = id;
		this.cliente = cliente;
		this.itens = itens;
		this.status = "Em andamento";
	}

	public String getStatus() {
		return this.status;
	}

	public long getId() {
		return this.id;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public List<ItemCompra> getItens() {
		return this.itens;
	}

	public void entregarCompra() {
		this.status = "Entregue";
	}

	public void cancelarCompra() {
		this.status = "Cancelado";
	}

	public void pagarCompra() {
		this.status = "Aprovado";
	}

}
