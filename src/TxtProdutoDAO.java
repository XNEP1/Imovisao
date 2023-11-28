import java.util.ArrayList;

public class TxtProdutoDAO implements ProdutoDAO {

    private ArrayList<Produto> produtos;

    public TxtProdutoDAO(UsuarioDAO usuarioDAO) {
        this.produtos = new ArrayList<Produto>();
        cadastrarProduto(new Produto(0,
                10, "Cadeira", "Uma bela cadeira.", 100,
                new Categoria("Exemplos"),
                new Modelo3D("modelos/exemplo1.txt", 1, 1, 1),
                usuarioDAO.buscaAnunciante(0)));
        cadastrarProduto(new Produto(1,
                20, "Xicara de Cafe", "Uma homenagem ao Java.", 200,
                new Categoria("Exemplos"),
                new Modelo3D("modelos/exemplo2.txt", 1, 1, 1),
                usuarioDAO.buscaAnunciante(1)));
        cadastrarProduto(new Produto(2,
                30, "Whiskey Singleton", "Seria uma bebida baseada em um padrao de projeto?", 300,
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
        for (Produto produto : this.produtos) {
            if (produto.getId() == idProduto) {
                return produto;
            }
        }
        return null;
    }

    @Override
    public void cadastrarProduto(Produto produto) {
        for (Produto p : this.produtos) {
            if (p.getId() == produto.getId()) {
                System.out.println("Erro: Produto com ID " + produto.getId() + " já existe.");
                return;
            }
        }
        this.produtos.add(produto);
    }

    @Override
    public void removerProduto(Produto produto) {
        Produto p = buscaProduto(produto.getId());
        if (p == null) {
            System.out.println("Erro: Produto com ID " + produto.getId() + " não existe.");
        }
        this.produtos.remove(p);
    }

    @Override
    public long getIdUnico() {
        long id = 0;
        for (Produto produto : this.produtos) {
            if (produto.getId() > id) {
                id = produto.getId();
            }
        }
        return id + 1;
    }

}
