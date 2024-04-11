package enemies

import utils.GridPos

class Tanker(var position: GridPos) extends Enemy {
  var health: Int = 3500
  val damage: Int = 300
  val attackSpeed: Double = 1
  var pathIndex: Int = 0

  override def getType =
    "Tanker"
}
