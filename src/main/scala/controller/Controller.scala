package cl.uchile.dcc
package controller

import controller.states.{GameState, InitialSetup}

class Controller {
  private var state: GameState = new InitialSetup
  def setState(state: GameState) : Unit =
    this.state = state
  
  
  
  
  
}
