package com.example.tresenraya.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.tresenraya.R
import com.example.tresenraya.databinding.ActivityMainBinding
import com.example.tresenraya.viewmodel.TresEnRayaViewModel
import androidx.activity.viewModels



class TresEnRayaActivity : AppCompatActivity(){
    private val TAG = TresEnRayaActivity::class.java.name

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

        presenter.onCreate()
    }


    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


    /***************************************************************************************
     * Definición del menú de reset
     ***************************************************************************************/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_reset -> { presenter.onResetSelected(); true }
            else -> super.onOptionsItemSelected(item)
        }
    }



    /***************************************************************************************
     * Métodos que serán llamados desde el presenter para modificar la vista
     ***************************************************************************************/



    override fun setButtonText(row: Int, col: Int, text: String) {
        val btn = binding.buttonGrid.findViewWithTag<View>("$row$col") as Button
        btn.text = text
    }

    override fun showWinner(winningPlayerDisplayLabel: String) {
        binding.winnerPlayerLabel.text = winningPlayerDisplayLabel
        binding.winnerPlayerViewGroup.visibility = View.VISIBLE
    }

    override fun clearWinnerDisplay() {
        binding.winnerPlayerViewGroup.visibility = View.GONE
        binding.winnerPlayerLabel.text = ""
    }

    override fun clearButtons() {
        for (i in 0 until binding.buttonGrid.childCount) {
            (binding.buttonGrid.getChildAt(i) as Button).text = null
        }
    }



    /***************************************************************************************
     * Definición del comportamiento de la vista ante eventos (cuando se tocan las celdas)
     ***************************************************************************************/

    /** Método que indica al presenter qué celda se ha pulsado */
    private fun onCellClicked(button: Button) {
        val tag = button.tag.toString().toCharArray()
        val row = tag[0].digitToInt()
        val col = tag[1].digitToInt()
        Log.i(TAG, "Click Row: [$row,$col]")
        presenter.onButtonSelected(row, col)
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