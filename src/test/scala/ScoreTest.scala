package cl.uchile.dcc
import munit.FunSuite

/**
 * Test suite for the Score class.
 *
 * This suite verifies that scores are successfully generated, arithmetic is
 * correctly applied (base chips * multiplier), and formatting works as expected.
 */
class ScoreTest extends FunSuite {

  /** Verifies that a Score instance can be created properly. */
  test("Score can be created") {
    val score = new Score(10, 2)
    assert(score != null)
  }

  /** Verifies the standard arithmetic logic behind total score generation. */
  test("Total is chips multiplied by mult") {
    val score = new Score(10, 2)
    assertEquals(score.total, 20)
  }

  /** Verifies that the calculation logic handles different values accurately. */
  test("Total works with different values") {
    val score = new Score(7, 3)
    assertEquals(score.total, 21)
  }

  /** Verifies that the total is properly nullified if base chips are zero. */
  test("Total is zero if chips is zero") {
    val score = new Score(0, 5)
    assertEquals(score.total, 0)
  }

  /** Verifies that the total is properly nullified if the multiplier is zero. */
  test("Total is zero if multiplier is zero") {
    val score = new Score(10, 0)
    assertEquals(score.total, 0)
  }

  /** Verifies that the formatting string returns the expected sentence. */
  test("printScore returns correct string") {
    val score = new Score(10, 2)
    assertEquals(score.printScore(), "Total Score = 20")
  }

  /** Verifies that the printed format recalculates based on its assigned parameters. */
  test("printScore reflects correct total") {
    val score = new Score(5, 5)
    assertEquals(score.printScore(), "Total Score = 25")
  }
}
