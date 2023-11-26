public class Anunciante extends Usuario {

	private int avalicao;

	public Anunciante(long id, String nome, String email, String telefone, String senha, String documento,
			Endereco endereco, int avaliacao) {
		super(id, nome, email, telefone, senha, documento, endereco);
		this.avalicao = avaliacao;
	}

}
