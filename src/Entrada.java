import java.util.Scanner;

public class Entrada {

    private static Entrada instance;
    private Scanner scanner;

    private Entrada() {
        this.scanner = new Scanner(System.in);
    }

    public static Entrada getInstance() {
        if (Entrada.instance == null) {
            Entrada.instance = new Entrada();
        }
        return Entrada.instance;
    }

    public String leString(String mensagem) {
        System.out.print(mensagem + "> ");
        return this.scanner.nextLine();
    }

    public int leInt(String mensagem) {
        String linha;
        while (true) {
            linha = leString(mensagem);
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada invalida.");
            }
        }
    }

    public long leLong(String mensagem) {
        String linha;
        while (true) {
            linha = leString(mensagem);
            try {
                return Long.parseLong(linha);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada invalida.");
            }
        }
    }

    public boolean leBoolean(String mensagem) {
        String linha;
        while (true) {
            linha = leString(mensagem + " [s/n]");
            if (linha.equals("s")) {
                return true;
            } else if (linha.equals("n")) {
                return false;
            } else {
                System.out.println("Erro: Entrada invalida.");
            }
        }
    }

}
