package gui

import scalafx.scene.layout.{BorderPane, VBox, Pane, HBox}
import game.{GameState, TowerDefenceGame}
import scalafx.animation.AnimationTimer

object GameView {
  def apply(): BorderPane = new BorderPane {
    val game = new TowerDefenceGame
    val state = game.gameState
    
    val purchaseView = PurchaseView(game)
   // val placeButton = PlaceButton(state.player)
    val board = GameBoardView(game, state)
    val statusBar = StatusBar(state)
    val towerInventory = TowerInventory(game)

    // class renderGameState, which renders enemies, towers and status bar
    val render = RenderGameState(game, board, statusBar)

    val topContainer = new VBox {
       children = Seq(statusBar, purchaseView)
    }   
    
    val inventoryContainer = new VBox {
      children = Seq(towerInventory)
    } 

    top = topContainer
    center = board
    right = inventoryContainer

    // override def handle(now: Long) = 
    // render.renderGame()
    var lastTime = 0L
    val guiTimer = AnimationTimer(t => {
      if lastTime > 0 then
        val delta = (t-lastTime)/1e9
        this.render.renderGame()

      lastTime = t
  })
    guiTimer.start()

  }
}

