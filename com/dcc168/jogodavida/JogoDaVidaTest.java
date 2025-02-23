package com.dcc168.jogodavida;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JogoDaVidaTest {
    private JogoDaVida jogo;
   
    @Before
    public void setUp() {
        jogo = new JogoDaVida();
    }

    @Test
    public void testContarVizinhosVivos() {
        int[][] tabuleiro = {
            {1, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
        };
        jogo.setTabuleiro(tabuleiro);
        assertEquals(3, jogo.contarVizinhosVivos(0, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContarVizinhosVivosForaDoLimite() {
        jogo.contarVizinhosVivos(-1, 0);
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
}
