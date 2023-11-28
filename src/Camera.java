import java.util.Scanner;

public class Camera {

    private boolean autorizada;

    private void pedeAutorizacao() {
        Entrada entrada = Entrada.getInstance();
        System.out.println("[Camera] Voce autoriza o acesso a camera?");
        if (entrada.leBoolean("Opção")) {
            this.autorizada = true;
        } else {
            this.autorizada = false;
        }
    }

    public void verModelo3D(Modelo3D modelo3D) {
        if (!this.autorizada)
            pedeAutorizacao();
        if (this.autorizada) {
            System.out.println("[Camera] Mostrando modelo 3D...");
            System.out.println(modelo3D.getRenderizacao());
            System.out.println("[Camera] terminado.");
        } else {
            System.out.println("[Camera] Acesso negado.");
        }
    }

}
