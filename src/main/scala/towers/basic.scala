package towers

import utils.{GridPos}


class Basic(var position: GridPos) extends Tower {
  var health: Int = 1200
  var damage: Int = 250
  val firingRate: Int = 3
  val range: Double = 4.2
  val cost: Int = 100
  val upgradeCost: Int = 150
  override def ugrade(): Unit =
    this.health += 300
    this.damage += 100
    this.level += 1
}
