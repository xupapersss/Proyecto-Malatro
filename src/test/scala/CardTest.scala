package cl.uchile.dcc
import munit.FunSuite
import Suit._
import Rank._

class CardTest extends FunSuite {

  test("A Card can be created") {
    val card = new Card(new Clubs, new Ace)
    assert(card != null)
  }

  test("Card stores correct suit") {
    val suit = new Hearts
    val card = new Card(suit, new Two)

    assertEquals(card.suit, suit)
  }

  test("Card stores correct rank") {
    val rank = new King
    val card = new Card(new Spades, rank)

    assertEquals(card.rank, rank)
  }

  test("Card chips correspond to rank value (Ace)") {
    val card = new Card(new Clubs, new Ace)

    assertEquals(card.chips, 11)
  }

  test("Card chips correspond to rank value (Number card)") {
    val card = new Card(new Diamonds, new Seven)

    assertEquals(card.chips, 7)
  }

  test("Card chips correspond to rank value (Face card)") {
    val card = new Card(new Spades, new Queen)

    assertEquals(card.chips, 10)
  }

  test("Different cards are distinguishable") {
    val card1 = new Card(new Clubs, new Ace)
    val card2 = new Card(new Clubs, new King)

    assert(card1 != card2)
  }
}