import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

	private Carrinho carrinho;

	private List<Produto> favoritos;

	public Cliente(long id, String nome, String email, String telefone, String senha, String documento,
			Endereco endereco) {
		super(id, nome, email, telefone, senha, documento, endereco);
		this.carrinho = new Carrinho();
		this.favoritos = new ArrayList<Produto>();
	}

    public void ReportarAnuncio (Produto prod, int motivo, String outro, String comentario) {
        Anunciante anunc;
        Denuncia den;
        
        anunc = prod.getAnunciante();
        
        den = new Denuncia(prod, anunc, motivo);
        
        if (comentario != null) {
            den.setComentario(comentario);
        }
        
        if (outro != null) {
            den.setOutro(outro);
        }
        
        prod.registrarDenuncia(den);
    }

}
