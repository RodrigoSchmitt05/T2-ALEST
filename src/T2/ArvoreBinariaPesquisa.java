package T2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void inserir(int chave) {
        Nodo n = new Nodo(chave);
        if (raiz == null) {
            raiz = n;
        } else {
            inserirRecursivo(n, raiz);
        }
        tamanho++;
    }

    private void inserirRecursivo(Nodo n, Nodo pai) {
        if (n.chave < pai.chave) {
            if (pai.esquerda == null) {
                pai.esquerda = n;
                n.pai = pai;
            } else {
                inserirRecursivo(n, pai.esquerda);
            }
        } else {
            if (pai.direita == null) {
                pai.direita = n;
                n.pai = pai;
            } else {
                inserirRecursivo(n, pai.direita);
            }
        }
    }
    
    public Map<String, Object> pesquisar(int chave) {
        Map<String, Object> resultado = new HashMap<>();
        List<Integer> caminho = new ArrayList<>();
        Nodo atual = raiz;
        boolean encontrado = false;

        while (atual != null) {
            caminho.add(atual.chave);
            if (chave == atual.chave) {
                encontrado = true;
                break;
            } else if (chave < atual.chave) {
                atual = atual.esquerda;
            } else {
                atual = atual.direita;
            }
        }

        resultado.put("caminho", caminho);
        resultado.put("encontrado", encontrado);
        return resultado;
    }

    public List<Integer> caminharPreOrdem() {
        List<Integer> lista = new ArrayList<>();
        caminharPreOrdemRecursivo(raiz, lista);
        return lista;
    }

    private void caminharPreOrdemRecursivo(Nodo n, List<Integer> lista) {
        if (n != null) {
            lista.add(n.chave);
            caminharPreOrdemRecursivo(n.esquerda, lista);
            caminharPreOrdemRecursivo(n.direita, lista);
        }
    }

    public List<Integer> caminharPosOrdem() {
        List<Integer> lista = new ArrayList<>();
        caminharPosOrdemRecursivo(raiz, lista);
        return lista;
    }

    private void caminharPosOrdemRecursivo(Nodo n, List<Integer> lista) {
        if (n != null) {
            caminharPosOrdemRecursivo(n.esquerda, lista);
            caminharPosOrdemRecursivo(n.direita, lista);
            lista.add(n.chave);
        }
    }

    public List<Integer> caminharCentral() {
        List<Integer> lista = new ArrayList<>();
        caminharCentralRecursivo(raiz, lista);
        return lista;
    }

    private void caminharCentralRecursivo(Nodo n, List<Integer> lista) {
        if (n != null) {
            caminharCentralRecursivo(n.esquerda, lista);
            lista.add(n.chave);
            caminharCentralRecursivo(n.direita, lista);
        }
    }
    
    public List<Integer> caminharLargura() {
        List<Integer> resultado = new ArrayList<>();
        if (raiz == null) {
            return resultado;
        }

        Fila fila = new Fila();
        fila.enfileirar(raiz);

        while (!fila.estaVazia()) {
            Nodo n = fila.desenfileirar();
            if (n != null) {
                resultado.add(n.chave);
                if (n.esquerda != null) {
                    fila.enfileirar(n.esquerda);
                }
                if (n.direita != null) {
                    fila.enfileirar(n.direita);
                }
            }
        }
        return resultado;
    }

    private class Fila {
        private class NodoFila {
            private Nodo arvoreNodo;
            private NodoFila proximo;

            NodoFila(Nodo arvoreNodo) {
                this.arvoreNodo = arvoreNodo;
            }
        }

        private NodoFila inicio;
        private NodoFila fim;

        public void enfileirar(Nodo arvoreNodo) {
            NodoFila novo = new NodoFila(arvoreNodo);
            if (fim != null) {
                fim.proximo = novo;
            }
            fim = novo;
            if (inicio == null) {
                inicio = fim;
            }
        }

        public Nodo desenfileirar() {
            if (inicio == null) return null;
            Nodo arvoreNodo = inicio.arvoreNodo;
            inicio = inicio.proximo;
            if (inicio == null) {
                fim = null;
            }
            return arvoreNodo;
        }

        public boolean estaVazia() {
            return inicio == null;
        }
    }
}
