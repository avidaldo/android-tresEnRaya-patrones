package com.example.tresenraya.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.GridLayout
import com.example.tresenraya.R
import com.example.tresenraya.databinding.ActivityMainBinding
import com.example.tresenraya.model.Tablero


/** En la típica implementación de MVC en Android, se considera la Activity como
 * Controlador y los xml de layouts como vista. La activity será la clase principal
 * que controlará el funcionamiento de la app comunicando el modelo con la vista.
 */

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.name

    private lateinit var binding: ActivityMainBinding

    private lateinit var modelo: Tablero


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGrid.setOnClickListener(::onCellClicked) // Asignamos comportamiento a los botones

        modelo = Tablero() // Instanciamos el modelo en el momento en que se lanza la activity

    }


    /** Método que se lanza cuando se hace click en una celda */
    private fun onCellClicked(button: Button) {
        val tag = button.tag.toString().toCharArray()
        val row = tag[0].digitToInt()
        val col = tag[1].digitToInt()
        val jugadorQueMovio = modelo.marcar(row, col) ?: return // TODO: arreglar con scoped functions?

        Log.i(TAG, "Celda [$row,$col] marcada por Jugador $jugadorQueMovio")

        button.text = jugadorQueMovio.toString()
        if (modelo.ganador != null) { // Comprobamos si el movimiento ha generado un ganador
            binding.winnerPlayerLabel.text = jugadorQueMovio.toString()
            binding.winnerPlayerViewGroup.visibility = View.VISIBLE
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_reset -> {
                reset(); true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun reset() {
        binding.winnerPlayerViewGroup.visibility = View.GONE
        binding.winnerPlayerLabel.text = ""
        modelo.reiniciar()
        for (i in 0 until binding.buttonGrid.childCount) {
            (binding.buttonGrid.getChildAt(i) as Button).text = null
        }
    }




    /** Función de extensión que recibe como parámetro una lambda
     * Se exitende GridLayout para que tenga este método, el cual recorre todos los
     * Button que la componen (asunción) y para cada uno de ellos le asigna la función
     * pasada a su OnClickListener */
    private fun GridLayout.setOnClickListener( onClick: (Button) -> Unit ) {
        for (i in 0 until childCount) {
            val boton = getChildAt(i) as Button
            boton.setOnClickListener {
                onClick(boton)
            }
        }
    }


}