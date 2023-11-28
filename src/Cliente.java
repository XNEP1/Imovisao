import java.util.ArrayList;
import java.util.List;
import java.security.InvalidParameterException;

public class Cliente extends Usuario {

    private Carrinho carrinho;

    private List<ItemCompra> comprados;
    private List<Produto> favoritos;

    public Cliente(long id, String nome, String email, String telefone, String senha, String documento,
            Endereco endereco) {
        super(id, nome, email, telefone, senha, documento, endereco);
        this.carrinho = new Carrinho();
        this.comprados = new ArrayList<ItemCompra>();
        this.favoritos = new ArrayList<Produto>();
    }

    public void reportarAnuncio(Produto prod, int motivo, String mensagem, String outro) {
        Anunciante anunc;
        Denuncia den;

        anunc = prod.getAnunciante();

        den = new Denuncia(prod, anunc, motivo);

        if (mensagem != null) {
            den.setMensagem(mensagem);
        }

        if (outro != null) {
            den.setOutro(outro);
        }

        prod.registrarDenuncia(den);
    }

    public void enviarFeedback(Produto prod, String texto) {

        if (texto == null)
            throw new InvalidParameterException("Um feedback não pode ter uma mensagem nula.");

        Feedback feedback = new Feedback(prod, texto);
        Anunciante anunc = prod.getAnunciante();
        anunc.registrarFeedback(feedback);
    }

    public void registrarCompra(ItemCompra item) {
        this.comprados.add(item);
    }

    public List<ItemCompra> getItensComprados() {
        return this.comprados;
    }

    public void favoritarProduto(Produto prod) {
        if (prod == null) {
            System.out.println("Erro: Produto não pode ser nulo.");
            return;
        }

        if(this.favoritos.contains(prod)){
            return;
        }

        this.favoritos.add(prod);
    }

    public void desfavoritarProduto(Produto prod) {
        if (prod == null) {
            System.out.println("Erro: Produto não pode ser nulo.");
            return;
        }

        if(!this.favoritos.contains(prod)){
            return;
        }

        this.favoritos.remove(prod);
    }

    public Carrinho getCarrinho() {
        return this.carrinho;
    }

    // busca carrinho cliente pelo id
    public Carrinho getCarrinhoId(long id) {
        if (this.getId() == id) {
            return this.carrinho;
        }
        return null;
    }

    public List<Produto> getFavoritos() {
        return this.favoritos;
    }

}
