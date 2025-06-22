package T2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

public class App {
    public static void main(String[] args) {
        String caminhoArquivo = args.length > 0 ? args[0] : "C:\\Users\\rsaho\\T2-ALEST\\src\\T2\\valores.txt"; // alterar o endereco do arquivo valores.txt
        App app = new App();
        app.rodarAplicacao(caminhoArquivo);
    }

    public void rodarAplicacao(String caminhoArquivo) {
        List<Integer> numeros = lerValoresDoArquivo(caminhoArquivo);
        
        if (numeros == null || numeros.isEmpty()) {
            System.out.println("Não foi possível ler os valores do arquivo ou o arquivo está vazio.");
            return;
        }

        int chaveParaPesquisar = numeros.remove(numeros.size() - 1);
        
        ArvoreBinariaPesquisa arvore = new ArvoreBinariaPesquisa();
        for (int numero : numeros) {
            arvore.inserir(numero);
        }
        
        System.out.println("Árvore construída com sucesso.");

        gerarArquivosDeCaminhamento(arvore);
        
        gerarArquivoDePesquisa(arvore, chaveParaPesquisar);
        
        System.out.println("Arquivos de saída gerados com sucesso!");
    }

    private List<Integer> lerValoresDoArquivo(String caminhoArquivo) {
        List<Integer> numeros = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(caminhoArquivo))) {
            while (scanner.hasNextInt()) {
                numeros.add(scanner.nextInt());
            }
            System.out.println("Arquivo " + caminhoArquivo + " lido com sucesso.");
            return numeros;
        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo não encontrado: " + caminhoArquivo);
            return null;
        }
    }
    
    private void gerarArquivosDeCaminhamento(ArvoreBinariaPesquisa arvore) {
        escreverListaEmArquivo("preordem.txt", arvore.caminharPreOrdem());
        escreverListaEmArquivo("posordem.txt", arvore.caminharPosOrdem());
        escreverListaEmArquivo("central.txt", arvore.caminharCentral());
        escreverListaEmArquivo("largura.txt", arvore.caminharLargura());
    }

    private void gerarArquivoDePesquisa(ArvoreBinariaPesquisa arvore, int chave) {
        Map<String, Object> resultadoPesquisa = arvore.pesquisar(chave);
        
        List<Integer> caminho = (List<Integer>) resultadoPesquisa.get("caminho");
        boolean encontrado = (boolean) resultadoPesquisa.get("encontrado");

        StringBuilder conteudoResultado = new StringBuilder();
        conteudoResultado.append(formatarLista(caminho)).append("\n");
        conteudoResultado.append("Quantidade de nodos visitados: ").append(caminho.size()).append("\n");
        conteudoResultado.append(encontrado ? "Achou" : "Não Achou");

        escreverStringEmArquivo("resultado.txt", conteudoResultado.toString());
    }

    private void escreverListaEmArquivo(String nomeArquivo, List<Integer> dados) {
        String conteudo = formatarLista(dados);
        escreverStringEmArquivo(nomeArquivo, conteudo);
    }
    
    private void escreverStringEmArquivo(String nomeArquivo, String conteudo) {
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            writer.write(conteudo);
            System.out.println("Arquivo gerado: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo " + nomeArquivo + ": " + e.getMessage());
        }
    }
    
    private String formatarLista(List<Integer> lista) {
        StringJoiner joiner = new StringJoiner(" ");
        for (Integer numero : lista) {
            joiner.add(numero.toString());
        }
        return joiner.toString();
    }
}
