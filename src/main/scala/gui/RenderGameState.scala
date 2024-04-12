package gui

import game._

/*
 * RenderGameState controls the rendering of the gameboard,
 * enemies and towers. The methods are found in corresponding file names 
 * to the variable names with the exception of board, which is 
 * found in GameBoardView.scala 
 */
class RenderGameState(game: TowerDefenceGame, board: GameBoardView, statusBar: StatusBar, towerRenderer: TowerRenderer) {
  def renderGame() = 
  // Clear previous GUI elements if necessary
    board.getChildren.clear()
      // Render Board
    board.renderBoard()

  // Render enemies
    val enemyRenderer = EnemyRenderer(game, board)
    enemyRenderer.renderEnemies()

  //Render towers
   // val towerRenderer = TowerRenderer(game, board)
    towerRenderer.renderTowers()
    
    statusBar.updateLabels()
}
