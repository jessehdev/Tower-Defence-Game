package gui

import gridcells._
import scalafx.scene.paint.Color

class GridCellView {
    
 // Method to determine the color of a cell based on its type
 // Called in GameBoardView.scala
  def determineCellColor(cell: GridCell): Color = cell match {
    case _: PathCell => Color.LightGray
    case _: TowerCell => Color.Violet
    case _: SceneryCell => Color.LightGreen
    case _: WinningAreaCell => Color.Gold
    case _ => Color.Red //shouldn't appear
  }
}