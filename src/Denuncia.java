public class Denuncia {

	private String mensagem;
    private int motivo;
    private Produto prod;
    private String anunc;
    private String comentario = null;
    private String outro = null;

    public Denuncia (Produto prod, String anunc, int motivo) {
        this.prod = prod;
        this.anunc = anunc;
        this.motivo = motivo;
    }

    public Produto getProduto () {
        return this.prod;
    }

    public int getAnunciante () {
        return this.anunc;
    }

    public int getMotivo () {
        return this.motivo;
    }

    public String getComentario () {
        return this.comentario;
    }

    public String getOutro () {
        return this.outro;
    }

    public void setComentario (String comentario) {
        this.comentario = comentario;
    }

    public void setOutro (String outro) {
        this.outro = outro;
    }

}
