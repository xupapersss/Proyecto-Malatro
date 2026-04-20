package cl.uchile.dcc
package PokerHands

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class ThreeOfaKind extends RankBasedHand {
  val basechips = 30
  val basemult = 3
  
  def isTOaK(cards: ArrayBuffer[Card]): Boolean =
    var counter = buildCounter(cards)
  
    counter.values.exists(_ == 3)
}

