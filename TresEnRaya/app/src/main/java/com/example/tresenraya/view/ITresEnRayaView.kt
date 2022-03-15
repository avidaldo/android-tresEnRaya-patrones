package com.example.tresenraya.view

/** Una buena práctica es que la Activity (o Fragment) implemente una interfaz,
 * de tal manera que el presenter haga referencia a ella. Esto elimina
 * el acoplamiento hacia una vista específica y permite hacer pruebas unitarias
 * (con una implementación simulada de la vista).
 *
 * Esto nos permitirá reemplazar fácilmente la vista por cualquier otra que implemente esta interfaz
 */

interface ITresEnRayaView {
    fun showWinner(winningPlayerDisplayLabel: String)
    fun clearWinnerDisplay()
    fun clearButtons()
    fun setButtonText(row: Int, col: Int, text: String)
}