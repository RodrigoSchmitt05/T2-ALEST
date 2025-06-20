package T2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        App app = new App();
        app.rodarAplicacao();
    }
    public void rodarAplicacao() {
        String caminhoArquivo = "valores.txt";
        lerValoresEInserirNaArvore(caminhoArquivo);
    }

    public void lerValoresEInserirNaArvore(String caminhoArquivo) {
        ArvoreBinariaPesquisa arvore = new ArvoreBinariaPesquisa();
        try (Scanner scanner = new Scanner(new File(caminhoArquivo))) {
            while (scanner.hasNextInt()) {
                int valor = scanner.nextInt();
                arvore.inserir(valor);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + caminhoArquivo);
        }
    }
}