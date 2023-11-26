import java.util.List;

public class Compra {

	private String status;

	private long id;

	private Cliente cliente;

	private List<ItemCompra> itemCompra;

	public Compra(long id, Cliente cliente, List<ItemCompra> itemCompra) {
		this.id = id;
		this.cliente = cliente;
		this.itemCompra = itemCompra;
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

	public List<ItemCompra> getItemCompra() {
		return this.itemCompra;
	}

	public void finalizarCompra() {
		this.status = "Finalizado";
	}

	public void cancelarCompra() {
		this.status = "Cancelado";
	}

	public void pagarCompra() {
		this.status = "Aprovado";
	}

}
