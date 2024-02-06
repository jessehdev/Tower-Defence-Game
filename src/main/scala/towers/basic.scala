package towers
import utils.{GridPos}

class basic extends Tower {
  val health: Int = 1200
  val damage: Int = 250
  val firingRate: Int = 3
  val range: Int = 6
  val position = GridPos(1,1)

}
