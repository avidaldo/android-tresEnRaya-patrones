package com.example.tresenraya;

import static junit.framework.Assert.assertNull;

import static org.junit.Assert.assertEquals;

import com.example.tresenraya.model.Tablero;

import org.junit.Before;
import org.junit.Test;


public class TicTacToeTests {

    private Tablero board;

    @Before
    public void setup() {
        board = new Tablero();
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

        board.jugarTurno(0,0); // x
        assertNull(board.getGanador());

        board.jugarTurno(1,0); // o
        assertNull(board.getGanador());

        board.jugarTurno(0,1); // x
        assertNull(board.getGanador());

        board.jugarTurno(2,1); // o
        assertNull(board.getGanador());

        board.jugarTurno(0,2); // x
        assertEquals(Tablero.Jugador.X, board.getGanador());
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

        board.jugarTurno(0,1); // x
        assertNull(board.getGanador());

        board.jugarTurno(0,0); // o
        assertNull(board.getGanador());

        board.jugarTurno(2,1); // x
        assertNull(board.getGanador());

        board.jugarTurno(1,1); // o
        assertNull(board.getGanador());

        board.jugarTurno(0,2); // x
        assertNull(board.getGanador());

        board.jugarTurno(2,2); // o
        assertEquals(Tablero.Jugador.O, board.getGanador());

    }

}
