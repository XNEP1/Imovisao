import java.util.List;

public class Cliente extends Usuario {

	private Carrinho carrinho;

	private List<Produto> favoritos;

    public void ReportarAnuncio (int idProduto, int motivo, String outro, String comentario) {
        ProdutoDAO lp;
        Produto prod;
        Anunciante anunc;
        Denuncia den;
        
        try {
            lp = Sistema.getProdutoDAO();
            prod = lp.buscarProduto(idProduto);
            anunc = prod.getAnunciante();
            
            den = new Denuncia(prod, anunc, motivo);
            
            if (comentario != null) {
                den.setComentario(comentario);
            }
            
            if (outro != null) {
                den.setOutro(outro);
            }
            
            prod.registrarDenuncia(den);
        } catch (Exception e) {
        
        }
    }

}
