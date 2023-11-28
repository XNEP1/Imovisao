import java.util.ArrayList;

public class TxtCompraDAO implements CompraDAO {

    private ArrayList<Compra> compras;

    public TxtCompraDAO(UsuarioDAO usuarioDAO) {
        this.compras = new ArrayList<Compra>();
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
    public ArrayList<Compra> buscaComprasPorCliente(long idCliente) {
        ArrayList<Compra> comprasCliente = new ArrayList<Compra>();
        for (Compra compra : compras) {
            if (compra.getCliente().getId() == idCliente) {
                comprasCliente.add(compra);
            }
        }
        return comprasCliente;
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

    @Override
    public long getIdUnico() {
        long id = 0;
        for (Compra compra : compras) {
            if (compra.getId() > id) {
                id = compra.getId();
            }
        }
        return id + 1;
    }
}
