package gui

import scalafx.scene.layout.{BorderPane, VBox, Pane, HBox}
import game.{GameState, TowerDefenceGame}
import scalafx.animation.AnimationTimer
import scalafx.geometry.Pos
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType

object GameView {
  def apply(): BorderPane = new BorderPane {
    val game = new TowerDefenceGame
    val state = game.gameState
    
    val board = GameBoardView(game, state)
    val statusBar = StatusBar(state)
    val towerInventory = TowerInventory(game)
    val upgradeView = UpgradeView(game)
    val purchaseView = PurchaseView(game, upgradeView)

    // class renderGameState, which renders enemies, towers and status bar
    val render = RenderGameState(game, board, statusBar)
    
    val transactionContainer = new HBox {
      alignment = Pos.Center
      spacing = 40
      children = Array(upgradeView, purchaseView)
    }

    val topContainer = new VBox {
      alignment = Pos.Center
      children = Array(statusBar, transactionContainer)
    }   
    
    top = topContainer
    center = board
// EI TOIMI
/*
    def renderState(): Unit =
      if game.gameStateLost == true then
        new Alert(AlertType.Information) {
        title = "Game Lost"
        headerText = s"Too bad :("
        contentText = s"Enemies managed to get to the winning area"
        }.showAndWait()
        guiTimer.stop()
      else if game.gameStateWon == true then
        new Alert(AlertType.Information) {
        title = "Game Won"
        headerText = s"Congratulations"
        contentText = s"Total time : ${timeCounter}"
        }.showAndWait()
  */

    var timeCounter = 0L
    var lastTime = 0L
    val guiTimer = AnimationTimer(t => {
      timeCounter += 1
      if lastTime > 0 then
        val delta = (t-lastTime)/1e9
        this.render.renderGame()
       // this.renderState()
      lastTime = t
  })
    guiTimer.start()

  }
}

