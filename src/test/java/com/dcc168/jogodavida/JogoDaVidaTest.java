package com.dcc168.jogodavida;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JogoDaVidaTest {
    private JogoDaVida jogo;

    @Before
    public void setUp() {
        jogo = new JogoDaVida();
    }

    @Test
    public void testContarVizinhosVivos() {
        int[][] tabuleiro = new int[6][6];
        tabuleiro[1][1] = 1;
        tabuleiro[1][2] = 1;
        tabuleiro[2][1] = 1;
        
        jogo.setTabuleiro(tabuleiro);
        assertEquals(2, jogo.contarVizinhosVivos(1, 1));
    }

    @Test
    public void testProximaGeracao() {
        int[][] tabuleiro = new int[6][6];
        tabuleiro[1][1] = 1;
        tabuleiro[1][2] = 1;
        tabuleiro[2][1] = 1;
        
        jogo.setTabuleiro(tabuleiro);
        jogo.proximaGeracao();
        
        int[][] resultado = jogo.getTabuleiro();
        assertEquals(1, resultado[1][1]);
        assertEquals(1, resultado[1][2]);
        assertEquals(1, resultado[2][1]);
        assertEquals(1, resultado[2][2]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTabuleiroTamanhoInvalido() {
        jogo.setTabuleiro(new int[5][5]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContarVizinhosVivosForaDoLimite() {
        jogo.contarVizinhosVivos(-1, 0);
    }

    @Test
    public void testAplicarRegras() {

        assertEquals(0, jogo.aplicarRegras(1, 1));
        
        assertEquals(1, jogo.aplicarRegras(1, 2));
        assertEquals(1, jogo.aplicarRegras(1, 3));
        
        assertEquals(0, jogo.aplicarRegras(1, 4));
        
        assertEquals(1, jogo.aplicarRegras(0, 3));
        
        assertEquals(0, jogo.aplicarRegras(0, 2));
    }

    @Test
    public void testMortePorSolidao() {
        int[][] tabuleiro = {
            {1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
        };
        jogo.setTabuleiro(tabuleiro);
        jogo.proximaGeracao();
        assertEquals(0, jogo.getTabuleiro()[0][0]);
    }

    @Test
    public void testMortePorSuperpopulacao() {
        int[][] tabuleiro = {
            {1, 1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
        };
        jogo.setTabuleiro(tabuleiro);
        jogo.proximaGeracao();
        assertEquals(0, jogo.getTabuleiro()[1][1]);
    }

    @Test
    public void testNascimento() {
        int[][] tabuleiro = {
            {1, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
        };
        jogo.setTabuleiro(tabuleiro);
        jogo.proximaGeracao();
        assertEquals(1, jogo.getTabuleiro()[1][1]);
    }

    @Test
    public void testSobrevivencia() {
        int[][] tabuleiro = {
            {1, 1, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
        };
        jogo.setTabuleiro(tabuleiro);
        jogo.proximaGeracao();
        assertEquals(1, jogo.getTabuleiro()[1][1]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTabuleiroInvalido() {
        int[][] tabuleiroInvalido = {
            {1, 1, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        jogo.setTabuleiro(tabuleiroInvalido);
    }

    @Test
    public void testSetTabuleiroValido() {
        int[][] tabuleiroValido = {
            {1, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
        };
        jogo.setTabuleiro(tabuleiroValido);
        assertArrayEquals(tabuleiroValido, jogo.getTabuleiro());
    }
}
