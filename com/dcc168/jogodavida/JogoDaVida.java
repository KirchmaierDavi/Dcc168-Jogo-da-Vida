package com.dcc168.jogodavida;

public class JogoDaVida {
    private final int TAMANHO = 6;
    private int[][] tabuleiro;

    public JogoDaVida() {
        tabuleiro = new int[TAMANHO][TAMANHO];
    }

    public void setTabuleiro(int[][] novoTabuleiro) {
        if (novoTabuleiro.length != TAMANHO || novoTabuleiro[0].length != TAMANHO) {
            throw new IllegalArgumentException("Tabuleiro deve ser 6x6");
        }
        this.tabuleiro = novoTabuleiro;
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public int contarVizinhosVivos(int linha, int coluna) {
        if (linha < 0 || linha >= TAMANHO || coluna < 0 || coluna >= TAMANHO) {
            throw new IllegalArgumentException("Posição inválida");
        }

        int vizinhosVivos = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
               
                int novaLinha = linha + i;
                int novaColuna = coluna + j;
               
                if (novaLinha >= 0 && novaLinha < TAMANHO &&
                    novaColuna >= 0 && novaColuna < TAMANHO) {
                    vizinhosVivos += tabuleiro[novaLinha][novaColuna];
                }
            }
        }
        return vizinhosVivos;
    }

    public void proximaGeracao() {
        int[][] novoTabuleiro = new int[TAMANHO][TAMANHO];
       
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                int vizinhosVivos = contarVizinhosVivos(i, j);
                novoTabuleiro[i][j] = aplicarRegras(tabuleiro[i][j], vizinhosVivos);
            }
        }
       
        tabuleiro = novoTabuleiro;
    }

    public int aplicarRegras(int estadoAtual, int vizinhosVivos) {
        if (estadoAtual == 1) {
            if (vizinhosVivos < 2) return 0; 
            if (vizinhosVivos > 3) return 0; 
            return 1; 
        } else {
            if (vizinhosVivos == 3) return 1; 
            return 0; 
        }
    }
}