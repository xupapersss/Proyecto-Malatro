package cl.uchile.dcc
import munit.FunSuite
import suit._
import rank._

/**
 * Test suite for the Card class.
 *
 * This suite verifies the correct instantiation of cards, proper storage of
 * suits and ranks, correct chip value assignments, and the string representation
 * of the cards.
 */
class CardTest extends FunSuite {

  /** Verifies that a Card object can be properly instantiated. */
  test("A Card can be created") {
    val card = new Card(new Clubs, new Ace)
    assert(card != null)
  }

  /** Verifies that a Card correctly stores and returns its assigned suit. */
  test("Card stores correct suit") {
    val suit = new Hearts
    val card = new Card(suit, new Two)

    assertEquals(card.suit, suit)
  }

  /** Verifies that a Card correctly stores and returns its assigned rank. */
  test("Card stores correct rank") {
    val rank = new King
    val card = new Card(new Spades, rank)

    assertEquals(card.rank, rank)
  }

  /** Verifies that the chip value of an Ace corresponds to its rank value. */
  test("Card chips correspond to rank value (Ace)") {
    val card = new Card(new Clubs, new Ace)

    assertEquals(card.chips, 11)
  }

  /** Verifies that the chip value of a number card corresponds to its rank value. */
  test("Card chips correspond to rank value (Number card)") {
    val card = new Card(new Diamonds, new Seven)

    assertEquals(card.chips, 7)
  }

  /** Verifies that the chip value of a face card corresponds to its rank value. */
  test("Card chips correspond to rank value (Face card)") {
    val card = new Card(new Spades, new Queen)

    assertEquals(card.chips, 10)
  }

  /** Verifies that two distinct Card instances are evaluated as not equal. */
  test("Different cards are distinguishable") {
    val card1 = new Card(new Clubs, new Ace)
    val card2 = new Card(new Clubs, new King)

    assert(card1 != card2)
  }

  /** Verifies that the cardName method returns the correct human-readable string representation of the card. */
  test("cardName returns the correct string format") {
    val card1 = new Card(new Spades, new Ace)
    assertEquals(card1.cardName, "Ace of Spades")

    val card2 = new Card(new Hearts, new Seven)
    assertEquals(card2.cardName, "Seven of Hearts")

    val card3 = new Card(new Diamonds, new King)
    assertEquals(card3.cardName, "King of Diamonds")
  }
}