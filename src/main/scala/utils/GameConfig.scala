package utils
import gridcells._
import scala.collection.mutable.ArrayBuffer
import enemies._
import utils.Wave

object GameConfig extends App {
  /*
   * Returns an Array of PathCells that correspond to the path that enemies are to take in each level configuration.
   * Depends on the json file having the path in correct sequential order. 
   */
  def generateEnemyPath(level: Int): ArrayBuffer[PathCell] = 
    val jsonFile = s"level${level}.json"
        val json = ujson.read(os.read(os.pwd / "src" / "main" / "levels" / jsonFile))

    val pathCoordinates = json("grid")("path")
    val asTuples = pathCoordinates.arr.map(obj => (obj("x").toString.toInt, obj("y").toString.toInt))
    val asCells = asTuples.map(coords => PathCell(GridPos(coords._1, coords._2)))
    
    return asCells
  end generateEnemyPath

  def generateWaves(level: Int): Array[Wave] =
    val jsonFile = s"level${level}.json"
        val json = ujson.read(os.read(os.pwd / "src" / "main" / "levels" / jsonFile))

    val firstCell = json("grid")("path")(0)
    val (x, y) = (firstCell("x").toString.toInt, firstCell("y").toString.toInt)
    val allEnemies = json("waves")
    val byWaves = allEnemies.arr.groupBy(obj => obj("wave"))
    val asEnemies = byWaves.map(hash => (hash._1, hash._2.map(obj => if obj("type").toString == "\"Tanker\"" then 
        new Tanker(GridPos(x,y)) 
      else 
        new Fiend(GridPos(x,y)))))
    val asWaves = asEnemies.map(hash => hash._1 -> new Wave(hash._2)).values.toArray

    return asWaves
  end generateWaves

  def generateGrid(level: Int): ArrayBuffer[Array[GridCell]] =
    val jsonFile = s"level${level}.json"
        val json = ujson.read(os.read(os.pwd / "src" / "main" / "levels" / jsonFile))
    
    val pathCells: ArrayBuffer[GridCell] = json("grid")("path").arr
      .map(obj => (obj("x").toString.toInt, obj("y").toString.toInt))
      .map(coords => PathCell(GridPos(coords._1, coords._2)))
    val lastCell = WinningAreaCell(GridPos(pathCells.last.gridPos.x, pathCells.last.gridPos.y))
    pathCells -= pathCells.last
    pathCells += lastCell
    val towerCells = json("grid")("towers").arr
      .map(obj => (obj("x").toString.toInt, obj("y").toString.toInt))
      .map(coords => TowerCell(GridPos(coords._1, coords._2)))
    val sceneryCells = json("grid")("scenery").arr
      .map(obj => (obj("x").toString.toInt, obj("y").toString.toInt))
      .map(coords => SceneryCell(GridPos(coords._1, coords._2)))

    val all = pathCells ++ towerCells ++ sceneryCells
    val (maxX, maxY) = (
      all.maxBy(cell => cell.gridPos.x).gridPos.x,
      all.maxBy(cell => cell.gridPos.y).gridPos.y
    )

    val grid = ArrayBuffer[Array[GridCell]]()

    for (i <- 0 to maxX)
      val arr = ArrayBuffer[GridCell]()
      for (j <- 0 to maxY)
        arr += all.find(cell => cell.gridPos.x == i && cell.gridPos.y == j).get
      grid.append(arr.toArray)

    return grid
  end generateGrid

  def generateInitialResources(level: Int): Int =
    val jsonFile = s"level${level}.json"
        val json = ujson.read(os.read(os.pwd / "src" / "main" / "levels" / jsonFile))
    
    val resources = json("resources") 

    return resources.toString.toInt
  end generateInitialResources

  def getWaveCount(level: Int): Int =
    val jsonFile = s"level${level}.json"
        val json = ujson.read(os.read(os.pwd / "src" / "main" / "levels" / jsonFile))

    val waves = json("waves").arr.groupBy(obj => obj("wave")).size

    return waves
  end getWaveCount
}
