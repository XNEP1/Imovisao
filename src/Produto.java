import java.util.ArrayList;
import java.util.List;

public class Produto {

    private String nome;
    private long id;
    private double preco;
    private String descricao;
    private int avaliacao;
    private Categoria categoria;
    private Modelo3D modelo3D;
    private Anunciante anunciante;
    private List<Denuncia> denuncias;

    public Produto(long id, double preco, String nome, String descricao, int avaliacao, Categoria categoria,
            Modelo3D modelo3d, Anunciante anunciante) {
        this.nome = nome;
        this.id = id;
        this.preco = preco;
        this.descricao = descricao;
        this.avaliacao = avaliacao;
        this.categoria = categoria;
        this.modelo3D = modelo3d;
        this.anunciante = anunciante;
        this.denuncias = new ArrayList<Denuncia>();
    }

    // Getters e Setters

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getId() {
        return this.id;
    }

    public int getAvaliacao() {
        return this.avaliacao;
    }

    public Modelo3D getModelo3D() {
        return this.modelo3D;
    }

    public void setModelo3D(Modelo3D modelo3d) {
        modelo3D = modelo3d;
    }

    public void setAnunciante(Anunciante anunciante) {
        this.anunciante = anunciante;
    }

    public void setDenuncias(List<Denuncia> denuncias) {
        this.denuncias = denuncias;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Anunciante getAnunciante() {
        return this.anunciante;
    }

    public List<Denuncia> getDenuncias() {
        return this.denuncias;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    // Funcoes membro

    public void registrarDenuncia(Denuncia denuncia) {
        this.denuncias.add(denuncia);
    }

    public String getVisualizacao() {
        String completa = "";
        String substr;
        int i;
        completa += String.format(
                " ______________________________________________________________________________________ \n");
        completa += String.format("│ %03d - %-50s │ %-25s │\n", id, nome, categoria.getNome());
        completa += String.format("│  R$: %-80.2f│\n", preco);
        completa += String.format(
                "│  Descrição:________________________________________________________________________  │\n",
                "Descrição:");
        for (i = 0; i < descricao.length() / 80; i++) {
            substr = descricao.substring(i * 80, i * 80 + 80);
            completa += String.format("│ │ %-80s │ │\n", substr);

        }
        substr = descricao.substring(i * 80, descricao.length());
        completa += String.format("│ │ %-80s │ │\n", substr);
        completa += String.format(
                "│ │__________________________________________________________________________________│ │\n");
        completa += String.format("│  Anunciante: %-55s  Avaliação: %-3d │\n", this.getAnunciante().getNome(),
                this.getAvaliacao());
        completa += String.format(
                "│______________________________________________________________________________________│\n");
        return completa;
    }
}
