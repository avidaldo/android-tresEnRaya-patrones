package com.example.tresenralla.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.GridLayout
import com.example.tresenralla.R
import com.example.tresenralla.databinding.ActivityMainBinding
import com.example.tresenralla.model.Tablero.Jugador
import com.example.tresenralla.model.Tablero

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.name

    private lateinit var binding: ActivityMainBinding

    private lateinit var modelo: Tablero


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        modelo = Tablero()

        binding.buttonGrid.setOnClickListener(::onCellClicked)

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


    private fun onCellClicked(button: Button) {
        val tag = button.tag.toString()
        val row = Integer.valueOf(tag.substring(0, 1))
        val col = Integer.valueOf(tag.substring(1, 2))
        Log.i(TAG, "Click Row: [$row,$col]")
        val playerThatMoved: Jugador = modelo.marcar(row, col)
        button.text = playerThatMoved.toString()
        if (modelo.ganador != null) {
            binding.winnerPlayerLabel.text = playerThatMoved.toString()
            binding.winnerPlayerViewGroup.visibility = View.VISIBLE
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_reset -> {
                reset()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun reset() {
        binding.winnerPlayerViewGroup.visibility = View.GONE
        binding.winnerPlayerLabel.text = ""
        modelo!!.reiniciar()
        for (i in 0 until binding.buttonGrid.childCount) {
            (binding.buttonGrid.getChildAt(i) as Button).text = null
        }
    }


}