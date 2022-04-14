package com.example.tresenraya.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tresenraya.model.TresEnRayaModel



class TresEnRayaViewModel() : ViewModel() {

    private var model = TresEnRayaModel()
    val modelLiveData = MutableLiveData(model)


    fun onButtonSelected(row: Int, col: Int) {
        model.marcar(row, col)
        modelLiveData.value = model
    }

    fun onResetSelected() {
        model.reiniciar()
        modelLiveData.value = model
    }
}

