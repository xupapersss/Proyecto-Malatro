package cl.uchile.dcc
package PokerHands

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class Pair extends PokerHand {
  override val basechips = 10
  override val basemult = 2

  def isPair(cards: ArrayBuffer[Card]): Boolean = {
    val counter: mutable.Map[Int, Int] = mutable.Map()

    for (card <- cards) {
      val ord = card.rank.order

      if (counter.contains(ord)) {
        counter(ord) = counter(ord) + 1
      } else {
        counter(ord) = 1
      }
    }

    counter.values.exists(_ == 2)

  }
}