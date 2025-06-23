# Trabalho 2 - ALEST1: Árvore Binária de Pesquisa

Este projeto consiste na implementação de uma Árvore Binária de Pesquisa (ABP) em Java. O programa lê um arquivo `.txt` contendo valores inteiros, insere esses valores na árvore em ordem de leitura e realiza os quatro caminhamentos clássicos: pré-ordem, pós-ordem, central e largura.

A última linha do arquivo representa a chave a ser pesquisada. O programa gera arquivos `.txt` com o resultado de cada caminhamento e um arquivo `resultado.txt` contendo o caminho percorrido na busca, a quantidade de nós visitados e uma indicação se a chave foi encontrada.


## Funcionalidades

- Leitura de um arquivo `.txt` contendo números inteiros
- Construção de árvore binária com os valores lidos
- Geração dos arquivos de caminhamento:
  - `preordem.txt`
  - `posordem.txt`
  - `central.txt`
  - `largura.txt`
- Pesquisa de uma chave presente na última linha do arquivo
- Geração do arquivo `resultado.txt` com:
  - Caminho percorrido
  - Quantidade de nós visitados
  - Indicação se a chave foi encontrada ou não

## Como executar

1. Clonar o repositório

2. Substituir ou editar o arquivo `valores.txt` com os valores desejados (última linha = chave de busca)

3. Compilar e executar:


4. Verificar os arquivos gerados na raiz do projeto:
- `preordem.txt`
- `posordem.txt`
- `central.txt`
- `largura.txt`
- `resultado.txt`

## Estrutura

- `App.java`: Classe principal que gerencia a aplicação
- `ArvoreBinariaPesquisa.java`: Implementação da árvore e seus métodos
- `valores.txt`: Arquivo de entrada com os dados

## Integrantes

Yan Pantoja, Pedro Filipetto e Rodrigo Schmitt
