package cl.uchile.dcc

import cl.uchile.dcc.PokerHands.HighCard
import cl.uchile.dcc.Rank._
import cl.uchile.dcc.Suit._
import munit.FunSuite

import scala.collection.mutable.ArrayBuffer

class PokerHandTest extends FunSuite {

  test("isHCard returns true for any hand") {
    val cards = ArrayBuffer(
      new Card(new Hearts, new Two),
      new Card(new Spades, new Five)
    )
    val result = new HighCard().isHCard(cards)

    assert(result)
  }
}
