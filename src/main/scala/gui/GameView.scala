package gui

import scalafx.scene.layout.{BorderPane, VBox, Pane, HBox}
import game.{GameState, TowerDefenceGame}
import scalafx.animation.AnimationTimer
import scalafx.geometry.Pos

object GameView {
  def apply(): BorderPane = new BorderPane {
    val game = new TowerDefenceGame
    val state = game.gameState
    
    val board = GameBoardView(game, state)
    val statusBar = StatusBar(state)
    val towerInventory = TowerInventory(game)
    val purchaseView = PurchaseView(game)
    val upgradeView = UpgradeView(game)
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

    // override def handle(now: Long) = 
    // render.renderGame()
    var lastTime = 0L
    val guiTimer = AnimationTimer(t => {
      if lastTime > 0 then
        val delta = (t-lastTime)/1e9
        this.render.renderGame()
        //println("rendering game state")

      lastTime = t
  })
    guiTimer.start()

  }
}

