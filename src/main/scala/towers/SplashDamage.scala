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
        game.towerShootingsMap.addOne(this, enemiesToShoot)
        game.towerHasShot = true
        handleTakingDamage(enemiesToShoot)
      else
        game.towerShootingsMap.addOne(this, inRange)
        game.towerHasShot = true
        handleTakingDamage(inRange)
  end shootEnemy

  // This function is used so that enemies won't dissapear in the gui once the shot
  // is taken but rather once the ammunition has reached their position
  def handleTakingDamage(arr: ArrayBuffer[Enemy]) =
   for enemy <- arr do
     if enemy.health <= this.damage then 
       Thread.sleep(200)
       enemy.takeDamage(this.damage)
     else
       enemy.takeDamage(this.damage)
  end handleTakingDamage
}
