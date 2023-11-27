import java.util.ArrayList;

public class TxtCompraDAO implements CompraDAO {

    private ArrayList<Compra> compras;

    public TxtCompraDAO(UsuarioDAO usuarioDAO) {
        this.compras = new ArrayList<Compra>();
        cadastrarCompra(
                new Compra(0, usuarioDAO.buscaCliente(0), usuarioDAO.buscaCliente(0).getCarrinho().getItemCompra()));
        cadastrarCompra(
                new Compra(1, usuarioDAO.buscaCliente(1), usuarioDAO.buscaCliente(1).getCarrinho().getItemCompra()));
        cadastrarCompra(
                new Compra(2, usuarioDAO.buscaCliente(2), usuarioDAO.buscaCliente(2).getCarrinho().getItemCompra()));
    }

    @Override
    public ArrayList<Compra> listarCompras() {
        return this.compras;
    }

    @Override
    public Compra buscaCompra(long idCompra) {
        for (Compra compra : compras) {
            if (compra.getId() == idCompra) {
                return compra;
            }
        }
        return null;
    }

    @Override
    public void cadastrarCompra(Compra compra) {
        for (Compra c : compras) {
            if (c.getId() == compra.getId()) {
                System.out.println("Erro: Compra com ID " + compra.getId() + " já existe.");
                return;
            }
        }
        compras.add(compra);
    }
}
