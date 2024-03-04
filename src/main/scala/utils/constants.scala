package utils

import enemies._
import gridcells._
import towers.{Tower,Basic}
import utils.{Wave}
import scala.collection.mutable.ArrayBuffer

class Constants {

  //these will be read from a JSON file eventually
  val m = Tanker(GridPos(2,1))
  val n = Fiend(GridPos(3,1))
  val enemies: ArrayBuffer[Enemy] = ArrayBuffer[Enemy](m,n)

  val k = new Basic(GridPos(3,3))
  val l = new Basic(GridPos(2,1))
  //val towers: ArrayBuffer[Tower] = ArrayBuffer[Tower](k,l)

  var initialResources = 250

  val boardHeight = 6
  val boardWidth = 6
  
  val waves = Array[Wave](
    Wave(ArrayBuffer(new Tanker(GridPos(0,1)), new Fiend(GridPos(0,1)), new Tanker(GridPos(0,1)), new Fiend(GridPos(0,1)), new Tanker(GridPos(0,1)), new Fiend(GridPos(0,1)))),
    Wave(ArrayBuffer(new Tanker(GridPos(0,1)), new Tanker(GridPos(0,1)), new Fiend(GridPos(0,1)), new Fiend(GridPos(0,1)))),
    Wave(ArrayBuffer(new Tanker(GridPos(0,1)), new Tanker(GridPos(0,1)), new Fiend(GridPos(0,1)), new Fiend(GridPos(0,1))),
    // Continue for other waves
    ))
  
  var amountOfWaves = waves.length

  val enemyPath = Array[GridCell](PathCell(GridPos(0,1)), PathCell(GridPos(1,1)), PathCell(GridPos(2,1)),
    PathCell(GridPos(3,1)), PathCell(GridPos(4,1)), PathCell(GridPos(5,1)), PathCell(GridPos(6,1)), PathCell(GridPos(7,1)), PathCell(GridPos(8,1)),
    PathCell(GridPos(8,2)), PathCell(GridPos(8,3)), PathCell(GridPos(8,4)), PathCell(GridPos(7,4)), PathCell(GridPos(6,5)), PathCell(GridPos(6,4)), PathCell(GridPos(5,5)),
    PathCell(GridPos(4,5)), PathCell(GridPos(4,6)), PathCell(GridPos(4,7)), WinningAreaCell(GridPos(4,8))
    )

  val initialPos = enemyPath(0).gridPos
  
    // define a 6x6 grid and add gridcells to it, in the beginning only scenerycells and pathcells
  val grid = ArrayBuffer[Array[GridCell]]()
  grid += Array(SceneryCell(GridPos(0,0)), PathCell(GridPos(0,1)), TowerCell(GridPos(0,2)), TowerCell(GridPos(0,3)),
  SceneryCell(GridPos(0,4)), SceneryCell(GridPos(0,5)), SceneryCell(GridPos(0,6)), SceneryCell(GridPos(0,7)), SceneryCell(GridPos(0,8)))

  grid += Array(SceneryCell(GridPos(1,0)), PathCell(GridPos(1,1)), SceneryCell(GridPos(1,2)), SceneryCell(GridPos(1,3)),
  SceneryCell(GridPos(1,4)), SceneryCell(GridPos(1,5)), TowerCell(GridPos(1,6)), SceneryCell(GridPos(1,7)), SceneryCell(GridPos(1,8)))

  grid += Array(SceneryCell(GridPos(2,0)), PathCell(GridPos(2,1)), TowerCell(GridPos(2,2)), SceneryCell(GridPos(2,3)),
  SceneryCell(GridPos(2,4)), SceneryCell(GridPos(2,5)), SceneryCell(GridPos(2,6)), SceneryCell(GridPos(2,7)), SceneryCell(GridPos(2,8)))

  grid += Array(SceneryCell(GridPos(3,0)), PathCell(GridPos(3,1)), SceneryCell(GridPos(3,2)), SceneryCell(GridPos(3,3)),
  SceneryCell(GridPos(3,4)), SceneryCell(GridPos(3,5)), SceneryCell(GridPos(3,6)), SceneryCell(GridPos(3,7)), SceneryCell(GridPos(3,8)))

  grid += Array(SceneryCell(GridPos(4,0)), PathCell(GridPos(4,1)), TowerCell(GridPos(4,2)), TowerCell(GridPos(4,3)),
  SceneryCell(GridPos(4,4)), PathCell(GridPos(4,5)), PathCell(GridPos(4,6)), PathCell(GridPos(4,7)), WinningAreaCell(GridPos(4,8)))

  grid += Array(SceneryCell(GridPos(5,0)), PathCell(GridPos(5,1)), SceneryCell(GridPos(5,2)), TowerCell(GridPos(5,3)),
  SceneryCell(GridPos(5,4)), PathCell(GridPos(5,5)), TowerCell(GridPos(5,6)), SceneryCell(GridPos(5,7)), TowerCell(GridPos(5,8)))

  grid += Array(SceneryCell(GridPos(6,0)), PathCell(GridPos(6,1)), TowerCell(GridPos(6,2)), SceneryCell(GridPos(6,3)),
  PathCell(GridPos(6,4)), PathCell(GridPos(6,5)), SceneryCell(GridPos(6,6)), SceneryCell(GridPos(6,7)), SceneryCell(GridPos(6,8)))
  
  grid += Array(SceneryCell(GridPos(7,0)), PathCell(GridPos(7,1)), SceneryCell(GridPos(7,2)), SceneryCell(GridPos(7,3)),
  PathCell(GridPos(7,4)), SceneryCell(GridPos(7,5)), SceneryCell(GridPos(7,6)), SceneryCell(GridPos(7,7)), SceneryCell(GridPos(7,8)))
  
  grid += Array(SceneryCell(GridPos(8,0)), PathCell(GridPos(8,1)), PathCell(GridPos(8,2)), PathCell(GridPos(8,3)),
  PathCell(GridPos(8,4)), SceneryCell(GridPos(8,5)), SceneryCell(GridPos(8,6)), SceneryCell(GridPos(8,7)), SceneryCell(GridPos(8,8)))
}