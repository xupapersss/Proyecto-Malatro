package cl.uchile.dcc
package controller.states

import controller.Controller

class EvaluatingState extends GameState {

  override def evaluateScore(context: Controller): Unit = {
    val score = 200
    val targetScore = 100
    val playCount = 4
    
    if (score >= targetScore)
      context.setState(new Win)

    else
      if (playCount > 3)
        context.setState(new Lose)

      else 
        context.setState(new PlayersTurn)
  }

}
