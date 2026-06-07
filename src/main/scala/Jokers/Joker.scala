package cl.uchile.dcc
package Jokers
import PokerHands._
import Rank._
import Suit._



/**
 * Represents a Joker card with special behavior in the game.
 *
 * A Joker introduces custom effects that can modify how a hand is evaluated,
 * how scores are calculated, or how cards interact with each other.
 *
 * Each Joker has:
 * - a name, used to identify it
 * - an effect, which defines its behavior in the game
 */
trait Joker {

  /**
   * The name of the Joker.
   *
   * This is used to identify the Joker and distinguish it from others.
   */
  def name: String

  /**
   * The effect of the Joker.
   *
   * This method is intended to define how the Joker modifies the game,
   * such as altering scoring, changing card behavior, or affecting hand evaluation.
   *
   * NOTE: This is not yet implemented.
   */
  def effect(): Unit = {}

  def flushScore(score: Score, hand: Flush): Score
  def highCardScore(score: Score, hand: HighCard): Score
  def pairScore(score: Score, hand: Pair): Score
  def straightScore(score: Score, hand: Straight): Score
  def straightFlushScore(score: Score, hand: StraightFlush): Score
  def threeOfaKindScore(score: Score, hand: ThreeOfaKind): Score


  def clubsScore(score: Score, suit: Clubs): Score
  def diamondsScore(score: Score, suit: Diamonds): Score
  def heartsScore(score: Score, suit: Hearts): Score
  def spadesScore(score: Score, suit: Spades): Score

  def aceScore(score: Score, rank: Ace): Score
  def twoScore(score: Score, rank: Two): Score
  def threeScore(score: Score, rank: Three): Score
  def fourScore(score: Score, rank: Four): Score
  def fiveScore(score: Score, rank: Five): Score
  def sixScore(score: Score, rank: Six): Score
  def sevenScore(score: Score, rank: Seven): Score
  def eightScore(score: Score, rank: Eight): Score
  def nineScore(score: Score, rank: Nine): Score
  def tenScore(score: Score, rank: Ten): Score
  def jackScore(score: Score, rank: Jack): Score
  def queenScore(score: Score, rank: Queen): Score
  def kingScore(score: Score, rank: King): Score
}
