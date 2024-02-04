package enemies

trait Enemy {
  val health: Int
  val damage: Int
  val attackSpeed: Int
  val gridPos = ???
 
  def getPos() = ???
 
  def move() = ???
 
  def takeDamage() = ???
}