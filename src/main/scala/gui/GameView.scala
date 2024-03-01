package gui

import gui._
import scalafx.scene.layout.{BorderPane, VBox, Pane}
import game.{GameState, TowerDefenceGame}

object GameView {
  def apply(): BorderPane = new BorderPane {
    val game = new TowerDefenceGame
    val state = new GameState(game)
    
    val statusBar = StatusBar(state.player, state)
    val purchaseButton = PurchaseButton(state.player)
    val board = GameBoardView(game, state)

    val topContainer: VBox = new VBox {
       children = Seq(statusBar, purchaseButton)
    }   

    top = topContainer
    center = board
  }
}

