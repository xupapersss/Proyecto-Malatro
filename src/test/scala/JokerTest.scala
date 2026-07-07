package cl.uchile.dcc
import munit.FunSuite
import jokers.*
import suit.*
import rank.*
import pokerhands.*

import scala.collection.mutable.ArrayBuffer

/**
 * Test suite for the Joker classes.
 *
 * This suite verifies the proper instantiation, naming, and mathematical
 * scoring effects of every Joker implemented in the game.
 */
class JokerTest extends FunSuite {

  /** Verifies that all Joker types can be instantiated successfully. */
  test("Jokers can be created") {
    val greedy = new GreedyJoker
    val devious = new DeviousJoker
    val even = new EvenSteven
    val scary = new ScaryFace

    assert(greedy != null)
    assert(devious != null)
    assert(even != null)
    assert(scary != null)
  }

  /** Verifies that all Jokers return their expected human-readable names. */
  test("Jokers have correct names") {
    assertEquals(new GreedyJoker().name, "Greedy Joker")
    assertEquals(new DeviousJoker().name, "Devious Joker")
    assertEquals(new EvenSteven().name, "Even Steven")
    assertEquals(new ScaryFace().name, "Scary Face")
  }

  /** Verifies that distinct Joker instances are evaluated as not equal. */
  test("Different jokers are distinguishable") {
    val greedy = new GreedyJoker
    val devious = new DeviousJoker
    val even = new EvenSteven
    val scary = new ScaryFace

    assert(greedy != devious)
    assert(greedy != even)
    assert(greedy != scary)
    assert(devious != even)
    assert(devious != scary)
    assert(even != scary)
  }

  /** Verifies that Even Steven correctly adds +4 multiplier per even card. */
  test("EvenSteven works as intended"){
    val c1 = new Card(new Hearts, new Two) // Even
    val c2 = new Card(new Spades, new Two) // Even
    val c3 = new Card(new Clubs, new Four) // Even
    val c4 = new Card(new Diamonds, new Five) // Odd
    val c5 = new Card(new Hearts, new Six) // Even
    val j1 = new EvenSteven

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5),
      ArrayBuffer(j1))

    val playedPokerHand = new HandPriority(hand).bestHand
    val baseChips = playedPokerHand.basechips
    val baseMult = playedPokerHand.basemult
    val baseScore = new Score(baseChips, baseMult)

    val finalScore = j1.effect(baseScore, playedPokerHand, hand.cards)

    // 4 even cards * +4 mult = +16 mult
    assertEquals(finalScore.getMult, baseScore.getMult + 16)
  }

  /** Verifies that Greedy Joker correctly adds +3 multiplier per Diamond card. */
  test("GreedyJoker works as intended") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Diamonds, new Three) // Diamond
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)  // Diamond
    val c5 = new Card(new Hearts, new Six)
    val j1 = new GreedyJoker

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5),
      ArrayBuffer(j1))

    val playedPokerHand = new HandPriority(hand).bestHand
    val baseChips = playedPokerHand.basechips
    val baseMult = playedPokerHand.basemult
    val baseScore = new Score(baseChips, baseMult)

    val finalScore = j1.effect(baseScore, playedPokerHand, hand.cards)

    // 2 Diamond cards * +3 mult = +6 mult
    assertEquals(finalScore.getMult, baseScore.getMult + 6)
  }

  /** Verifies that Scary Face correctly adds +30 chips per Face card. */
  test("ScaryFace works as intended") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Jack) // Face Card
    val j1 = new ScaryFace

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5),
      ArrayBuffer(j1))

    val playedPokerHand = new HandPriority(hand).bestHand
    val baseChips = playedPokerHand.basechips
    val baseMult = playedPokerHand.basemult
    val baseScore = new Score(baseChips, baseMult)

    val finalScore = j1.effect(baseScore, playedPokerHand, hand.cards)

    // 1 Face card = +30 chips
    assertEquals(finalScore.getChips, baseScore.getChips + 30)
  }

  /** Verifies that Devious Joker correctly adds +100 chips specifically on a Straight. */
  test("DeviousJoker works as intended for a straight") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Six)
    val j1 = new DeviousJoker

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5),
      ArrayBuffer(j1))

    val playedPokerHand = new HandPriority(hand).bestHand
    val baseChips = playedPokerHand.basechips
    val baseMult = playedPokerHand.basemult
    val baseScore = new Score(baseChips, baseMult)

    assertEquals(baseScore.getChips, 30)
    assertEquals(baseScore.getMult, 4)
    assertEquals(baseScore.total, 120)

    val finalScore = j1.effect(baseScore, playedPokerHand, hand.cards)

    // Base (30) + 100 = 130. 130 * 4 = 520
    assertEquals(finalScore.total, 520)
  }

  /** Verifies that Devious Joker correctly adds +100 chips specifically on a Straight Flush. */
  test("DeviousJoker works as intended for a StraightFlush") {
    val c2 = new Card(new Hearts, new Three)
    val c1 = new Card(new Hearts, new Two)
    val c3 = new Card(new Hearts, new Four)
    val c4 = new Card(new Hearts, new Five)
    val c5 = new Card(new Hearts, new Six)
    val j1 = new DeviousJoker

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5),
      ArrayBuffer(j1))

    val playedPokerHand = new HandPriority(hand).bestHand
    val baseChips = playedPokerHand.basechips
    val baseMult = playedPokerHand.basemult
    val baseScore = new Score(baseChips, baseMult)

    assertEquals(baseScore.getChips, 100)
    assertEquals(baseScore.getMult, 8)
    assertEquals(baseScore.total, 800)

    val finalScore = j1.effect(baseScore, playedPokerHand, hand.cards)

    // Base (100) + 100 = 200. 200 * 8 = 1600
    assertEquals(finalScore.total, 1600)
  }

  /** Verifies that Devious Joker does not alter the score on unsupported hands. */
  test("DeviousJoker works as intended for any other PokerHand") {
    val c2 = new Card(new Hearts, new Three)
    val c1 = new Card(new Hearts, new Two)
    val c3 = new Card(new Hearts, new Four)
    val c4 = new Card(new Hearts, new Nine) // Breaks the straight
    val c5 = new Card(new Hearts, new Six)
    val j1 = new DeviousJoker

    val hand = new Hand(ArrayBuffer(c1, c2, c3, c4, c5),
      ArrayBuffer(j1))

    val playedPokerHand = new HandPriority(hand).bestHand
    val baseChips = playedPokerHand.basechips
    val baseMult = playedPokerHand.basemult
    val baseScore = new Score(baseChips, baseMult)

    val finalScore = j1.effect(baseScore, playedPokerHand, hand.cards)

    assertEquals(finalScore.total, baseScore.total)
  }
}