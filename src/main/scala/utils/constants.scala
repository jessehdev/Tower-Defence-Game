package utils

import enemies.{Enemy}
import scala.collection.mutable.ArrayBuffer

class constants {
  //enemy related
  var enemyHealth = 1000
  var enemyDamage = 120
  
  //tower related
  var towerHealth = 1500
  var towerAttack = 150
  var towerRange = 7
  var firingRate = 2
  
  //these will be read from a JSON file eventually
  val enemyPath = ???
  val enemyWave = ???
  val enemies: ArrayBuffer[Enemy] = ???
  var initialResources = 150
}