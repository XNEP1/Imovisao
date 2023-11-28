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
            String renderizacao = modelo3D.getRenderizacao();
            if (renderizacao != null)
                System.out.println(renderizacao);
        } else {
            System.out.println("[Camera] Acesso negado.");
        }
    }

}
