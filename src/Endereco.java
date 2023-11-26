public class Endereco {

	private String logradouro;

	private int numero;

	private String complemento;

	private String bairro;

	private String cep;

	private String cidade;

	private String estado;

	public Endereco(String logradouro, int numero) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = "";
		this.bairro = "";
		this.cep = "";
		this.cidade = "";
		this.estado = "";
	}

}
