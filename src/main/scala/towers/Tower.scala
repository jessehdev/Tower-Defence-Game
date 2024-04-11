package towers
import utils.{GridPos}
import enemies.{Enemy}
import scala.collection.mutable.ArrayBuffer
import scala.math.{pow, sqrt}

/*
 * A tower is an important entity of the game as the name Tower defence game suggests
 * Different towers (Basic and Splashdamage) inherit this class and can be found in
 * the same directory as this.
 * Towers try to shoot enemies, which are heading for the winning area.
  */

trait Tower {
  var position: GridPos
  var health: Int
  var damage: Int
  val firingRate: Int
  val range: Double
  val cost: Int
  val upgradeCost: Int
  var level: Int = 1

  /*
   * This method gets all the enemies that exist in the game currently as a parameter.
   * Then it determines, by using Pythagora's theorem, if an enemy is within its range. 
   * If so, the enemy in question gets added to an ArrayBuffer "inRange".
   * If inRange is not empty, an arbritary enemy is chosen to shoot at
   */
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