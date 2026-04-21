package cl.uchile.dcc
package PokerHands

import scala.collection.mutable.ArrayBuffer

class StraightFlush extends PokerHand {
  override val basechips = 100
  override val basemult = 8

  def isStraightFlush(cards: ArrayBuffer[Card]): Boolean = {
    val straight = new Straight
    val flush = new Flush

    straight.isStraight(cards) && flush.isFlush(cards)
  }
}