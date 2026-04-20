package cl.uchile.dcc
package PokerHands

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class ThreeOfaKind extends PokerHand {
  val basechips = 30
  val basemult = 3

  def isTOaK(cards: ArrayBuffer[Card]): Boolean = {
    val counter: mutable.Map[Int, Int] = mutable.Map()

    for (card <- cards) {
      val ord = card.rank.order

      if (counter.contains(ord)) {
        counter(ord) = counter(ord) + 1
      } else {
        counter(ord) = 1
      }
    }

    counter.values.exists(_ == 3)

  }
}

