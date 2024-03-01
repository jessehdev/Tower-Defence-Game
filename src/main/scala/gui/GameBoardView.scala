package gui

import game._
import gridcells._
import scalafx.scene.layout.GridPane
import scalafx.scene.shape.{Rectangle, Circle, Polygon}
import scalafx.scene.paint.Color
import game.{TowerDefenceGame, GameState}

class GameBoardView(game: TowerDefenceGame, gameState: GameState) extends GridPane {
 // val cellSize = 40
  val gridPaneSize = 800
  val gridWidth = game.grid.length
  val gridHeight = game.grid.length
  //scaling the cellsize
  val cellSize = gridPaneSize / gridWidth.toDouble

  prefWidth = gridPaneSize
  prefHeight = gridPaneSize

  // Initializing the grid
  for (x <- 0 until gridWidth; y <- 0 until gridHeight) {
    val cell = game.grid(x)(y) 
    val rectangle = new Rectangle {
      //binding would be <==
      width = cellSize
      height = cellSize
      fill = determineCellColor(cell) 
      stroke = Color.Black
    }
    
    add(rectangle, x, y)
  }

  // Method to determine the color of a cell based on its type
  def determineCellColor(cell: GridCell): Color = cell match {
    case _: PathCell => Color.LightGray
    case _: TowerCell => Color.Violet
    case _: SceneryCell => Color.LightGreen
    case _: WinningAreaCell => Color.Gold
    case _ => Color.Red //shouldn't appear
  }
}  