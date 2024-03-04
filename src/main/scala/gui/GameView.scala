package gui

import scalafx.scene.layout.{BorderPane, VBox, Pane, HBox}
import game.{GameState, TowerDefenceGame}
import scalafx.animation.AnimationTimer
import scalafx.geometry.Pos
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType
import scalafx.application.Platform

object GameView {
  def apply(): BorderPane = new BorderPane {
    val game = new TowerDefenceGame
    val state = game.gameState
    
    val board = GameBoardView(game, state)
    val statusBar = StatusBar(state)
    val towerInventory = TowerInventory(game)
    val upgradeView = UpgradeView(game)
    val purchaseView = PurchaseView(game, upgradeView)

    // Flags for rendering game state alerts
    var gameLostAlertShown = false
    var gameWonAlertShown = false
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

    def renderState() = 
      Platform.runLater {
        if game.gameStateLost && !gameLostAlertShown then
          gameLostAlertShown = true 
          new Alert(AlertType.Information) {
          title = "Game Lost"
          headerText = "Maybe try again?"
          contentText = s"You lasted : ${game.tickCounter/60} seconds"
          }.showAndWait()
        else if game.gameStateWon && !gameWonAlertShown then
          gameWonAlertShown = true 
          new Alert(AlertType.Information) {
          title = "Congratulations, Game Won"
          headerText = "You've defeated all the enemies!"
          contentText = s"You lasted : ${game.tickCounter/60} seconds"
          }.showAndWait()
      } 
    end renderState

    var timeCounter = 0L
    var lastTime = 0L
    val guiTimer = AnimationTimer(t => {
      timeCounter += 1
      if lastTime > 0 then
        val delta = (t-lastTime)/1e9
        this.render.renderGame()
        this.renderState()
      lastTime = t
  })
    guiTimer.start()

  }
}

