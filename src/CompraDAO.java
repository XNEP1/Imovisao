import java.util.ArrayList;

public interface CompraDAO {

    public ArrayList<Compra> listarCompras();

    public Compra buscaCompra(long idCompra);
}
