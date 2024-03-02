package enemies

import utils.GridPos

class Fiend(var position: GridPos) extends Enemy {
  var health: Int = 500
  val damage: Int = 150
  val attackSpeed: Int = 2
  var pathIndex: Int = 0

  override def getType =
    "Fiend"
}
