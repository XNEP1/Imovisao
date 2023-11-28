import java.util.ArrayList;

public interface ProdutoDAO {

    public ArrayList<Produto> listarProdutos();

    public Produto buscaProduto(long idProduto);

    public void cadastrarProduto(Produto produto);

    public void removerProduto(Produto produto);
}
