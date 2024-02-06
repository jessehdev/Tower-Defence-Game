package utils

import enemies.{Enemy, Tanker}
import gridcells.{GridCell, PathCell}
import towers.{Tower,Basic}
import utils.{Wave}
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
  val enemyWave = ???

  val m = new Tanker
  val n = new Tanker
  val enemies: ArrayBuffer[Enemy] = ArrayBuffer[Enemy](m,n)

  val k = new Basic
  val l = new Basic
  val towers: ArrayBuffer[Tower] = ArrayBuffer[Tower](k,l)

  var initialResources = 150
  
  val enemyPath = Array[GridCell](PathCell(GridPos(0,2)), PathCell(GridPos(1,2)), PathCell(GridPos(2,2)),
    PathCell(GridPos(3,2)), PathCell(GridPos(4,2)), PathCell(GridPos(5,2)), PathCell(GridPos(6,2)))
  
  val i = new Wave(ArrayBuffer(m))
  val j = new Wave(ArrayBuffer(m,n))
  val waves = Array[Wave](i,j)
}