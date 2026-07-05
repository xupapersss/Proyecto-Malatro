package cl.uchile.dcc
package controller.states

import controller.Controller

class Win extends GameState {

  override def isWin: Boolean = true

  override def isGameOver: Boolean = true
}
