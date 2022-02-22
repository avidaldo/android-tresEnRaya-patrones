package com.example.tresenraya.model;


import org.jetbrains.annotations.NotNull;

public class Tablero {

    // Definimos 2 jugadores identificados por los caracteres 'X' y 'O', que serán los que se escriban en la celda
    public enum Jugador {X, O}

    class Celda {
        private Jugador value = null;  // Cada celda puede estar vacía o con el valor de un jugador ('O' o 'X')
        public Jugador getValue() {
            return value;
        }
        public void setValue(Jugador value) {
            this.value = value;
        }
    }

    private Celda[][] celdas = new Celda[3][3]; // El tablero se compone de 3x3 celdas

    private Jugador ganador;
    public Jugador getGanador() {
        return ganador;
    }

    private enum GameState {JUGANDO, TERMINADO}
    private GameState estado;

    private Jugador jugadorEnTurno;


    public Tablero() {
        reiniciar();
    }


    public void reiniciar() {
        clearCells();
        ganador = null;
        jugadorEnTurno = Jugador.X;
        estado = GameState.JUGANDO;
    }

    /**
     * Método que marca la celda indicada por los párametros con el caracter del jugador en turno.
     *
     * @param row 0..2
     * @param col 0..2
     * @return
     */
    public boolean jugarTurno(int row, int col) {
        if (estado == GameState.TERMINADO) return false; // No se sigue marcando si el juego ha terminado

        if (!marcar(row, col)) return false;


        if (isMovimientoGana(row, col)) {
            estado = GameState.TERMINADO;
            ganador = jugadorEnTurno;
        } else {
            cambiarTurno(); // Cambia el Jugador en turno
        }

        return true;
    }

    private boolean marcar(int row, int col) {
        if (!isValida(row, col)) return false; // Celda inválida (la vista ya no debería permitirlo)
        celdas[row][col].setValue(jugadorEnTurno);
        return true;
    }


    private void clearCells() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                celdas[i][j] = new Celda();
            }
        }
    }

    private boolean isValida(int row, int col) {
        if (isOutOfBounds(row) || isOutOfBounds(col) ||
                isCeldaConValor(row, col))
            return false;
        return true;
    }

    private boolean isOutOfBounds(int idx) {
        return idx < 0 || idx > 2;
    }

    private boolean isCeldaConValor(int row, int col) {
        return getplayerInCell(row,col) != null;
    }

    public Jugador getplayerInCell(int row, int col) {
        return celdas[row][col].getValue();
    }


    /**
     * Devuelve true si el movimiento gana
     */
    private boolean isMovimientoGana(int fila, int columna) {
        return (celdas[fila][0].getValue() == jugadorEnTurno         // 3-in-the-row
                && celdas[fila][1].getValue() == jugadorEnTurno
                && celdas[fila][2].getValue() == jugadorEnTurno
                || celdas[0][columna].getValue() == jugadorEnTurno      // 3-in-the-column
                && celdas[1][columna].getValue() == jugadorEnTurno
                && celdas[2][columna].getValue() == jugadorEnTurno
                || fila == columna            // 3-in-the-diagonal
                && celdas[0][0].getValue() == jugadorEnTurno
                && celdas[1][1].getValue() == jugadorEnTurno
                && celdas[2][2].getValue() == jugadorEnTurno
                || fila + columna == 2    // 3-in-the-opposite-diagonal
                && celdas[0][2].getValue() == jugadorEnTurno
                && celdas[1][1].getValue() == jugadorEnTurno
                && celdas[2][0].getValue() == jugadorEnTurno);
    }

    private void cambiarTurno() {
        jugadorEnTurno = jugadorEnTurno == Jugador.X ? Jugador.O : Jugador.X;
    }

}
