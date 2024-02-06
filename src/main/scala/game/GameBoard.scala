package game

import gridcells.{GridCell, SceneryCell}
import utils.{GridPos}
import utils.{Constants}

class GameBoard (val width: Int, val height: Int) {
  val size = width * height
 
 // initializes a grid consisting of scenery cells
  var grid: Array[Array[GridCell]] = Array.tabulate(width, height)( (i, j) =>
  SceneryCell(GridPos(i, j)) 
  )

  //will be replaced with reading from JSON
  val constants = new Constants
  val enemyPath = constants.enemyPath

  for row <- grid do
    for cell <- row do
        for i <- enemyPath do
            if i.gridPos == cell.gridPos then
                val i = grid(1)(7)

/*
  grid.foreach( arr => arr.foreach( cell => 
    for i <- enemyPath do
      if i.gridPos == cell.gridPos then
        cell = new PathCell(i.gridPos)

  ) )
  */ 
}
