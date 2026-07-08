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
import scalafx.scene.media.{Media, MediaPlayer, MediaView}
import rank.*
import jokers.*
import cl.uchile.dcc.controller.Controller
import pokerhands.*
import scala.collection.mutable.ArrayBuffer

object MalatroApp extends JFXApp3 {

  private var backgroundPlayer: MediaPlayer = _

  private val bgPane = new StackPane()
  private val contentPane = new StackPane()

  private val mainRoot = new StackPane {
    children = List(bgPane, contentPane)
  }

  private def setBackgroundVideo(fileName: String): Unit = {
    if (backgroundPlayer != null) {
      backgroundPlayer.stop()
      backgroundPlayer.dispose()
    }

    val videoUrl = getClass.getResource(s"/images/$fileName")
    if (videoUrl != null) {
      val media = new Media(videoUrl.toExternalForm)
      backgroundPlayer = new MediaPlayer(media)
      backgroundPlayer.cycleCount = MediaPlayer.Indefinite
      backgroundPlayer.play()

      val bgView = new MediaView(backgroundPlayer) {
        preserveRatio = false
        mouseTransparent = true
      }

      bgView.fitWidth.bind(mainRoot.width)
      bgView.fitHeight.bind(mainRoot.height)

      bgPane.children = List(bgView)
    } else {
      println(s"⚠️ Error: No se encontró el video en /images/$fileName")
      bgPane.children.clear()
    }
  }



  private def createCardView(card: Card, index: Int, onClick: (Int, StackPane) => Unit): StackPane = {
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
    stage = new JFXApp3.PrimaryStage {
      title = "Malatro"
      scene = new Scene(1000, 700) {
        fill = Color.DarkSlateGray
        root = mainRoot
      }
    }

    setBackgroundVideo("balatrofondo.mp4")

    contentPane.children = List(createTitleView())
  }


  private def createTitleView(): StackPane = {
    val titleText = new Text("MALATRO") {
      fill = Color.Black
      style = "-fx-font-size: 100px; -fx-font-weight: bold; -fx-font-family: monospace;"
    }

    val promptText = new Text("Click anywhere to start") {
      fill = Color.Yellow
      style = "-fx-font-size: 24px;"
    }

    val gifPath = getClass.getResource("/images/BALATROBALATRO.gif")
    val gifView = if (gifPath != null) {
      new ImageView(new Image(gifPath.toString)) {
        fitWidth = 600
        preserveRatio = true
        mouseTransparent = true
      }
    } else {
      println("⚠️ Error: Couldn't found /images/BALATROBALATRO.gif")
      new ImageView()
    }

    val textBox = new VBox {
      spacing = 300
      alignment = Pos.Center
      mouseTransparent = true
      children = List(titleText, promptText)
    }

    val titleContainer = new StackPane {
      children = List(gifView, textBox)
    }

    titleContainer.onMouseClicked = _ => {
      contentPane.children = List(createDifficultyView())
    }

    titleContainer
  }

  private def createDifficultyView(): StackPane = {
    val menuTitle = new Text("Select difficulty:") {
      fill = Color.White
      style = "-fx-font-size: 24px; -fx-font-weight: bold;"
    }

    val btnEasy = new Button("Easy (300)") {
      style = "-fx-font-size: 18px; -fx-min-width: 200px;"
      onAction = _ => contentPane.children = List(createGameView(300))
    }
    val btnMedium = new Button("Medium (1000)") {
      style = "-fx-font-size: 18px; -fx-min-width: 200px;"
      onAction = _ => contentPane.children = List(createGameView(1000))
    }
    val btnHard = new Button("Hard (5000)") {
      style = "-fx-font-size: 18px; -fx-min-width: 200px;"
      onAction = _ => contentPane.children = List(createGameView(5000))
    }

    val menuBox = new VBox {
      spacing = 30
      alignment = Pos.Center
      children = List(menuTitle, btnEasy, btnMedium, btnHard)
    }

    new StackPane {
      children = List(menuBox)
    }
  }

