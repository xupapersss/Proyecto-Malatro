package cl.uchile.dcc
package PokerHands

import scala.collection.mutable.ArrayBuffer

trait PokerHand {
  def basechips: Int

  def basemult: Int

  def isValid(cards: ArrayBuffer[Card]): Boolean
}
