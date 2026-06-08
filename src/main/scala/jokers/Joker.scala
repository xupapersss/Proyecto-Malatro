package cl.uchile.dcc
package jokers
import pokerhands._
import rank._
import suit._



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

  def flushScore(score: Score, hand: Flush): Score = score 
  def highCardScore(score: Score, hand: HighCard): Score = score
  def pairScore(score: Score, hand: Pair): Score = score
  def straightScore(score: Score, hand: Straight): Score = score
  def straightFlushScore(score: Score, hand: StraightFlush): Score = score
  def threeOfaKindScore(score: Score, hand: ThreeOfaKind): Score = score

  
  def clubsScore(score: Score, suit: Clubs): Score = score
  def diamondsScore(score: Score, suit: Diamonds): Score = score
  def heartsScore(score: Score, suit: Hearts): Score = score
  def spadesScore(score: Score, suit: Spades): Score = score
  
  
  def aceScore(score: Score, rank: Ace): Score = score
  def twoScore(score: Score, rank: Two): Score = score
  def threeScore(score: Score, rank: Three): Score = score
  def fourScore(score: Score, rank: Four): Score = score
  def fiveScore(score: Score, rank: Five): Score = score
  def sixScore(score: Score, rank: Six): Score = score
  def sevenScore(score: Score, rank: Seven): Score = score
  def eightScore(score: Score, rank: Eight): Score = score
  def nineScore(score: Score, rank: Nine): Score = score
  def tenScore(score: Score, rank: Ten): Score = score
  def jackScore(score: Score, rank: Jack): Score = score
  def queenScore(score: Score, rank: Queen): Score = score
  def kingScore(score: Score, rank: King): Score = score
}
