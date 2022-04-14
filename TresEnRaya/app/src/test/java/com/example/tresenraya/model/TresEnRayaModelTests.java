package com.example.tresenraya.model;

import static junit.framework.Assert.assertNull;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class TresEnRayaModelTests {

    private TresEnRayaModel board;


    /** Código que se ejecutará siempre antes de cada test. En este caso, para probar los métodos del modelo, es necesario tener
     * instanciado un objeto de éste */
    @Before
    public void setup() {
        board = new TresEnRayaModel();
    }

    /**
     * This test will simulate and verify x is the winner.
     *
     *    X | X | X
     *    O |   |
     *      | O |
     */
    @Test
    public void test3inRowAcrossTopForX() {

        board.marcar(0,0); // x
        assertNull(board.getGanador());

        board.marcar(1,0); // o
        assertNull(board.getGanador());

        board.marcar(0,1); // x
        assertNull(board.getGanador());

        board.marcar(2,1); // o
        assertNull(board.getGanador());

        board.marcar(0,2); // x
        assertEquals(TresEnRayaModel.Jugador.X, board.getGanador());
    }


    /**
     * This test will simulate and verify o is the winner.
     *
     *    O | X | X
     *      | O |
     *      | X | O
     */
    @Test
    public void test3inRowDiagonalFromTopLeftToBottomForO() {

        board.marcar(0,1); // x
        assertNull(board.getGanador());

        board.marcar(0,0); // o
        assertNull(board.getGanador());

        board.marcar(2,1); // x
        assertNull(board.getGanador());

        board.marcar(1,1); // o
        assertNull(board.getGanador());

        board.marcar(0,2); // x
        assertNull(board.getGanador());

        board.marcar(2,2); // o
        assertEquals(TresEnRayaModel.Jugador.O, board.getGanador());

    }

}
