package towers

trait Tower {
  val health: Int
  val damage: Int
  val firingRate: Int
  val position = ???
  val range: Int

  def shootEnemy() = ???
  
  def ugrade() = ???
}