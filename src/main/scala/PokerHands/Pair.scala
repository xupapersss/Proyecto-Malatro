package cl.uchile.dcc
package PokerHands

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class Pair extends RankBasedHand {
  override val basechips = 10
  override val basemult = 2

  def isPair(cards: ArrayBuffer[Card]): Boolean = {
    var counter = buildCounter(cards)

    counter.values.exists(_ == 2)
  }
}