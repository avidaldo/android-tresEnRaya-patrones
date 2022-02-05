package com.example.tresenraya.presenter

import com.example.tresenraya.model.TresEnRayaModel
import com.example.tresenraya.view.ITresEnRayaView


/** El Presenter es similar al controlador de MVC, sólo que no está vinculado a una implementación específica de la Vista, solo a una interfaz. Esto
 * soluciona los problemas de testabilidad, así como de modularidad/flexibilidad que teníamos con MVC. De hecho, puristas del MVP argumentarían que el
 * presenter nunca debería tener referencias a ninguna API o código de Android.
 *
 * Nos permite definir de modo simple y claro la intención de cada acción. En lugar de decirle a la vista cómo mostrar algo, simplemente le dice
 * qué hacer.
 */

class TresEnRayaPresenter(private val view: ITresEnRayaView) : ITresEnRayaPresenter {

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

