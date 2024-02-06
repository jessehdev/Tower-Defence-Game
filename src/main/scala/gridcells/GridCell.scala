package gridcells
import utils.{GridPos}

abstract class GridCell {
  val gridPos: GridPos

  def hasEnemy(): Boolean = ???
  def hasTower(): Boolean = ???

  def isNeighbourOf(g: GridCell): Boolean =
    val dx = (this.gridPos.x - g.gridPos.x).abs
    val dy = (this.gridPos.y - g.gridPos.y).abs
    if (dx == 1 && dy <= 1) || (dy == 1 && dx <= 1) then
      true
    else
      false
  end isNeighbourOf
}