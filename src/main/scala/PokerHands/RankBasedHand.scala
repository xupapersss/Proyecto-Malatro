package cl.uchile.dcc
package PokerHands

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

abstract class RankBasedHand extends PokerHand {

  def buildCounter(cards: ArrayBuffer[Card]): mutable.Map[Int, Int] = {
    val counter = mutable.Map[Int, Int]()

    for (card <- cards) {
      val ord = card.rank.order
      counter(ord) = counter.getOrElse(ord, 0) + 1
    }

    counter
  }
}
