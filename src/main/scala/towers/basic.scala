package towers

import utils.{GridPos}
import game.TowerDefenceGame


class Basic(game: TowerDefenceGame, var position: GridPos) extends Tower(game: TowerDefenceGame) {
  var health: Int = 1200
  var damage: Int = 1000
  val firingRate: Int = 3
  val range: Double = 4.2
  val cost: Int = 100
  val upgradeCost: Int = 150
  override def ugrade(): Unit =
    this.health += 300
    this.damage += 50
    this.level += 1
}