  private def createGameView(selectedScore: Int): StackPane = {
    setBackgroundVideo("Gameplayfondo.mp4")

    val gameController = new Controller()
    gameController.targetScore = selectedScore
    gameController.initialGame()

    var selectedIndexes = scala.collection.mutable.Set[Int]()
    val selectedJokers = scala.collection.mutable.Set[String]()

    val topBox = new HBox { spacing = 50; alignment = Pos.Center }
    val handBox = new HBox { spacing = 10; padding = Insets(20); alignment = Pos.Center }
    val jokerSelectionBox = new HBox { spacing = 15; alignment = Pos.Center; padding = Insets(10) }
    val sortBox = new HBox { spacing = 15; alignment = Pos.Center }
    val actionBox = new HBox { spacing = 20; alignment = Pos.Center }

    val statusText = new Text {
      fill = Color.White
      style = "-fx-font-size: 20px; -fx-font-weight: bold;"
    }
    topBox.children = List(statusText)

    val currentHandText = new Text("Selected Hand: None") {
      fill = Color.Cyan
      style = "-fx-font-size: 18px; -fx-font-weight: bold;"
    }

    val selectionInfoBox = new HBox {
      spacing = 10
      alignment = Pos.Center
      children = List(currentHandText)
    }

    val winGif = new ImageView()
    val winPath = getClass.getResource("/images/hog-exploding.gif")
    if (winPath != null) winGif.image = new Image(winPath.toString)
    winGif.fitWidth = 250;
    winGif.preserveRatio = true

    val loseGif1 = new ImageView()
    val losePath1 = getClass.getResource("/images/Pancho_Melo_Llorando_y_rompiendo_todo.gif")
    if (losePath1 != null) loseGif1.image = new Image(losePath1.toString)
    loseGif1.fitWidth = 300;
    loseGif1.preserveRatio = true

    val loseGif2 = new ImageView()
    val losePath2 = getClass.getResource("/images/monkey-confused.gif")
    if (losePath2 != null) loseGif2.image = new Image(losePath2.toString)
    loseGif2.fitWidth = 250;
    loseGif2.preserveRatio = true

    val endGameMediaBox = new HBox {
      spacing = 30
      alignment = Pos.Center
      visible = false
      managed = false
    }


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
        println(s"⚠️ Error: Couldn't found the image in /jokers/$fileName")
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

    def updateSelectedHandText(): Unit = {
      if (selectedIndexes.isEmpty) {
        currentHandText.text = "Selected Hand: None"
      } else {
        val currentCards = gameController.hand.cards
        val selectedCards = ArrayBuffer.from(
          selectedIndexes.toList.map(index => currentCards(index))
        )
        val tempHand = new Hand(selectedCards, ArrayBuffer())
        val bestPokerHand = HandPriority(tempHand).bestHand

        currentHandText.text = s"Selected Hand: ${bestPokerHand.pokerHandName}"
      }
    }

    val returnButton = new Button("Return to Main Menu") {
      style = "-fx-font-size: 18px; -fx-padding: 10px 20px;"
      visible = false
      managed = false
      onAction = _ => {
        setBackgroundVideo("balatrofondo.mp4")
        contentPane.children = List(createTitleView())
      }
    }

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
          updateSelectedHandText()
        })
        handBox.children += cardNode
      }

      updateSelectedHandText()

      if (gameController.isWin) {
        statusText.text = s"¡YOU WIN! You reached ${gameController.currentScore} points."
        statusText.fill = Color.Yellow
        handBox.children.clear()

        jokerSelectionBox.visible = false;
        jokerSelectionBox.managed = false
        actionBox.visible = false;
        actionBox.managed = false
        selectionInfoBox.visible = false;
        selectionInfoBox.managed = false
        sortBox.visible = false;
        sortBox.managed = false
        handBox.managed = false

        endGameMediaBox.children = List(winGif)
        endGameMediaBox.visible = true;
        endGameMediaBox.managed = true
        returnButton.visible = true;
        returnButton.managed = true

      } else if (gameController.isGameOver) {
        statusText.text = s"GAME OVER. You are out of plays.\nFinal score: ${gameController.currentScore}"
        statusText.fill = Color.Red
        handBox.children.clear()

        jokerSelectionBox.visible = false;
        jokerSelectionBox.managed = false
        actionBox.visible = false;
        actionBox.managed = false
        selectionInfoBox.visible = false;
        selectionInfoBox.managed = false
        sortBox.visible = false;
        sortBox.managed = false
        handBox.managed = false

        endGameMediaBox.children = List(loseGif1, loseGif2)
        endGameMediaBox.visible = true;
        endGameMediaBox.managed = true
        returnButton.visible = true;
        returnButton.managed = true
      }
    }


    val sortRankButton = new Button("Sort by Rank") {
      style = "-fx-font-size: 14px;"
      onAction = _ => {
        gameController.hand.getHandCards.sortInPlaceBy(_.rank.value)
        updateUI()
      }
    }

    val sortSuitButton = new Button("Sort by Suit") {
      style = "-fx-font-size: 14px;"
      onAction = _ => {
        def suitToNumber(label: String): Int = label.toLowerCase match {
          case "spades" => 1
          case "hearts" => 2
          case "clubs" => 3
          case "diamonds" => 4
          case _ => 5
        }
        gameController.hand.getHandCards.sortInPlaceBy(card => suitToNumber(card.suit.label))
        updateUI()
      }
    }

    sortBox.children = List(sortRankButton, sortSuitButton)

    val discardButton = new Button("Discard") {
      style = "-fx-font-size: 16px;"
      onAction = _ => {
        if (selectedIndexes.nonEmpty) {
          val indexesToDiscard = selectedIndexes.toSeq.sorted(Ordering[Int].reverse).to(scala.collection.mutable.ArrayBuffer)
          gameController.discardCards(indexesToDiscard)
          updateUI()
        }
      }
    }

    val playButton = new Button("Play") {
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
      children = List(topBox, jokerSelectionBox, handBox, sortBox, selectionInfoBox, actionBox, endGameMediaBox, returnButton)
    }

    updateUI()

    new StackPane {
      children = List(mainLayout)
    }
  }
}