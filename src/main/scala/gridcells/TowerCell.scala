package gridcells

import utils.{GridPos}

case class TowerCell(val gridPos: GridPos) extends GridCell {
  override val canPlaceTower = true
}