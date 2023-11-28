import java.util.ArrayList;
import java.util.List;

public class Anunciante extends Usuario {

    private int avalicao;
    private List<Feedback> feedbacks;

    public Anunciante(long id, String nome, String email, String telefone, String senha, String documento,
            Endereco endereco, int avaliacao) {
        super(id, nome, email, telefone, senha, documento, endereco);
        this.avalicao = avaliacao;
        this.feedbacks = new ArrayList<Feedback>();
    }

    public void registrarFeedback(Feedback feedback) {
        this.feedbacks.add(feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return this.feedbacks;
    }

    public List<Feedback> getUnreadFeedbacks() {
        List<Feedback> unreadFeedbacks = new ArrayList<>();

        for (Feedback feedback : this.feedbacks) {
            if (!feedback.foiLido()) {
                unreadFeedbacks.add(feedback);
            }
        }

        return unreadFeedbacks;
    }

    public List<Feedback> getReadFeedbacks() {
        List<Feedback> readFeedbacks = new ArrayList<>();

        for (Feedback feedback : this.feedbacks) {
            if (feedback.foiLido()) {
                readFeedbacks.add(feedback);
            }
        }

        return readFeedbacks;
    }

    public int getAvaliacao() {
        return this.avalicao;
    }

}
