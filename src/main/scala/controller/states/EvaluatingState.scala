package cl.uchile.dcc
package controller.states

import controller.Controller

/**
 * State representing the evaluation phase after a player plays cards.
 *
 * This state checks if the player has reached the target score (Win condition)
 * or if they have exhausted their available plays without reaching it (Lose condition).
 * If neither is met, it returns the game to the player's turn.
 */
class EvaluatingState extends GameState {

  override def evaluateScore(context: Controller): Unit = {

    if (context.currentScore >= context.targetScore)
      context.setState(new Win)

    else
      if (context.hand.getPlayCount >= 3)
        context.setState(new Lose)

      else
        context.setState(new PlayersTurn)
  }

}