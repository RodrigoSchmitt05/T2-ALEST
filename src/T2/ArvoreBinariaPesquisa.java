package T2;

public class ArvoreBinariaPesquisa {
    private class Nodo {
        private int chave;
        private Nodo esquerda;
        private Nodo direita;
        private Nodo pai;

        public Nodo(int chave) {
            this.chave = chave;
        }
    }

    private Nodo raiz;
    private int tamanho;

    public ArvoreBinariaPesquisa() {
        tamanho = 0;
    }

    public void adicionar(int chave) {
        Nodo n = new Nodo(chave);
        if (raiz == null)
            raiz = n;
        else {
            adicionarRecursivo(n, raiz);
        }
        tamanho++;
    }

    private void adicionarRecursivo(Nodo n, Nodo pai) {
        if (n.chave <= pai.chave) {
            if (pai.esquerda == null) {
                pai.esquerda = n;
                n.pai = pai;
            } else {
                adicionarRecursivo(n, pai.esquerda);
            }
        } else {
            if (pai.direita == null) {
                pai.direita = n;
                n.pai = pai;
            } else {
                adicionarRecursivo(n, pai.direita);
            }
        }
    }

    public boolean contem(int chave) {
        Nodo n = obterNodoRecursivamente(chave, raiz);
        return n != null;
    }

    private Nodo obterNodoRecursivamente(int chave, Nodo n) {
        if (n == null) {
            return null;
        }
        if (chave == n.chave) {
            return n;
        } else if (chave < n.chave) {
            return obterNodoRecursivamente(chave, n.esquerda);
        } else {
            return obterNodoRecursivamente(chave, n.direita);
        }
    }

    public String caminharPreOrdem() {
        StringBuilder sb = new StringBuilder();
        caminharPreOrdemRecursivo(raiz, sb);
        return sb.toString();
    }
    private void caminharPreOrdemRecursivo(Nodo n, StringBuilder sb) {
        if (n != null) {
            sb.append(n.chave).append("");
            caminharPreOrdemRecursivo(n.esquerda, sb);
            caminharPreOrdemRecursivo(n.direita, sb);
        }
    }

    public String caminharPosOrdem() {
        StringBuilder sb = new StringBuilder();
        caminharPreOrdemRecursivo(raiz, sb);
        return sb.toString();
    }
    private void caminharPosOrdemRecursivo(Nodo n, StringBuilder sb) {
        if (n != null) {
            caminharPosOrdemRecursivo(n.esquerda, sb);
            caminharPosOrdemRecursivo(n.direita, sb);
            sb.append(n.chave).append("");
        }
    }

    public String caminharCentral() {
        StringBuilder sb = new StringBuilder();
        caminharCentralRecursivo(raiz, sb);
        return sb.toString();
    }
    public void caminharCentralRecursivo(Nodo n, StringBuilder sb) {
        if (n != null) {
            caminharCentralRecursivo(n.esquerda, sb);
            sb.append(n.chave).append("");
            caminharCentralRecursivo(n.direita, sb);
        }
    }

    public int obterTamanho() {
        return tamanho;
    }

    public String caminharLargura() {
        if(raiz == null) return null;
        String caminho = "";

        Fila fila = new Fila();
        Nodo n = raiz;
        fila.enfileirar(n.chave);
        while(!fila.estaVazia()) {
            int e = fila.desenfileirar();
            caminho = caminho + String.valueOf(e) + "";
            n = obterNodoRecursivamente(e, raiz);
            if(n.esquerda != null) fila.enfileirar(n.esquerda.chave);
            if(n.esquerda != null) fila.enfileirar(n.direita.chave);
        }
        return caminho;
    }

    public int obterNivel(int chave) {
        return obterNivelRecursivo(chave, raiz, 0);
    }
    private int obterNivelRecursivo(int chave, Nodo n, int nivel) {
        if (n == null) return -1;
        if (n.chave == chave) return nivel;
        else if (chave < n.chave) return obterNivelRecursivo(chave, n.esquerda, nivel + 1);
        else return obterNivelRecursivo(chave, n.direita, nivel + 1);
    }
    
    public void imprimirArvore() {
        imprimirArvoreRecursivo(raiz, 0);
    }
    private void imprimirArvoreRecursivo(Nodo n, int nivel) {
        if (n != null) {
            imprimirArvoreRecursivo(n.direita, nivel + 1);
            for (int i = 0; i < nivel; i++) {
                System.out.println("   ");
            }
            System.out.println(n.chave);
            imprimirArvoreRecursivo(n.esquerda, nivel + 1);
        }
    }

    public void inserir(int valor) {
        adicionar(valor);
    }

}
