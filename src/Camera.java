import java.util.Scanner;

public class Camera {

    private boolean autorizada;

    private void pedeAutorizacao() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("[Camera] Voce autoriza o acesso a camera? [s/n]");
        String resposta;
        do {
            resposta = scanner.nextLine();
            if (resposta.equals("s")) {
                this.autorizada = true;
            } else if (resposta.equals("n")) {
                this.autorizada = false;
            } else {
                System.out.println("[Camera] Resposta invalida. Digite 's' para sim ou 'n' para nao.");
            }
        } while (!resposta.equals("s") && !resposta.equals("n"));
        scanner.close();
    }

    public void verModelo3D(Modelo3D modelo3D) {
        if (!this.autorizada)
            pedeAutorizacao();
        if (this.autorizada) {
            System.out.println("[Camera] Mostrando modelo 3D...");
            System.out.println("[Camera] terminado.");
        } else {
            System.out.println("[Camera] Acesso negado.");
        }
    }

}
