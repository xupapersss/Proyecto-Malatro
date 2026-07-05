package cl.uchile.dcc
package controller.states

import controller.Controller

import cl.uchile.dcc.pokerhands.HandPriority

import scala.collection.mutable.ArrayBuffer

class PlayersTurn extends GameState {

  override def playCards(context: Controller, indexes: ArrayBuffer[Int]): Unit = {
    
    val playedCards = context.hand.playHand(indexes)
    
    val bestHand = new HandPriority(context.hand).bestHand
    
    val scoreGained = context.hand.handScore(playedCards,bestHand)
    context.currentScore += scoreGained
    
    context.drawCards()
    
    context.setState(new EvaluatingState)
  }

  override def discardCards(context: Controller, indexes: ArrayBuffer[Int]): Unit = {
    
    context.hand.discardCards(indexes)
    
    context.drawCards()
  }
}
