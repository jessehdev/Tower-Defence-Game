package towers
import utils.{GridPos}

trait Tower {
  val health: Int
  val damage: Int
  val firingRate: Int
  val range: Int
  val position: GridPos

  def shootEnemy() = ???
  
  def ugrade() = ???
}