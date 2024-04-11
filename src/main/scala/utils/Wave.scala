package utils

import enemies.{Enemy}
import scala.collection.mutable.ArrayBuffer

/*
 * A wave in the game consist of an arbitrary number of enemies
 */

class Wave(val enemies: ArrayBuffer[Enemy]) {
   override def toString: String = s"Wave consisting of($enemies)"  
}
