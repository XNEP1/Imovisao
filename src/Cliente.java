import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

	private Carrinho carrinho;

	private List<Produto> favoritos;

	public Cliente(long id, String nome, String email, String telefone, String senha, String documento,
			Endereco endereco) {
		super(id, nome, email, telefone, senha, documento, endereco);
		this.carrinho = new Carrinho();
		this.favoritos = new ArrayList<Produto>();
	}

    public void reportarAnuncio (Produto prod, int motivo, String mensagem, String outro) {
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

    public void listarFavoritos() {
        Entrada entrada = Entrada.getInstance();
        List<Produto> prods = this.favoritos;

        String substr;
        int index = 0;
        int start = 0;

        while (true) {
            for (int j = start; j < start + 5 && j < prods.size(); j++) {
                Produto prod = prods.get(j);
                int i;
                System.out.printf(
                        " ______________________________________________________________________________________ \n");
                System.out.printf("│ %d - %-50s │ %-25s   │\n", index, prod.getNome(), prod.getCategoria().getNome());
                System.out.printf("│  R$: %-80.2f│\n", prod.getPreco());
                System.out.printf(
                        "│  Descrição:________________________________________________________________________  │\n",
                        "Descrição:");
                for (i = 0; i < prod.getDescricao().length() / 80; i++) {
                    substr = prod.getDescricao().substring(i * 80, i * 80 + 80);
                    System.out.printf("│ │ %-80s │ │\n", substr);

                }
                substr = prod.getDescricao().substring(i * 80, prod.getDescricao().length());
                System.out.printf("│ │ %-80s │ │\n", substr);
                System.out.printf(
                        "│ │__________________________________________________________________________________│ │\n");
                System.out.printf(
                        "│  Favoritar produto: f%d                                                               │\n",
                        index);
                System.out.printf(
                        "│______________________________________________________________________________________│\n");
                index++;
            }
            System.out.printf("  Páginas:  ");
            for (int k = 0; k <= prods.size() / 5; k++)
                System.out.printf("%d ", k);
            System.out.printf("\n");

            // TODO: Tratar numero negativo
            start = entrada.leInt("Página:");
            System.out.println(start + " " + prods.size());
            if (start > prods.size() / 5)
                start = prods.size() / 5;

            index = 0;
            start *= 5;
        }
    }

    public void favoritarProduto(Produto prod) {
        if (prod == null) {
            System.out.println("Erro: Produto não pode ser nulo.");
            return;
        }

        this.favoritos.add(prod);
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
