package towers

import utils.{GridPos}
import enemies.{Enemy}
import scala.collection.mutable.ArrayBuffer
import scala.math.{sqrt, pow}
import game.TowerDefenceGame
import scala.compiletime.ops.double

/*
 * SplashDamage is a tower type that shoots multiple enemies at once 
 */
class SplashDamage(game: TowerDefenceGame, var position: GridPos) extends Tower(game: TowerDefenceGame) {
  var health: Int = 800
  var damage: Int = 750
  val firingRate: Int = 3
  val range: Double = 3.3
  val cost: Int = 150
  val upgradeCost: Int = 100
  override def ugrade(): Unit =
    this.health += 200
    this.damage += 50
    this.level += 1

    /*
    * Contrary to other types of towers, chooses at most 3 enemies to shoot at once.
    */
  override def shootEnemy(arr: ArrayBuffer[Enemy]) = 
    var inRange = ArrayBuffer[Enemy]()
    arr.foreach( enemy =>
      val dx = ( this.position.x - enemy.position.x ).abs.toDouble
      val dy = ( this.position.y - enemy.position.y ).abs.toDouble
      val squared = pow(dx, 2.0) + pow(dy, 2.0)
      if sqrt(squared) <= this.range then
        inRange += enemy
    )
    if inRange.nonEmpty then
      val enemiesToShoot = ArrayBuffer[Enemy]()
      if inRange.length > 3 then
        enemiesToShoot.addAll(inRange.take(3))
        enemiesToShoot.foreach(_.takeDamage(this.damage))
        game.towerShootingsMap.addOne(this, enemiesToShoot)
      else
        inRange.foreach( _.takeDamage(this.damage))
        game.towerShootingsMap.addOne(this, inRange)
  end shootEnemy
}
