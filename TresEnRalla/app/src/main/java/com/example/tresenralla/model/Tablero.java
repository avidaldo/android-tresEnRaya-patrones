package com.example.tresenralla.model;


public class Tablero {

    class Celda {
        private Jugador value = null;

        public Jugador getValue() {
            return value;
        }

        public void setValue(Jugador value) {
            this.value = value;
        }
    }

    public enum Jugador {X, O}

    private Celda[][] celdas = new Celda[3][3];

    private Jugador ganador;
    public Jugador getGanador() {
        return ganador;
    }
    private GameState estado;
    private Jugador turno;

    private enum GameState {JUGANDO, TERMINADO}


    public Tablero() {
        reiniciar();
    }


    public void reiniciar() {
        clearCells();
        ganador = null;
        turno = Jugador.X;
        estado = GameState.JUGANDO;
    }

    /**
     * Mark the current row for the player who's current turn it is.
     * Will perform no-op if the arguments are out of range or if that position is already played.
     * Will also perform a no-op if the game is already over.
     *
     * @param row 0..2
     * @param col 0..2
     * @return the player that moved or null if we did not move anything.
     */
    public Jugador marcar(int row, int col) {

        if (!isValida(row, col)) return null;

        celdas[row][col].setValue(turno);

        if (isMovimientoGana(turno, row, col)) {
            estado = GameState.TERMINADO;
            ganador = turno;

        } else {
            cambiarTurno();
        }


        return turno;
    }


    private void clearCells() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                celdas[i][j] = new Celda();
            }
        }
    }

    private boolean isValida(int row, int col) {
        if (estado == GameState.TERMINADO ||
                isOutOfBounds(row) || isOutOfBounds(col) ||
                isCeldaConValor(row, col))
            return false;
        return true;
    }

    private boolean isOutOfBounds(int idx) {
        return idx < 0 || idx > 2;
    }

    private boolean isCeldaConValor(int row, int col) {
        return celdas[row][col].getValue() != null;
    }


    /**
     * Devuelve true si el movimiento gana
     */
    private boolean isMovimientoGana(Jugador player, int fila, int columna) {
        return (celdas[fila][0].getValue() == player         // 3-in-the-row
                && celdas[fila][1].getValue() == player
                && celdas[fila][2].getValue() == player
                || celdas[0][columna].getValue() == player      // 3-in-the-column
                && celdas[1][columna].getValue() == player
                && celdas[2][columna].getValue() == player
                || fila == columna            // 3-in-the-diagonal
                && celdas[0][0].getValue() == player
                && celdas[1][1].getValue() == player
                && celdas[2][2].getValue() == player
                || fila + columna == 2    // 3-in-the-opposite-diagonal
                && celdas[0][2].getValue() == player
                && celdas[1][1].getValue() == player
                && celdas[2][0].getValue() == player);
    }

    private void cambiarTurno() {
        turno = turno == Jugador.X ? Jugador.O : Jugador.X;
    }

}
