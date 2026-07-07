package cl.uchile.dcc
package view

import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.layout.{HBox, StackPane, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.image.{Image, ImageView}
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.Button
import scalafx.scene.text.Text
import rank.*
import jokers.*
import cl.uchile.dcc.controller.Controller

object MalatroApp extends JFXApp3 {

  def createCardView(card: Card, index: Int, onClick: (Int, StackPane) => Unit): StackPane = {
    val rankStr = card.rank match {
      case _: Ace => "Ace"; case _: Two => "2"; case _: Three => "3"
      case _: Four => "4"; case _: Five => "5"; case _: Six => "6"
      case _: Seven => "7"; case _: Eight => "8"; case _: Nine => "9"
      case _: Ten => "10"; case _: Jack => "Jack"; case _: Queen => "Queen"
      case _: King => "King"; case _ => card.rank.getRankName
    }
    val fileName = s"${rankStr}_of_${card.suit.label}.png"
    val imagePath = getClass.getResource(s"/cards/$fileName")

    val imageView = if (imagePath != null) {
      new ImageView(new Image(imagePath.toString)) {
        fitWidth = 80
        preserveRatio = true
      }
    } else {
      new ImageView()
    }

    val cardPane = new StackPane { children = List(imageView) }
    cardPane.onMouseClicked = _ => onClick(index, cardPane)
    cardPane
  }

  override def start(): Unit = {
    val menuTitle = new Text("Select difficulty:") {
      fill = Color.White
      style = "-fx-font-size: 24px; -fx-font-weight: bold;"
    }

    val btnEasy = new Button("Easy (300)") {
      style = "-fx-font-size: 18px; -fx-min-width: 200px;"
      onAction = _ => stage.scene = createGameScene(300)
    }
    val btnMedium = new Button("Medium (1000)") {
      style = "-fx-font-size: 18px; -fx-min-width: 200px;"
      onAction = _ => stage.scene = createGameScene(1000)
    }
    val btnHard = new Button("Hard (5000)") {
      style = "-fx-font-size: 18px; -fx-min-width: 200px;"
      onAction = _ => stage.scene = createGameScene(5000)
    }

    val menuBox = new VBox {
      spacing = 30
      alignment = Pos.Center
      children = List(menuTitle, btnEasy, btnMedium, btnHard)
      translateX = 380
      translateY = 200
    }

    stage = new JFXApp3.PrimaryStage {
      title = "Malatro"
      scene = new Scene(1000, 700) {
        fill = Color.Grey
        content = List(menuBox)
      }
    }
  }

  def createGameScene(selectedScore: Int): Scene = {
    val gameController = new Controller()
    gameController.targetScore = selectedScore
    gameController.initialGame()

    var selectedIndexes = scala.collection.mutable.Set[Int]()
    val selectedJokers = scala.collection.mutable.Set[String]()

    val topBox = new HBox { spacing = 50; alignment = Pos.Center }
    val handBox = new HBox { spacing = 10; padding = Insets(20); alignment = Pos.Center }
    val jokerSelectionBox = new HBox { spacing = 15; alignment = Pos.Center; padding = Insets(10) }
    val actionBox = new HBox { spacing = 20; alignment = Pos.Center }

    val statusText = new Text {
      fill = Color.White
      style = "-fx-font-size: 20px; -fx-font-weight: bold;"
    }
    topBox.children = List(statusText)

    val jokerFiles = Map(
      "Devious" -> "Devious_Joker.png",
      "Even Steven" -> "Even_Steven.png",
      "Greedy" -> "Greedy_Joker.png",
      "Scary Face" -> "Scary_Face.png"
    )

    def createJokerButton(jokerId: String, fileName: String): VBox = {
      val path = getClass.getResource(s"/jokers/$fileName")
      val imgView = if (path != null) {
        new ImageView(new Image(path.toString)) {
          fitWidth = 60; preserveRatio = true
        }
      } else {
        println(s"⚠️ Error: No se encontró la imagen en /Joker/$fileName")
        new ImageView()
      }

      val container = new VBox {
        alignment = Pos.Center
        spacing = 5
        opacity = 0.4
        children = List(imgView, new Text(jokerId) { fill = Color.White })
      }

      container.onMouseClicked = _ => {
        if (selectedJokers.contains(jokerId)) {
          selectedJokers -= jokerId
          container.opacity = 0.4
        } else if (selectedJokers.size < 2) {
          selectedJokers += jokerId
          container.opacity = 1.0
        }
      }
      container
    }

    jokerSelectionBox.children = jokerFiles.map { case (name, file) => createJokerButton(name, file) }.toList

    def updateUI(): Unit = {
      selectedIndexes.clear()

      statusText.text =
        s"Target: $selectedScore | Score: ${gameController.currentScore}\n" +
          s"Plays: ${gameController.hand.getPlayCount}/3 | Discards: ${gameController.hand.getDiscardCount}/3"

      handBox.children.clear()
      val cards = gameController.hand.cards
      for (i <- cards.indices) {
        val cardNode = createCardView(cards(i), i, (clickedIndex, pane) => {
          if (selectedIndexes.contains(clickedIndex)) {
            selectedIndexes -= clickedIndex
            pane.translateY = 0
          } else if (selectedIndexes.size < 5) {
            selectedIndexes += clickedIndex
            pane.translateY = -20
          }
        })
        handBox.children += cardNode
      }

      if (gameController.isWin) {
        statusText.text = s"¡VICTORIA! Alcanzaste ${gameController.currentScore} puntos."
        statusText.fill = Color.Yellow
        handBox.children.clear()
        jokerSelectionBox.visible = false
        actionBox.visible = false
      } else if (gameController.isGameOver) {
        statusText.text = s"GAME OVER. Te quedaste sin jugadas.\nPuntaje final: ${gameController.currentScore}"
        statusText.fill = Color.Red
        handBox.children.clear()
        jokerSelectionBox.visible = false
        actionBox.visible = false
      }
    }

    val discardButton = new Button("Discard Selected") {
      style = "-fx-font-size: 16px;"
      onAction = _ => {
        if (selectedIndexes.nonEmpty) {
          val indexesToDiscard = selectedIndexes.toSeq.sorted(Ordering[Int].reverse).to(scala.collection.mutable.ArrayBuffer)
          gameController.discardCards(indexesToDiscard)
          updateUI()
        }
      }
    }

    val playButton = new Button("Play Selected") {
      style = "-fx-font-size: 16px;"
      onAction = _ => {
        if (selectedIndexes.nonEmpty) {

          gameController.hand.getHandJokers.clear()

          val jokersToAdd = scala.collection.mutable.ArrayBuffer[Joker]()
          if (selectedJokers.contains("Devious")) jokersToAdd += new DeviousJoker()
          if (selectedJokers.contains("Even Steven")) jokersToAdd += new EvenSteven()
          if (selectedJokers.contains("Greedy")) jokersToAdd += new GreedyJoker()
          if (selectedJokers.contains("Scary Face")) jokersToAdd += new ScaryFace()

          gameController.hand.addJokers(jokersToAdd)

          val indexesToPlay = selectedIndexes.toSeq.sorted(Ordering[Int].reverse).to(scala.collection.mutable.ArrayBuffer)
          gameController.playCards(indexesToPlay)
          gameController.evaluateScore()

          updateUI()
        }
      }
    }

    actionBox.children = List(playButton, discardButton)

    val mainLayout = new VBox {
      spacing = 30
      alignment = Pos.Center
      translateY = 50
      children = List(topBox, jokerSelectionBox, handBox, actionBox)
    }

    updateUI()

    new Scene(1000, 700) {
      fill = Color.Gray
      content = List(mainLayout)
    }
  }
}