public class Feedback {

    public final Produto produto;

    private final String texto;
    private boolean foiLido = false;

    public Feedback(Produto produto, String texto) {
        this.produto = produto;
        this.texto = texto;
    }

    public String ler() {
        this.foiLido = true;
        return this.texto;
    }

    public boolean foiLido() {
        return this.foiLido;
    }

}