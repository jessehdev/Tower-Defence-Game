package towers
import utils.{GridPos}

class Basic extends Tower {
  val health: Int = 1200
  val damage: Int = 250
  val firingRate: Int = 3
  val range: Int = 6
  val position = GridPos(1,1)
  val cost: Int = 100
}
