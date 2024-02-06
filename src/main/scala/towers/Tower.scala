package towers
import utils.{GridPos}

trait Tower {
  val health: Int
  val damage: Int
  val firingRate: Int
  val range: Int
  val position: GridPos
  val cost: Int

  def shootEnemy() = ???
  
  def ugrade() = ???
}