package cl.uchile.dcc
package PokerHands

import scala.collection.mutable.ArrayBuffer

class HighCard extends PokerHand {
  override val basechips = 5
  override val basemult = 1
  
  def isHCard(cards: ArrayBuffer[Card]): Boolean = true
}
