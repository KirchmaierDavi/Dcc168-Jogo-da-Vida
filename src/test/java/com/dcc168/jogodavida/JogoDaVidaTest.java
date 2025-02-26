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
    public void testContarVizinhosVivosNasBordas() {
        int[][] tabuleiro = new int[6][6];
        tabuleiro[0][0] = 1;
        tabuleiro[0][1] = 1;
        tabuleiro[1][0] = 1;
        tabuleiro[1][1] = 1;

        jogo.setTabuleiro(tabuleiro);
        assertEquals(3, jogo.contarVizinhosVivos(0, 0));
        assertEquals(3, jogo.contarVizinhosVivos(0, 1));
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

    @Test
    public void testProximaGeracaoCompleta() {
        int[][] tabuleiro = new int[6][6];
        tabuleiro[2][1] = 1;
        tabuleiro[2][2] = 1;
        tabuleiro[2][3] = 1;

        jogo.setTabuleiro(tabuleiro);
        jogo.proximaGeracao();

        int[][] resultado = jogo.getTabuleiro();
        assertEquals(1, resultado[1][2]);
        assertEquals(1, resultado[2][2]);
        assertEquals(1, resultado[3][2]);
    }

    @Test
    public void testAplicarRegrasTodasCondicoes() {
        for (int vizinhos = 0; vizinhos <= 8; vizinhos++) {
            int resultado = jogo.aplicarRegras(1, vizinhos);
            if (vizinhos == 2 || vizinhos == 3) {
                assertEquals(1, resultado);
            } else {
                assertEquals(0, resultado);
            }

            resultado = jogo.aplicarRegras(0, vizinhos);
            assertEquals(vizinhos == 3 ? 1 : 0, resultado);
        }
    }

    @Test
    public void testTabuleiroModificacoes() {
        int[][] tabuleiro = new int[6][6];
        tabuleiro[1][1] = 1;
        tabuleiro[1][2] = 1;
        tabuleiro[2][1] = 1;
        tabuleiro[2][2] = 1;

        jogo.setTabuleiro(tabuleiro);

        for (int i = 0; i < 3; i++) {
            jogo.proximaGeracao();
            int[][] atual = jogo.getTabuleiro();
            assertNotNull(atual);
            assertEquals(6, atual.length);
            assertEquals(6, atual[0].length);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContarVizinhosVivosLimitesSuperior() {
        jogo.contarVizinhosVivos(6, 6);
    }

    @Test
    public void testPadroesEstaveis() {
        int[][] tabuleiro = new int[6][6];
        tabuleiro[1][1] = 1;
        tabuleiro[1][2] = 1;
        tabuleiro[2][1] = 1;
        tabuleiro[2][2] = 1;

        jogo.setTabuleiro(tabuleiro);
        int[][] antes = jogo.getTabuleiro().clone();
        jogo.proximaGeracao();
        int[][] depois = jogo.getTabuleiro();

        for (int i = 0; i < 6; i++) {
            assertArrayEquals(antes[i], depois[i]);
        }
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
                { 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 }
        };
        jogo.setTabuleiro(tabuleiro);
        jogo.proximaGeracao();
        assertEquals(0, jogo.getTabuleiro()[0][0]);
    }

    @Test
    public void testMortePorSuperpopulacao() {
        int[][] tabuleiro = {
                { 1, 1, 1, 0, 0, 0 },
                { 1, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 }
        };
        jogo.setTabuleiro(tabuleiro);
        jogo.proximaGeracao();
        assertEquals(0, jogo.getTabuleiro()[1][1]);
    }

    @Test
    public void testNascimento() {
        int[][] tabuleiro = {
                { 1, 1, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 }
        };
        jogo.setTabuleiro(tabuleiro);
        jogo.proximaGeracao();
        assertEquals(1, jogo.getTabuleiro()[1][1]);
    }

    @Test
    public void testSobrevivencia() {
        int[][] tabuleiro = {
                { 1, 1, 0, 0, 0, 0 },
                { 1, 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 }
        };
        jogo.setTabuleiro(tabuleiro);
        jogo.proximaGeracao();
        assertEquals(1, jogo.getTabuleiro()[1][1]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTabuleiroInvalido() {
        int[][] tabuleiroInvalido = {
                { 1, 1, 0, 0, 0 },
                { 1, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0 }
        };
        jogo.setTabuleiro(tabuleiroInvalido);
    }

    @Test
    public void testSetTabuleiroValido() {
        int[][] tabuleiroValido = {
                { 1, 1, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 }
        };
        jogo.setTabuleiro(tabuleiroValido);
        assertArrayEquals(tabuleiroValido, jogo.getTabuleiro());
    }

    @Test
    public void testContarVizinhosVivosTodasBordas() {
        int[][] tabuleiro = new int[6][6];

        tabuleiro[0][0] = 1;
        tabuleiro[0][5] = 1;
        tabuleiro[5][0] = 1;
        tabuleiro[5][5] = 1;

        jogo.setTabuleiro(tabuleiro);
        assertEquals(0, jogo.contarVizinhosVivos(0, 0));
        assertEquals(0, jogo.contarVizinhosVivos(0, 5));
        assertEquals(0, jogo.contarVizinhosVivos(5, 0));
        assertEquals(0, jogo.contarVizinhosVivos(5, 5));
    }

    @Test
    public void testPadraoOscilador() {
        int[][] tabuleiro = new int[6][6];
        tabuleiro[2][1] = 1;
        tabuleiro[2][2] = 1;
        tabuleiro[2][3] = 1;

        jogo.setTabuleiro(tabuleiro);
        jogo.proximaGeracao();

        int[][] primeiraGeracao = jogo.getTabuleiro();
        assertEquals(1, primeiraGeracao[1][2]);
        assertEquals(1, primeiraGeracao[2][2]);
        assertEquals(1, primeiraGeracao[3][2]);

        jogo.proximaGeracao();

        int[][] segundaGeracao = jogo.getTabuleiro();
        assertEquals(1, segundaGeracao[2][1]);
        assertEquals(1, segundaGeracao[2][2]);
        assertEquals(1, segundaGeracao[2][3]);
    }

    @Test
    public void testCelulasMortasVizinhas() {
        int[][] tabuleiro = new int[6][6];
        tabuleiro[2][2] = 1;

        jogo.setTabuleiro(tabuleiro);
        assertEquals(0, jogo.contarVizinhosVivos(2, 2));
    }

    @Test
    public void testLimitesExtremosDoTabuleiro() {
        for (int i = -1; i <= 6; i++) {
            for (int j = -1; j <= 6; j++) {
                if (i < 0 || i >= 6 || j < 0 || j >= 6) {
                    try {
                        jogo.contarVizinhosVivos(i, j);
                        fail("Deveria lançar IllegalArgumentException para posição (" + i + "," + j + ")");
                    } catch (IllegalArgumentException e) {
                    }
                }
            }
        }
    }

    @Test
    public void testTabuleiroVazio() {
        int[][] tabuleiro = new int[6][6];
        jogo.setTabuleiro(tabuleiro);
        jogo.proximaGeracao();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(0, jogo.getTabuleiro()[i][j]);
            }
        }
    }

    @Test
public void testOscilador() {
    int[][] tabuleiro = new int[6][6];

    tabuleiro[2][1] = 1;
    tabuleiro[2][2] = 1;
    tabuleiro[2][3] = 1;
    
    jogo.setTabuleiro(tabuleiro);
    
    jogo.proximaGeracao();
    int[][] primeiraGeracao = jogo.getTabuleiro();
    assertEquals(1, primeiraGeracao[1][2]); 
    assertEquals(1, primeiraGeracao[2][2]); 
    assertEquals(1, primeiraGeracao[3][2]); 
    assertEquals(0, primeiraGeracao[2][1]);
    assertEquals(0, primeiraGeracao[2][3]); 
    
    jogo.proximaGeracao();
    int[][] segundaGeracao = jogo.getTabuleiro();
    assertEquals(1, segundaGeracao[2][1]); 
    assertEquals(1, segundaGeracao[2][2]); 
    assertEquals(1, segundaGeracao[2][3]); 
    assertEquals(0, segundaGeracao[1][2]); 
    assertEquals(0, segundaGeracao[3][2]); 
}
}
