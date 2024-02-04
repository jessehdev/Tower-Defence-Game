package gridcells
import utils.{GridPos}

abstract class GridCell {
  val gridPos: GridPos

  def hasEnemy(): Boolean = ???
  def hasTower(): Boolean = ???
  def isNeighbourOf(g: GridCell): Boolean = ???
}