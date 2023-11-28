public class Denuncia {

    private int motivo;
	private String mensagem;
    private Produto prod;
    private Anunciante anunc;
    private String outro = null;

    public Denuncia (Produto prod, Anunciante anunc, int motivo) {
        this.prod = prod;
        this.anunc = anunc;
        this.motivo = motivo;
    }

	public Denuncia(String mensagem) {
		this.mensagem = mensagem;
	}

    public Produto getProduto () {
        return this.prod;
    }

    public Anunciante getAnunciante () {
        return this.anunc;
    }

    public int getMotivo () {
        return this.motivo;
    }

    public String getMensagem () {
        return this.mensagem;
    }

    public String getOutro () {
        return this.outro;
    }

    public void setMensagem (String mensagem) {
        this.mensagem = mensagem;
    }

    public void setOutro (String outro) {
        this.outro = outro;
    }

}
