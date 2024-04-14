package gui_elems

import gridcells._
import scalafx.scene.paint.Color

/*
 * Handles the representation of different GridCells 
 */
class GridCellView {
 // Method to determine the color of a cell based on its type
 // Called in GameBoardView.scala
  def determineCellColor(cell: GridCell): Color = cell match {
    case _: PathCell => Color.web("#faf9f5") 
    case _: TowerCell => Color.web("#4CB9E7") // ("#50C4ED") 
    case _: SceneryCell => Color.web("#5ced73")  // ("#39e75f") 
    case _: WinningAreaCell => Color.web("#ffbf00") 
    case _ => Color.Red // shouldn't appear
  }
}