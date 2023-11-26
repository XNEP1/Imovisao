import java.util.ArrayList;

public class TxtUsuarioDAO implements UsuarioDAO {

    private ArrayList<Usuario> usuarios;

    public TxtUsuarioDAO() {
        this.usuarios = new ArrayList<Usuario>();
        cadastrarUsuario(new Anunciante(0,
                "Pichau", "pichau@example.com", "(99) 99999-9999", "1234",
                "00.000.000/0001-00", new Endereco("Rua Exemplo", 1000), 100));
        cadastrarUsuario(new Anunciante(1,
                "Kabum", "kabum@example.com", "(99) 99999-9999", "1234",
                "00.000.000/0001-00", new Endereco("Rua Exemplo", 1000), 100));
        cadastrarUsuario(new Cliente(2,
                "João", "joao@email.com", "(99) 99999-9999", "123",
                "000.000.000-00", new Endereco("Rua Lugar", 6000)));
    }

    @Override
    public ArrayList<Usuario> listarUsuarios() {
        return this.usuarios;
    }

    @Override
    public ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Cliente) {
                clientes.add((Cliente) usuario);
            }
        }
        return clientes;
    }

    @Override
    public ArrayList<Anunciante> listarAnunciantes() {
        ArrayList<Anunciante> anunciantes = new ArrayList<Anunciante>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Anunciante) {
                anunciantes.add((Anunciante) usuario);
            }
        }
        return anunciantes;
    }

    @Override
    public Usuario buscaUsuario(long idUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == idUsuario) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public Cliente buscaCliente(long idCliente) {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Cliente && usuario.getId() == idCliente) {
                return (Cliente) usuario;
            }
        }
        return null;
    }

    @Override
    public Anunciante buscaAnunciante(long idAnunciante) {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Anunciante && usuario.getId() == idAnunciante) {
                return (Anunciante) usuario;
            }
        }
        return null;
    }

    @Override
    public void cadastrarUsuario(Usuario usuario) {
        for (Usuario usuarioCadastrado : usuarios) {
            if (usuarioCadastrado.getId() == usuario.getId()) {
                System.out.println("Erro: Usuário com ID " + usuario.getId() + " já existe.");
                return;
            }
        }
        usuarios.add(usuario);
    }

}
