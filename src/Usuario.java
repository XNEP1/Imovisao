public class Usuario {

	private long id;

	private String nome;

	private String email;

	private String telefone;

	private String senha;

	private String documento;

	private Endereco endereco;

	public Usuario(long id, String nome, String email, String telefone, String senha, String documento,
			Endereco endereco) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.documento = documento;
		this.endereco = endereco;
	}

	public long getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public String getEmail() {
		return this.email;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public String getSenha() {
		return this.senha;
	}

	public String getDocumento() {
		return this.documento;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

}
