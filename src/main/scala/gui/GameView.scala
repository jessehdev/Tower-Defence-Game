package gui

import scalafx.scene.layout.{BorderPane, VBox, Pane, HBox}
import game.{GameState, TowerDefenceGame}
import scalafx.animation.AnimationTimer
import scalafx.geometry.Pos
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType
import scalafx.application.Platform
import scalafx.scene.control.Button
import scala.compiletime.ops.double

/*
 * GameView essentially encapsulates all of the views and entities in a game
 * It creates the one and only "TowerDefenceGame" object in the game
 * It also works as the root in the "Main.scala" (found in gui) 
 */
object GameView {
  def apply(): BorderPane = new BorderPane {
    val game = new TowerDefenceGame
    val state = game.gameState
    
    val board = GameBoardView(game, state)
    val statusBar = StatusBar(state)

    val upgradeView = UpgradeView(game)
    val purchaseView = PurchaseView(game, upgradeView)
    val startGameView = StartGameView(game, purchaseView)

    // Flags for rendering game state alerts
    var gameLostAlertShown = false
    var gameWonAlertShown = false

    val towerRenderer = TowerRenderer(game, board, this) 
    // class renderGameState, which renders enemies, towers and status bar
    val render = RenderGameState(game, board, statusBar, towerRenderer)
    
    //has the containers for upgrading and purchasing towers + starting the game
    val transactionContainer = new HBox {
      alignment = Pos.Center
      spacing = 40
      children = Array(startGameView,upgradeView, purchaseView)
    }
   
    val topContainer = new VBox {
      alignment = Pos.Center
      children = Array(statusBar, transactionContainer)
    }   
    
    top = topContainer
    center = board
//shows messages for winning/losing the game and stops the continuation of the game
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
  
    def animateShootings() =
       if game.towerShootingsMap.nonEmpty then
         for tower <- game.towerShootingsMap do
           //takes the enemies that the tower in question is about to shoot
           val enemiesToShoot = tower._2
           for enemy <- enemiesToShoot do
             towerRenderer.animateShooting(tower._1, enemy)
             
  /*
   * A scalafx animationtimer for updating (rendering) the gui 
   */
    var timeCounter = 0L
    var lastTime = 0L
    val guiTimer = AnimationTimer(t => {
      timeCounter += 1
      if lastTime > 0 then
        val delta = (t-lastTime)/1e9
        this.render.renderGame()
        this.renderState()
        animateShootings()
      lastTime = t
  })
    guiTimer.start()

  }
}

