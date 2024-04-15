package towers

import utils.{GridPos}
import game.TowerDefenceGame


class Basic(game: TowerDefenceGame, var position: GridPos) extends Tower(game: TowerDefenceGame) {
  var health: Int = 1200
  var damage: Int = 500
  val firingRate: Int = 3
  val range: Double = 3.5
  val cost: Int = 100
  val upgradeCost: Int = 50
  override def ugrade(): Unit =
    this.health += 300
    this.damage += 100
    this.level += 1
}
