package gui

package gui

import game.{TowerDefenceGame, GameState}
import enemies._
import scalafx.scene.layout.GridPane
import scalafx.scene.shape.Circle
import scalafx.scene.paint.Color
import scalafx.scene.layout.StackPane

class EnemyRenderer(game: TowerDefenceGame, gameBoard: GridPane) {
//  val cellSize = 100 //gameBoard.cellSize // Assuming you can access cellSize from GameBoardView

  def renderEnemies() = 
    // First, clear existing enemy representations from the GridPane to avoid duplicates
    //gameBoard.children.clear()
    game.enemies.foreach( enemy =>
      val enemyNode = createEnemyNode(enemy) 
      GridPane.setConstraints(enemyNode, enemy.position.x, enemy.position.y)
      gameBoard.children.add(enemyNode)
    )
  
    def createEnemyNode(enemy: Enemy): StackPane = 
      val enemyCircle = new Circle {
      radius = enemy match 
        case _: Fiend => 12
        case _: Tanker => 15
      fill = enemy match 
        case _: Fiend => Color.web("#FE0000") // Fiends are red
        case _: Tanker => Color.Violet // Tankers are violet
      // Randomize position within the StackPane
      translateX = enemy.offsetX
      translateY = enemy.offsetY
    }
      // StackPane centers children by default => circles in middle of GridCells
      new StackPane {
        children = List(enemyCircle)
    }
}


