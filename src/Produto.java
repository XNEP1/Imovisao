import java.util.ArrayList;
import java.util.List;

public class Produto {

	private String nome;

	private long id;

	private double preco;

	private String descricao;

	private int avaliacao;

	private Categoria categoria;

	private Modelo3D modelo3D;

	private Anunciante anunciante;

	private List<Denuncia> denuncia;

	public Produto(long id, double preco, String nome, String descricao, int avaliacao, Categoria categoria,
			Modelo3D modelo3d, Anunciante anunciante) {
		this.nome = nome;
		this.id = id;
		this.preco = preco;
		this.descricao = descricao;
		this.avaliacao = avaliacao;
		this.categoria = categoria;
		this.modelo3D = modelo3d;
		this.anunciante = anunciante;
		this.denuncia = new ArrayList<Denuncia>();
	}

	public String getNome() {
		return this.nome;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public Modelo3D getModelo3D() {
		return this.modelo3D;
	}

    public long getId() {
        return this.id;
    }

}
