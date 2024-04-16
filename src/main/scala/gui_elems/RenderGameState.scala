package gui_elems

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
      // The if clause prevents from concurrent mutation exception to be thrwon
      // since the buffer containing enemies isn't modified while 
      // the program reads the buffer (towers searching for enemies to shoot)   
    if (game.tickCounter / game.gameState.towersAttackModulo != 0) then
      enemyRenderer.renderEnemies()

  //Render towers
    towerRenderer.renderTowers()
    
    statusBar.updateLabels()
}
