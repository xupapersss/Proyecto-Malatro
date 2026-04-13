package cl.uchile.dcc
import munit.FunSuite

class ScoreTest extends FunSuite {

  test("Score can be created") {
    val score = new Score(10, 2)
    assert(score != null)
  }

  test("Total is chips multiplied by mult") {
    val score = new Score(10, 2)
    assertEquals(score.total, 20)
  }

  test("Total works with different values") {
    val score = new Score(7, 3)
    assertEquals(score.total, 21)
  }

  test("Total is zero if chips is zero") {
    val score = new Score(0, 5)
    assertEquals(score.total, 0)
  }

  test("Total is zero if multiplier is zero") {
    val score = new Score(10, 0)
    assertEquals(score.total, 0)
  }

  test("printScore returns correct string") {
    val score = new Score(10, 2)
    assertEquals(score.printScore(), "Total Score = 20")
  }

  test("printScore reflects correct total") {
    val score = new Score(5, 5)
    assertEquals(score.printScore(), "Total Score = 25")
  }
}