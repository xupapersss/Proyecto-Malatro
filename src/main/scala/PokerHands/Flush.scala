package cl.uchile.dcc
package PokerHands

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class Flush extends PokerHand {
  val basechips = 35
  val basemult = 4

  def isFlush(cards: ArrayBuffer[Card]): Boolean = {
    val counter = mutable.Map[String, Int]()

    for (card <- cards) {
      val suit = card.suit.label
      counter(suit) = counter.getOrElse(suit, 0) + 1
    }
    counter.values.exists(_ == cards.length)
  }
}
