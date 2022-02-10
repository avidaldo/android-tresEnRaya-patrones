package com.example.tresenraya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tresenraya.databinding.ActivityMainBinding


/** En esta rama solo se define el modelo y se ejemplifican tests de JUnit sobre él
 * (ver TicTacToeTests). Dejaremos el modelo en Java con mínimas modificaciones,
 * ejemplificando una situación real en la que se reutiliza un modelo existente
 * en un nuevo proyecto con la vista que se quiera. El modelo no está de ningún
 * modo vinculado a librerías de Android.
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

}