package com.example.tresenraya.viewmodel

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner


/** Intro a Mockito: https://www.youtube.com/watch?v=qkwZ6DItaNo */

@RunWith(MockitoJUnitRunner::class)
class TresEnRayaPresenterTests {

    private lateinit var presenter: TresEnRayaViewModel

    @Mock
    /** Creamos un mock de la vista, para poder probar el Presenter de modo independiente */
    private lateinit var view: ITresEnRayaView

    @Before
            /** Antes de cada test será necesario tener instanciado el presenter */
    fun setup() {
        presenter = TresEnRayaViewModel(view)
        presenter.onCreate() // Para instanciar el modelo
    }


    /** Función que indica al presentere que se ha hecho click en una celda y verifica que, como resultado,
     * la vista acaba llamando al método setButtonText para esa celda con el valor esperado como texto */
    private fun clickAndAssertValueAt(row: Int, col: Int, expectedValue: String) {
        presenter.onButtonSelected(row, col)
        verify(view).setButtonText(row, col, expectedValue)
    }

    /**
     * This test will simulate and verify o is the winner.
     *
     * X | X | X
     * O |   |
     *   | O |
     */
    @Test
    fun test3inRowAcrossTopForX() {
        clickAndAssertValueAt(0, 0, "X")
        verify(view, never()).showWinner(anyString())
        clickAndAssertValueAt(1, 0, "O")
        verify(view, never()).showWinner(anyString())
        clickAndAssertValueAt(0, 1, "X")
        verify(view, never()).showWinner(anyString())
        clickAndAssertValueAt(2, 1, "O")
        verify(view, never()).showWinner(anyString())
        clickAndAssertValueAt(0, 2, "X")
        verify(view).showWinner("X")
    }


    /**
     * This test will simulate and verify x is the winner.
     *
     * O | X | X
     * | O |
     * | X | O
     */
    @Test
    fun test3inRowDiagonalFromTopLeftToBottomForO() {
        clickAndAssertValueAt(0, 1, "X")
        verify(view, never()).showWinner(anyString())
        clickAndAssertValueAt(0, 0, "O")
        verify(view, never()).showWinner(anyString())
        clickAndAssertValueAt(2, 1, "X")
        verify(view, never()).showWinner(anyString())
        clickAndAssertValueAt(1, 1, "O")
        verify(view, never()).showWinner(anyString())
        clickAndAssertValueAt(0, 2, "X")
        verify(view, never()).showWinner(anyString())
        clickAndAssertValueAt(2, 2, "O")
        verify(view).showWinner("O")
    }

}