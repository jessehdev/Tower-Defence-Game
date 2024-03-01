package gui

import gui._
import scalafx.scene.layout.{BorderPane, VBox, Pane}
import game.{GameState, TowerDefenceGame}
import scalafx.animation.AnimationTimer

object GameView {
  def apply(): BorderPane = new BorderPane {
    val game = new TowerDefenceGame
    val state = new GameState(game)
    
    val statusBar = StatusBar(state.player, state)
    val purchaseButton = PurchaseButton(state.player)
    val board = GameBoardView(game, state)

    // class renderGameState, which renders both enemies and towers
    val render = RenderGameState(game, board)

    val topContainer: VBox = new VBox {
       children = Seq(statusBar, purchaseButton)
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

      lastTime = t
  })
    guiTimer.start()

  }
}

