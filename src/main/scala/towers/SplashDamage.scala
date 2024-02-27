package towers
import utils.{GridPos}
import enemies.{Enemy}
import scala.collection.mutable.ArrayBuffer
import scala.math.{sqrt, pow}

class SplashDamage(override val position: GridPos) extends Tower {
  var health: Int = 800
  var damage: Int = 100
  val firingRate: Int = 3
  val range: Double = 3.3
  val cost: Int = 200
  val upgradeCost: Int = 200
  
  override def ugrade(): Unit =
    this.health += 200
    this.damage += 70

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
      inRange.foreach( _.takeDamage(this.damage))
  end shootEnemy

}
