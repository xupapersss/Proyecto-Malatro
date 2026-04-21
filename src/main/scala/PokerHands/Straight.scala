package cl.uchile.dcc
package PokerHands

import scala.collection.mutable.ArrayBuffer

class Straight extends PokerHand {
  override val basechips = 30
  override val basemult = 4

  private def isConsecutive(orders: ArrayBuffer[Int]): Boolean = {
    val unique = orders.distinct

    if (unique.length != orders.length) {
      false
    } else {
      val sorted = unique.sorted
      var i = 0

      while (i < sorted.length - 1) {
        if (sorted(i + 1) != sorted(i) + 1) {
          return false
        }
        i += 1
      }

      true
    }
  }

  def isStraight(cards: ArrayBuffer[Card]): Boolean = {
    val orders = cards.map(_.rank.order)

    if (isConsecutive(orders)) {
      true
    } else {
      val highAceOrders = orders.map(order => if (order == 1) 14 else order)
      isConsecutive(highAceOrders)
    }
  }
}