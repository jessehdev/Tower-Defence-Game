package gui

import game._
import gui.EnemyRenderer

class RenderGameState(game: TowerDefenceGame, board: GameBoardView, statusBar: StatusBar) {
  def renderGame() = 
  // Clear previous GUI elements if necessary
    board.getChildren.clear()
      // Render Board
    board.renderBoard()

  // Render enemies
    val enemyRenderer = EnemyRenderer(game, board)
    enemyRenderer.renderEnemies()

  // Render towers
    val towerRenderer = TowerRenderer(game, board)
    towerRenderer.renderTowers()
    
    statusBar.updateLabels()
}
