public class Categoria {

	private String nome;

	public Categoria(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	@Override
	public String toString() {
		return this.nome;
	}

}
