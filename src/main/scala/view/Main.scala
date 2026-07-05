package cl.uchile.dcc
package view

import controller.Controller

import cl.uchile.dcc.jokers.{DeviousJoker, EvenSteven, GreedyJoker, Joker, ScaryFace}

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

private def playerJokers(controller: Controller): Unit = {
  println("\nAvailable Jokers:")
  println("  [1] Devious Joker (+100 chips on Straight)")
  println("  [2] Even Steven (+4 mult per even card)")
  println("  [3] Greedy Joker (+3 mult per Diamond)")
  println("  [4] Scary Face (+30 chips per face card)")
  println("  [0] None")

  var validSelection = false

  while (!validSelection) {
    println("\nChoose up to 2 Jokers (e.g., '1 3' or '0'):")
    val input = readLine().trim

    if (input == "0" || input.isEmpty) {
      validSelection = true
    } else {
      val choices = extractIndexes(input)

      if (choices.length > 2) {
        println("You can only chose a maximum of 2 Jokers.")
      } else if (choices.exists(c => c < 1 || c > 4)){
        println("Invalid choice. Please enter numbers between 1 and 4.")
      } else {
        val selectedJokers = ArrayBuffer[Joker]()
        for (c <- choices) {
          c match {
            case 1 => selectedJokers += new DeviousJoker
            case 2 => selectedJokers += new EvenSteven
            case 3 => selectedJokers += new GreedyJoker
            case 4 => selectedJokers += new ScaryFace
            case _ =>
          }
        }
        controller.hand.addJokers(selectedJokers)
        validSelection = true
      }
    }
  }
}


private def playerTargetScore(): Int = {
  var score = 0
  while (score <= 0){
    print(s"Enter your target score: ")
    val input = readLine().trim
    try {
      score = input.toInt
      if (score <= 0) println("Please enter a positive number.")
    } catch {
      case _: NumberFormatException =>
        println("Invalid input. Please enter a valid number")
    }
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
    try {
      indexes += s.toInt
    } catch {
      case _: NumberFormatException =>
    }
  }
  indexes
}
