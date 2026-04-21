package cl.uchile.dcc
package PokerHands

import scala.collection.mutable.ArrayBuffer

class Straight extends PokerHand {
  val basechips = 30
  val basemult = 4

  def isStraight(cards: ArrayBuffer[Card]): Boolean = {
    val orders = cards.map(_.rank.order)
    val unique = orders.distinct

    if (unique.length != cards.length)
      return false
    else {
      var i = 0
      val sorted = unique.sorted

      while (i < sorted.length - 1) {
        if (sorted(i + 1) != sorted(i) + 1) {
          return false
        }
        i += 1
      }
    }

    true
  }
}