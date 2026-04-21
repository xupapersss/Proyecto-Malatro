package cl.uchile.dcc
package PokerHands
import scala.collection.mutable.ArrayBuffer

class HandPriority {

  def bestHand(cards: ArrayBuffer[Card]): PokerHand = {
    if (new StraightFlush().isStraightFlush(cards)) {
      new StraightFlush
    } else if (new Flush().isFlush(cards)) {
      new Flush
    } else if (new Straight().isStraight(cards)) {
      new Straight
    } else if (new ThreeOfaKind().isTOaK(cards)) {
      new ThreeOfaKind
    } else if (new Pair().isPair(cards)) {
      new Pair
    } else {
      new HighCard
    }
  }
}
