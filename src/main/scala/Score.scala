package cl.uchile.dcc

class Score (chips: Int, mult: Int) {
  def total:Int = {
    chips * mult
  }

  def printScore(): String = {
    s"Total Score = $total"
  }
}
