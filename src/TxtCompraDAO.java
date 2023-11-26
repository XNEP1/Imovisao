import java.util.ArrayList;

public class TxtCompraDAO implements CompraDAO {

    private ArrayList<Compra> compras;

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
}
