package towers

import utils.{GridPos}
import enemies.{Enemy}
import scala.collection.mutable.ArrayBuffer
import scala.math.{sqrt, pow}

/*
 * SplashDamage is a tower type that shoots multiple enemies at once 
 */
class SplashDamage(var position: GridPos) extends Tower {
  var health: Int = 800
  var damage: Int = 750
  val firingRate: Int = 3
  val range: Double = 3.3
  val cost: Int = 100
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
      if inRange.length > 3 then
        inRange.take(3).foreach(_.takeDamage(this.damage))
      else
        inRange.foreach( _.takeDamage(this.damage))
  end shootEnemy
}
