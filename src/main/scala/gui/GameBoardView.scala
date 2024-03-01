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
  
  val gridCellView = new GridCellView
  // Initializing the grid
  for (x <- 0 until gridWidth; y <- 0 until gridHeight) {
    val cell = game.grid(x)(y) 
    val rectangle = new Rectangle {
      //binding would be <==
      width = cellSize
      height = cellSize
      fill = gridCellView.determineCellColor(cell) 
      stroke = Color.Black
    }
    
    add(rectangle, x, y)
  }
}