package com.example.tresenraya.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tresenraya.model.TresEnRayaModel


/**
 * Nuestro ViewModel heredará de la clase ViewModel
 *
 */

class TresEnRayaViewModel() : ViewModel() {

    /** Necesitaremos referencias tanto al modelo como a la vista, ya que desde aquí se hará la comunicación entre ambos.
     * La referencia a la vista se recibe desde el constructor ya que la ejecución empieza desde ella.
     * El modelo será instanciado por el propio presentador en el momento en que se lanza la app (en onCreate) */

    private lateinit var model: TresEnRayaModel
    override fun onCreate() { model =
        TresEnRayaModel()
    }


    /** Métodos a implementar de acuerdo al ciclo de vida de las Activity en Android.
     Estos métodos están definidos en la interfaz Presenter que esta clase implementa. */
    override fun onPause() {}
    override fun onResume() {}
    override fun onDestroy() {}


    /** Cuando un usuario selecciona una celda, nuestro presenter sólo recibe como información la fila y columna.
     * La vista es la encargada de determinar tales valores, a partir del Button que se presionó. */
    fun onButtonSelected(row: Int, col: Int) {
        val jugadorQueMueve = model.marcarCeldaOrNull(row, col) ?: return

        view.setButtonText(row, col, jugadorQueMueve.toString()) // Indicamos a la vista que marque esa celda con el jugador que toca
        if (model.ganador != null) { // Si el movimiento generó un ganador, lo indicamos a la vista
            view.showWinner(jugadorQueMueve.toString())
        }
    }

    /** Cuando necesitamos resetear, simplemente dictamos qué hacer. */
    fun onResetSelected() {
        view.clearWinnerDisplay()
        view.clearButtons()
        model.reiniciar()
    }
}

