import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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



	public String getArquivoModelo() {
		return arquivoModelo;
	}



	public void setArquivoModelo(String arquivoModelo) {
		this.arquivoModelo = arquivoModelo;
	}



	public double getTamanhoX() {
		return tamanhoX;
	}



	public void setTamanhoX(double tamanhoX) {
		this.tamanhoX = tamanhoX;
	}



	public double getTamanhoY() {
		return tamanhoY;
	}



	public void setTamanhoY(double tamanhoY) {
		this.tamanhoY = tamanhoY;
	}



	public double getTamanhoZ() {
		return tamanhoZ;
	}



	public void setTamanhoZ(double tamanhoZ) {
		this.tamanhoZ = tamanhoZ;
	}

}
