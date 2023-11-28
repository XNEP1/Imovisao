import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Modelo3D {

	private String arquivoModelo;

	private double tamanhoX;

	private double tamanhoY;

	private double tamanhoZ;

	public Modelo3D(String arquivoModelo, double tamanhoX, double tamanhoY, double tamanhoZ) {
		this.arquivoModelo = arquivoModelo;
		this.tamanhoX = tamanhoX;
		this.tamanhoY = tamanhoY;
		this.tamanhoZ = tamanhoZ;
	}

	public String getRenderizacao() {
		try {
			Path path = Paths.get("../" + arquivoModelo);
			List<String> lines = Files.readAllLines(path);
			String conteudo = "";
			for (String line : lines) {
				conteudo += line + "\n";
			}
			return conteudo;
		} catch (Exception e) {
			System.out.println("Erro: Arquivo de modelo '" + arquivoModelo + "' não encontrado.");
			return null;
		}
	}

}
