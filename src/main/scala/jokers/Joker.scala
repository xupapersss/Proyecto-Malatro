package cl.uchile.dcc
package jokers
import pokerhands.*
import rank.*
import suit.*

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
  def effect(score: Score, hand: PokerHand, cards: List[Card]): Score

  /**
   * Evaluates and applies score modifications when interacting with a Flush hand.
   *
   * @param score the current score
   * @param hand  the Flush hand being evaluated
   * @return the updated score
   */
  def flushScore(score: Score, hand: Flush): Score = score

  /**
   * Evaluates and applies score modifications when interacting with a High Card hand.
   *
   * @param score the current score
   * @param hand  the High Card hand being evaluated
   * @return the updated score
   */
  def highCardScore(score: Score, hand: HighCard): Score = score

  /**
   * Evaluates and applies score modifications when interacting with a Pair hand.
   *
   * @param score the current score
   * @param hand  the Pair hand being evaluated
   * @return the updated score
   */
  def pairScore(score: Score, hand: Pair): Score = score

  /**
   * Evaluates and applies score modifications when interacting with a Straight hand.
   *
   * @param score the current score
   * @param hand  the Straight hand being evaluated
   * @return the updated score
   */
  def straightScore(score: Score, hand: Straight): Score = score

  /**
   * Evaluates and applies score modifications when interacting with a Straight Flush hand.
   *
   * @param score the current score
   * @param hand  the Straight Flush hand being evaluated
   * @return the updated score
   */
  def straightFlushScore(score: Score, hand: StraightFlush): Score = score

  /**
   * Evaluates and applies score modifications when interacting with a Three of a Kind hand.
   *
   * @param score the current score
   * @param hand  the Three of a Kind hand being evaluated
   * @return the updated score
   */
  def threeOfaKindScore(score: Score, hand: ThreeOfaKind): Score = score

  /**
   * Evaluates and applies score modifications when interacting with the Clubs suit.
   *
   * @param score the current score
   * @param suit  the Clubs suit being evaluated
   * @return the updated score
   */
  def clubsScore(score: Score, suit: Clubs): Score = score

  /**
   * Evaluates and applies score modifications when interacting with the Diamonds suit.
   *
   * @param score the current score
   * @param suit  the Diamonds suit being evaluated
   * @return the updated score
   */
  def diamondsScore(score: Score, suit: Diamonds): Score = score

  /**
   * Evaluates and applies score modifications when interacting with the Hearts suit.
   *
   * @param score the current score
   * @param suit  the Hearts suit being evaluated
   * @return the updated score
   */
  def heartsScore(score: Score, suit: Hearts): Score = score

  /**
   * Evaluates and applies score modifications when interacting with the Spades suit.
   *
   * @param score the current score
   * @param suit  the Spades suit being evaluated
   * @return the updated score
   */
  def spadesScore(score: Score, suit: Spades): Score = score

  /**
   * Evaluates and applies score modifications when interacting with an Ace rank.
   *
   * @param score the current score
   * @param rank  the Ace rank being evaluated
   * @return the updated score
   */
  def aceScore(score: Score, rank: Ace): Score = score

  /**
   * Evaluates and applies score modifications when interacting with a Two rank.
   *
   * @param score the current score
   * @param rank  the Two rank being evaluated
   * @return the updated score
   */
  def twoScore(score: Score, rank: Two): Score = score

  /**
   * Evaluates and applies score modifications when interacting with a Three rank.
   *
   * @param score the current score
   * @param rank  the Three rank being evaluated
   * @return the updated score
   */
  def threeScore(score: Score, rank: Three): Score = score

  /**
   * Evaluates and applies score modifications when interacting with a Four rank.
   *
   * @param score the current score
   * @param rank  the Four rank being evaluated
   * @return the updated score
   */
  def fourScore(score: Score, rank: Four): Score = score

  /**
   * Evaluates and applies score modifications when interacting with a Five rank.
   *
   * @param score the current score
   * @param rank  the Five rank being evaluated
   * @return the updated score
   */
  def fiveScore(score: Score, rank: Five): Score = score

  /**
   * Evaluates and applies score modifications when interacting with a Six rank.
   *
   * @param score the current score
   * @param rank  the Six rank being evaluated
   * @return the updated score
   */
  def sixScore(score: Score, rank: Six): Score = score

  /**
   * Evaluates and applies score modifications when interacting with a Seven rank.
   *
   * @param score the current score
   * @param rank  the Seven rank being evaluated
   * @return the updated score
   */
  def sevenScore(score: Score, rank: Seven): Score = score

  /**
   * Evaluates and applies score modifications when interacting with an Eight rank.
   *
   * @param score the current score
   * @param rank  the Eight rank being evaluated
   * @return the updated score
   */
  def eightScore(score: Score, rank: Eight): Score = score

  /**
   * Evaluates and applies score modifications when interacting with a Nine rank.
   *
   * @param score the current score
   * @param rank  the Nine rank being evaluated
   * @return the updated score
   */
  def nineScore(score: Score, rank: Nine): Score = score

  /**
   * Evaluates and applies score modifications when interacting with a Ten rank.
   *
   * @param score the current score
   * @param rank  the Ten rank being evaluated
   * @return the updated score
   */
  def tenScore(score: Score, rank: Ten): Score = score

  /**
   * Evaluates and applies score modifications when interacting with a Jack rank.
   *
   * @param score the current score
   * @param rank  the Jack rank being evaluated
   * @return the updated score
   */
  def jackScore(score: Score, rank: Jack): Score = score

  /**
   * Evaluates and applies score modifications when interacting with a Queen rank.
   *
   * @param score the current score
   * @param rank  the Queen rank being evaluated
   * @return the updated score
   */
  def queenScore(score: Score, rank: Queen): Score = score

  /**
   * Evaluates and applies score modifications when interacting with a King rank.
   *
   * @param score the current score
   * @param rank  the King rank being evaluated
   * @return the updated score
   */
  def kingScore(score: Score, rank: King): Score = score

  /**
   * Evaluates and applies score modifications directly to a given Card.
   *
   * @param score the current score
   * @param card  the Card instance being evaluated
   * @return the updated score
   */
  def cardScore(score: Score, card: Card): Score = score
}
