package com.example.tresenraya.model

class TresEnRayaModel {
    enum class Jugador { X, O }

    class Celda {
        var value: Jugador? = null // Cada celda puede estar vacía (a null) o con el valor de un jugador ('O' o 'X')
        override fun toString(): String {
            return value?.toString() ?: ""
        }
    }

    var tablero = Array(3) { Array<Celda>(3) { Celda() } } // El tablero se compone de 3x3 celdas
    /* Modificación: lo inicializo así para que siempre haya un elemento celda en el array
    Antes podía haber un elemento del array a null. Ahora siempre hay un objeto celda,
    cuyo valor puede ser null (así fuerzo a usar el toString de celda, con null safety).
     */


    var ganador: Jugador? = null
    /* Quito la variable Estado, ya que es redudante con ganador.
    El juego termina cuando se asigna un valor a ganador. Mientras
    está a null, se está en juego.
     */


    private var jugadorEnTurno = Jugador.X


    fun reiniciar() {
        clearCells()
        ganador = null
        jugadorEnTurno = Jugador.X
    }

    /**
     * Método que marca la celda indicada por los párametros con el caracter del jugador en turno.
     *
     * @param row 0..2
     * @param col 0..2
     */
    fun marcar(row: Int, col: Int) {
        ganador?.let { return } // No se sigue marcando si el juego ha terminado
        if (!isValida(row, col)) return // Celda inválida (la vista ya no debería permitirlo

        tablero[row][col].value = jugadorEnTurno
        if (isMovimientoGana(jugadorEnTurno, row, col)) {
            //estado = GameState.TERMINADO
            ganador = jugadorEnTurno
        } else {
            cambiarTurno() // Cambia el Jugador en turno
        }

    }


    private fun clearCells() {
        for (i in 0..2) {
            for (j in 0..2) {
                tablero[i][j].value = null
                /* Ahora puedo poner el valor a null y no crear nuevas instancias */
            }
        }
    }

    private fun isValida(row: Int, col: Int): Boolean {
        return !(isOutOfBounds(row) || isOutOfBounds(col) ||
                isCeldaConValor(row, col))
    }

    private fun isOutOfBounds(idx: Int): Boolean {
        return idx < 0 || idx > 2
    }

    private fun isCeldaConValor(row: Int, col: Int): Boolean {
        return tablero[row][col].value != null
    }


    /** Devuelve true si el movimiento gana  */
    private fun isMovimientoGana(player: Jugador, fila: Int, columna: Int) =
        // 3-in-the-row
        (tablero[fila][0].value == player
                && tablero[fila][1].value == player
                && tablero[fila][2].value == player)
                || // 3-in-the-column
                (tablero[0][columna].value == player
                        && tablero[1][columna].value == player
                        && tablero[2][columna].value == player)
                || // 3-in-the-diagonal
                (fila == columna
                        && tablero[0][0].value == player
                        && tablero[1][1].value == player
                        && tablero[2][2].value == player)
                || // 3-in-the-opposite-diagonal
                (fila + columna == 2
                        && tablero[0][2].value == player
                        && tablero[1][1].value == player
                        && tablero[2][0].value == player)


    private fun cambiarTurno() {
        jugadorEnTurno = if (jugadorEnTurno == Jugador.X) Jugador.O else Jugador.X
    }


}