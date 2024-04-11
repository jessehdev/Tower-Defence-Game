package gridcells
import utils.{GridPos}
import game.{TowerDefenceGame}
import towers.{Tower}
import scala.collection.mutable.ArrayBuffer
import scala.compiletime.ops.double

/* 
 * A GridCell is a single cell in the grid, which is a 2D array.
 * More on the grid in game/GameBoard
 * A GridCell has a unique GridPos, more about them in utils/GridPos
 * 4 types of GridCells extend this class
 * PathCells are cells for enemies, which they move in
 * SceneryCell are "basic" cells where no towers can be placed nor enemies access
 * TowerCell is a cell a tower can be placed on
 * WinningAreaCell is one that if an enemy arrives, the game is lost 
 */


abstract class GridCell {
  val gridPos: GridPos
  val canPlaceTower = false

  // THIS WAS THE ORIGINAL  towers.exists( _.position == this.gridPos )
  def hasTower(towers: ArrayBuffer[Tower]): Boolean = 
    towers.exists(tower => tower.position.x == this.gridPos.x && tower.position.y == this.gridPos.y)
  end hasTower

  /* 
   *Checks if this GridCell is a neighbour of another GridCell
   * that is given as a parameter
   */ 
  def isNeighbourOf(g: GridCell): Boolean =
    val dx = (this.gridPos.x - g.gridPos.x).abs
    val dy = (this.gridPos.y - g.gridPos.y).abs
    if (dx == 1 && dy <= 1) || (dy == 1 && dx <= 1) then
      true
    else
      false
  end isNeighbourOf
}