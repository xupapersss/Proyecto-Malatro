package cl.uchile.dcc
package view

import controller.Controller

import cl.uchile.dcc.jokers.{DeviousJoker, EvenSteven, GreedyJoker, Joker, ScaryFace}

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.readLine

/**
 * Main entry point for the console-based Malatro game.
 *
 * This object manages the interactive command-line interface (CLI) for the player,
 * handling input, displaying the game state, and communicating with the Controller.
 */
object Main {

  /**
   * The main execution loop of the game.
   *
   * Initializes the controller, sets up the game parameters (target score and jokers),
   * and runs the main turn loop until a game over condition is met.
   *
   * @param args command-line arguments (not used)
   */
  def main(args: Array[String]): Unit = {
    println("¡Welcome to Malatro!")

    val gameController = new Controller

    gameController.targetScore = playerTargetScore()

    gameController.initialGame()

    playerJokers(gameController)

    println("Game started")

    while (!gameController.isGameOver) {
      displayGameData(gameController)
      playerAction(gameController)
    }

    displayGameData(gameController)

    if (gameController.isWin) {
      println("¡You Win!")
    } else {
      println("Game Over")
    }
  }
}

/**
 * Displays the current state of the game to the player.
 *
 * This includes the target score, current score, remaining plays,
 * remaining discards, the cards currently held in the hand, and any active Jokers.
 *
 * @param controller the main game controller holding the state
 */
private def displayGameData(controller: Controller): Unit = {
  println(s"Target Score: ${controller.targetScore}")
  println(s"Current Score: ${controller.currentScore}")
  if (controller.hand != null) {
    println(s"Plays used: ${controller.hand.getPlayCount} / 3")
    println(s"Discards used: ${controller.hand.getDiscardCount} / 3")

    val cards = controller.hand.handCards
    println("Your Hand: ")
    if (cards.isEmpty) {
      println("  (Empty)")
    } else {
      for (i <- cards.indices) {
        println(s" [$i] -> ${cards(i).cardName}")
      }
    }

    val jokers = controller.hand.jokers
    if (jokers.nonEmpty) {
      println("\nActive Jokers: ")
      for (i <- jokers.indices) {
        println(s" [*] -> ${jokers(i).name}")
      }
    }
  }
}

/**
 * Prompts the player to select up to two Jokers for their initial setup.
 *
 * Reads user input from the console and instantiates the chosen Jokers,
 * adding them to the controller's hand.
 *
 * @param controller the main game controller
 */
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

/**
 * Prompts the player to define the target score required to win the game.
 *
 * Ensures that the inputted score is a valid positive integer.
 *
 * @return the chosen target score
 */
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

/**
 * Prompts the player for their next move (play or discard) and the target cards.
 *
 * Processes the text input and forwards the command to the controller.
 *
 * @param controller the main game controller
 */
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

/**
 * Converts a space-separated string of numbers into an ArrayBuffer of integers.
 *
 * Useful for parsing card index selections from the console.
 *
 * @param text the raw string input from the user
 * @return an ArrayBuffer containing the parsed integers
 */
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