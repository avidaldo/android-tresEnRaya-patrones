package com.example.tresenralla;

import static junit.framework.Assert.assertNull;

import static org.junit.Assert.assertEquals;

import com.example.tresenralla.model.Board;
import com.example.tresenralla.model.Player;

import org.junit.Before;
import org.junit.Test;


public class TicTacToeTests {

    private Board board;

    @Before
    public void setup() {
        board = new Board();
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

        board.mark(0,0); // x
        assertNull(board.getWinner());

        board.mark(1,0); // o
        assertNull(board.getWinner());

        board.mark(0,1); // x
        assertNull(board.getWinner());

        board.mark(2,1); // o
        assertNull(board.getWinner());

        board.mark(0,2); // x
        assertEquals(Player.X, board.getWinner());
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

        board.mark(0,1); // x
        assertNull(board.getWinner());

        board.mark(0,0); // o
        assertNull(board.getWinner());

        board.mark(2,1); // x
        assertNull(board.getWinner());

        board.mark(1,1); // o
        assertNull(board.getWinner());

        board.mark(0,2); // x
        assertNull(board.getWinner());

        board.mark(2,2); // o
        assertEquals(Player.O, board.getWinner());

    }

}
