import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileReaderWrite {

    public static void mostrarVetor(int[] vetor) {
        for (int i = 0; i < vetor.length; i++) {
            System.out.println("[" + i + "] " + vetor[i]);
        }
    }

    public static int[] carregarVetor(String arquivo) throws IOException {
        var texto = carregarArquivo(arquivo);
        var vetorString = textoParaVetor(texto, ";");
        return vetorStringParaInt(vetorString);
    }

    public static String carregarArquivo(String arquivo) throws IOException {
        // carregar o texto existente no arquivo
        String texto = "";
        BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
        String linha = leitor.readLine();
        while (linha != null) {
            texto = texto + linha + "\n";
            linha = leitor.readLine();
        }
        leitor.close();
        return texto;
    }

    public static String[] textoParaVetor(String texto, String separador) {
        // converter o texto em um vetor de elementos (string)
        texto = texto.replace("\n", separador);
        return texto.split(separador);
    }

    public static int[] vetorStringParaInt(String[] vetorString) {
        // converter o vetor de strings para um vetor de inteiros
        int[] vetorInt = new int[vetorString.length];
        for (int i = 0; i < vetorString.length; i++) {
            vetorInt[i] = Integer.parseInt(vetorString[i]);
        }

        return vetorInt;
    }

    public static void escreverArquivo(String arquivo, String texto) throws IOException {
        if (carregarArquivo(arquivo) != null && !carregarArquivo(arquivo).isEmpty()) {
            texto = carregarArquivo(arquivo) + "\n" + texto;
        }

        BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo));
        escritor.append(texto);
        escritor.close();
    }
}
