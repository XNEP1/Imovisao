public class Feedback {

    public final Produto produto;

    private final String texto;
    private boolean foiLido = false;
    private long id;

    static long id_counter = 0;

    public Feedback(Produto produto, String texto) {
        this.id = id_counter++;
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

    public long getId(){
        return this.id;
    }

}