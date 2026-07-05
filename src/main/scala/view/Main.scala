package cl.uchile.dcc
package view

import controller.Controller

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.readLine


object Main {
  def main(args: Array[String]): Unit = {
    println("¡Welcome to Malatro!")

    val gameController = new Controller

    gameController.targetScore = playerTargetScore()

    gameController.initialGame()

    println("Game started")

    while (!gameController.isGameOver) {

      playerAction(gameController)
    }

    if (gameController.isWin) {
      println("¡You Win!")
    } else {
      println("Game Over")
    }
  }
}

private def displayGameData(controller: Controller): Unit = {
  println(s"Target Score: ${controller.targetScore}")
  println(s"Current Score: ${controller.currentScore}")
  if (controller.hand != null) {
    println(s"Plays used: ${controller.hand.getPlayCount} / 3")

    val cards = controller.hand.cards
    println("Your Hand: ")
    for (i <- cards.indices) {
      val rankName = cards(i).rank.getRankName
      val suitName = cards(i).suit.label
      println(s" [$i] -> $rankName of $suitName")
    }
  }


}


private def playerTargetScore(): Int = {
  var score = 0
  while (score == 0){
    print(s"Enter your target score: ")
    val input = readLine().trim
    score = input.toInt

  }
  score
}

private def playerAction(controller: Controller): Unit = {
  print("Enter action (play / discard): ")
  val action = readLine().trim.toLowerCase

  if (action == "play") {
    print("¿Which cards do you want to play? (e.g., 0 1 2): ")
    val indexInput = readLine()
    val indexes = extractIndexes(indexInput)

    controller.playCards(indexes)
    controller.evaluateScore()
  }
  else if (action == "discard") {
    print("¿Which cards do you want to discard? (e.g., 0 1 2): ")
    val indexInput = readLine()
    val indexes = extractIndexes(indexInput)

    controller.discardCards(indexes)
  } else {
    println("Unknown action. Please enter 'play' or 'discard'.")
  }
}

private def extractIndexes(text: String): ArrayBuffer[Int] = {
  val parts = text.trim.split(" ")
  val indexes = ArrayBuffer[Int]()

  for (s <- parts if s.nonEmpty) {
    indexes += s.toInt
  }
  indexes
}
