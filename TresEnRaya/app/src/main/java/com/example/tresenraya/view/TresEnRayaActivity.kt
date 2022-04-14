package com.example.tresenraya.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.tresenraya.R
import com.example.tresenraya.databinding.ActivityMainBinding
import com.example.tresenraya.viewmodel.TresEnRayaViewModel


class TresEnRayaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: TresEnRayaViewModel by viewModels() // Instanciamos el viewModel con la activity

    /**
     * viewModels() devuelve una propiedad delegada Lazy para acceder al ViewModel
     *
     * https://kotlinlang.org/docs/delegated-properties.html
     * https://en.wikipedia.org/wiki/Delegation_pattern
     *
     */


    /***************************************************************************************
     * Métodos del ciclo de vida
     ***************************************************************************************/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGrid.setOnClickListener(::onCellClicked)


        viewModel.modelLiveData.observe(this, Observer {

            binding.btn00.text = it.tablero[0][0].toString()
            binding.btn01.text = it.tablero[0][1].toString()
            binding.btn02.text = it.tablero[0][2].toString()
            binding.btn10.text = it.tablero[1][0].toString()
            binding.btn11.text = it.tablero[1][1].toString()
            binding.btn12.text = it.tablero[1][2].toString()
            binding.btn20.text = it.tablero[2][0].toString()
            binding.btn21.text = it.tablero[2][1].toString()
            binding.btn22.text = it.tablero[2][2].toString()


            binding.winnerPlayerViewGroup.visibility =
                it.ganador?.let {
                    binding.winnerPlayerLabel.text = it.toString()
                    View.VISIBLE
                } ?: View.GONE

        })

    }


    /***************************************************************************************
     * Definición del menú de reset
     ***************************************************************************************/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_reset -> {
                viewModel.onResetSelected(); true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    /***************************************************************************************
     * Definición del comportamiento de la vista ante eventos (cuando se tocan las celdas)
     ***************************************************************************************/

    /** Método que indica al viewModel qué celda se ha pulsado */
    private fun onCellClicked(button: Button) {
        val tag = button.tag.toString().toCharArray()
        val row = tag[0].digitToInt()
        val col = tag[1].digitToInt()
        viewModel.onButtonSelected(row, col)
    }


    /** Función de extensión que recibe como parámetro una lambda
     * Se extiende GridLayout para que tenga este método, el cual recorre todos los Button que la componen (asunción) y para cada uno de ellos le
     * asigna la función pasada a su OnClickListener */
    private fun GridLayout.setOnClickListener(onClick: (Button) -> Unit) {
        for (i in 0 until childCount) {
            val boton = getChildAt(i) as Button
            boton.setOnClickListener {
                onClick(boton)
            }
        }
    }


}