package cl.uchile.dcc
package controller.states

import controller.Controller

class EvaluatingState extends GameState {

  override def evaluateScore(context: Controller): Unit = {

    if (context.currentScore >= context.targetScore)
      context.setState(new Win)

    else
      if (context.hand.getPlayCount > 3)
        context.setState(new Lose)

      else
        context.setState(new PlayersTurn)
  }

}
