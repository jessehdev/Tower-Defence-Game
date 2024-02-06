package towers
import utils.{GridPos}

class Basic extends Tower {
  var health: Int = 1200
  var damage: Int = 250
  val firingRate: Int = 3
  val range: Int = 6
  val position = GridPos(1,1)
  val cost: Int = 100

  override def ugrade(): Unit =
    this.health += 300
    this.damage += 100
}
