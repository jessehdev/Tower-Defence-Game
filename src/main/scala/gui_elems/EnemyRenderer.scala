package gui_elems

import game.{TowerDefenceGame, GameState}
import enemies._
import scalafx.scene.layout.GridPane
import scalafx.scene.shape.Circle
import scalafx.scene.paint.Color
import scalafx.scene.layout.StackPane

/*
 * EnemyRenderer handles the graphical representation 
 * and rendering of all the enemies
 */
class EnemyRenderer(game: TowerDefenceGame, gameBoard: GridPane) {
//  val cellSize = 100 //gameBoard.cellSize // Assuming you can access cellSize from GameBoardView

//calls createEnemyNode for all enemies within the game
  def renderEnemies() = 
    game.enemies.foreach( enemy =>
      val enemyNode = createEnemyNode(enemy) 
      GridPane.setConstraints(enemyNode, enemy.position.x, enemy.position.y)
      gameBoard.children.add(enemyNode)
    )
  end renderEnemies
//handles the graphical representation of each enemy with respect to the type of enemy it is
  def createEnemyNode(enemy: Enemy): StackPane = 
    val enemyCircle = new Circle {
    radius = enemy match 
      case _: Fiend => 12
      case _: Tanker => 15
    fill = enemy match 
      case _: Fiend => Color.web("#FE0000") // Fiends are red
      case _: Tanker => Color.Violet // Tankers are violet
    // Randomization of position within the StackPane, offsets implemented in backend
    translateX = enemy.offsetX
    translateY = enemy.offsetY
  }
      // StackPane centers children by default => circles in middle of GridCells
      new StackPane {
        children = List(enemyCircle)
    }
}


