package utils

import enemies.{Enemy, Tanker}
import gridcells.{GridCell, PathCell, SceneryCell}
import towers.{Tower,Basic}
import utils.{Wave}
import scala.collection.mutable.ArrayBuffer

class Constants {
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

  val k = new Basic(GridPos(3,3))
  val l = new Basic(GridPos(2,1))
  val towers: ArrayBuffer[Tower] = ArrayBuffer[Tower](k,l)

  var initialResources = 150
  
  val i = new Wave(ArrayBuffer(m))
  val j = new Wave(ArrayBuffer(m,n))
  val waves = Array[Wave](i,j)

  val enemyPath = Array[GridCell](PathCell(GridPos(0,1)), PathCell(GridPos(1,1)), PathCell(GridPos(2,1)),
    PathCell(GridPos(3,1)), PathCell(GridPos(4,1)), PathCell(GridPos(5,1)))
  
    // define a 6x6 grid and add gridcells to it, in the beginning only scenerycells and pathcells
  val grid = ArrayBuffer[Array[GridCell]]()
  grid += Array(SceneryCell(GridPos(0,0)), PathCell(GridPos(0,1)), SceneryCell(GridPos(0,2)), SceneryCell(GridPos(0,3)),
  SceneryCell(GridPos(0,4)), SceneryCell(GridPos(0,5)))

  grid += Array(SceneryCell(GridPos(1,0)), PathCell(GridPos(1,1)), SceneryCell(GridPos(1,2)), SceneryCell(GridPos(1,3)),
  SceneryCell(GridPos(1,4)), SceneryCell(GridPos(1,5)))

  grid += Array(SceneryCell(GridPos(2,0)), PathCell(GridPos(2,1)), SceneryCell(GridPos(2,2)), SceneryCell(GridPos(2,3)),
  SceneryCell(GridPos(2,4)), SceneryCell(GridPos(2,5)))

  grid += Array(SceneryCell(GridPos(3,0)), PathCell(GridPos(3,1)), SceneryCell(GridPos(3,2)), SceneryCell(GridPos(3,3)),
  SceneryCell(GridPos(3,4)), SceneryCell(GridPos(3,5)))

  grid += Array(SceneryCell(GridPos(4,0)), PathCell(GridPos(4,1)), SceneryCell(GridPos(4,2)), SceneryCell(GridPos(4,3)),
  SceneryCell(GridPos(4,4)), SceneryCell(GridPos(4,5)))

  grid += Array(SceneryCell(GridPos(5,0)), PathCell(GridPos(5,1)), SceneryCell(GridPos(5,2)), SceneryCell(GridPos(5,3)),
  SceneryCell(GridPos(5,4)), SceneryCell(GridPos(1,5)))

}