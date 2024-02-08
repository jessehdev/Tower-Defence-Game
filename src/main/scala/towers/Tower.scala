package towers
import utils.{GridPos}
import enemies.{Enemy}
import scala.collection.mutable.ArrayBuffer
import scala.math.{pow, sqrt}

trait Tower {
  val position: GridPos
  var health: Int
  var damage: Int
  val firingRate: Int
  val range: Double
  val cost: Int
  val upgradeCost: Int

  // if enemy if range, choose an arbitrary one to shoot
  def shootEnemy(arr: ArrayBuffer[Enemy]) = 
    var inRange = ArrayBuffer[Enemy]()
    arr.foreach( enemy =>
      val dx = ( this.position.x - enemy.position.x ).abs.toDouble
      val dy = ( this.position.y - enemy.position.y ).abs.toDouble
      val squared = pow(dx, 2.0) + pow(dy, 2.0)
      if sqrt(squared) <= this.range then
        inRange += enemy
    )
    if inRange.nonEmpty then
      val target = inRange.head
      target.takeDamage(this.damage)
   end shootEnemy

  def ugrade(): Unit 
}