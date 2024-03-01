package gui

import gui._
import scalafx.scene.layout.{BorderPane, VBox, Pane}
import game.{GameState, TowerDefenceGame}

object GameView {
  def apply(): BorderPane = new BorderPane {
    val game = new TowerDefenceGame
    val state = new GameState(game)
    
    top = StatusBar(state.player, state)
    center = GameBoardView(game, state)
  }
}
