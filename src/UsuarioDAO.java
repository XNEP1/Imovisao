import java.util.ArrayList;

public interface UsuarioDAO {

    public ArrayList<Usuario> listarUsuarios();

    public ArrayList<Cliente> listarClientes();

    public ArrayList<Anunciante> listarAnunciantes();
    
    public Usuario buscaUsuario(long idUsuario);

    public Usuario buscaUsuario(String email);

    public Cliente buscaCliente(long idCliente);

    public Anunciante buscaAnunciante(long idAnunciante);

    public void cadastrarUsuario(Usuario usuario);

}
