import java.util.ArrayList;

public class TxtProdutoDAO implements ProdutoDAO {

    private ArrayList<Produto> produtos;

    public TxtProdutoDAO(UsuarioDAO usuarioDAO) {
        this.produtos = new ArrayList<Produto>();
        cadastrarProduto(new Produto(0,
                10, "Exemplo 1", "Um produto", 100,
                new Categoria("Exemplos"),
                new Modelo3D("modelos/exemplo1.txt", 1, 1, 1),
                usuarioDAO.buscaAnunciante(0)));
        cadastrarProduto(new Produto(1,
                20, "Exemplo 2", "Outro produto", 200,
                new Categoria("Exemplos"),
                new Modelo3D("modelos/exemplo2.txt", 1, 1, 1),
                usuarioDAO.buscaAnunciante(1)));
        cadastrarProduto(new Produto(2,
                30, "Exemplo 3", "Mais um produto", 300,
                new Categoria("Exemplos"),
                new Modelo3D("modelos/exemplo3.txt", 1, 1, 1),
                usuarioDAO.buscaAnunciante(0)));
    }

    @Override
    public ArrayList<Produto> listarProdutos() {
        return this.produtos;
    }

    @Override
    public Produto buscaProduto(long idProduto) {
        for (Produto produto : produtos) {
            if (produto.getId() == idProduto) {
                return produto;
            }
        }
        return null;
    }

    @Override
    public void cadastrarProduto(Produto produto) {
        for (Produto p : produtos) {
            if (p.getId() == produto.getId()) {
                System.out.println("Erro: Produto com ID " + produto.getId() + " já existe.");
                return;
            }
        }
        produtos.add(produto);
    }

    @Override
    public void removerProduto(Produto produto) {
        Produto p = buscaProduto(produto.getId());
        if (p == null) {
            System.out.println("Erro: Produto com ID " + produto.getId() + " não existe.");
        }
        produtos.remove(p);
    }

}
