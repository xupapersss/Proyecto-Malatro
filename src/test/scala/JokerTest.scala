package cl.uchile.dcc
import munit.FunSuite
import jokers.*
import suit.*
import rank.*
import pokerhands.*

import scala.collection.mutable.ArrayBuffer

class JokerTest extends FunSuite {

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

  test("Jokers have correct names") {
    assertEquals(new GreedyJoker().name, "Greedy Joker")
    assertEquals(new DeviousJoker().name, "Devious Joker")
    assertEquals(new EvenSteven().name, "Even Steven")
    assertEquals(new ScaryFace().name, "Scary Face")
  }
  
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

  test("EvenSteven works as intended for a straight"){
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Six)
    val j1 = new EvenSteven

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

    assertEquals(finalScore.total, 480)
  }

  test("GreedyJoker works as intended for a straight") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Six)
    val j1 = new GreedyJoker

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

    assertEquals(finalScore.total, 210)
  }

  test("ScaryFace works as intended for a straight") {
    val c1 = new Card(new Hearts, new Two)
    val c2 = new Card(new Spades, new Three)
    val c3 = new Card(new Clubs, new Four)
    val c4 = new Card(new Diamonds, new Five)
    val c5 = new Card(new Hearts, new Six)
    val j1 = new ScaryFace

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

    assertEquals(finalScore.total, 120)
  }
}