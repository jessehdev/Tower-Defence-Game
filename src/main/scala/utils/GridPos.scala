package utils

/*  
 * Every GridCell in this game has a unique GridPos, 
 * which has an x value and a y value, respectively. Both are integers.
*/ 

class GridPos(val x: Int, val y: Int) {
  override def toString: String = s"X=$x, Y=$y"
}