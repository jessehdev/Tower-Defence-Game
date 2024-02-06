package towers
import utils.{GridPos}

trait Tower(val position: GridPos) {
  var health: Int
  var damage: Int
  val firingRate: Int
  val range: Int
  val cost: Int

  def shootEnemy() = ???
  
  def ugrade(): Unit 
}