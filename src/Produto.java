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



    public void registrarDenuncia (Denuncia novaDenuncia) {
        this.denuncia.add(novaDenuncia);
    }

}
