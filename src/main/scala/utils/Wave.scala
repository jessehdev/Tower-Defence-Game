package utils

import enemies.{Enemy}
import scala.collection.mutable.ArrayBuffer

/*
    * below is false currently, it is true if class is the following
    * abstact class Wave { etc }
    * class wave is abstract contrary to original plan
    * this is because enemies is not defined in the class
    * but rather because a new instance of it should be created when 
    * reading json 
    * consider creating an array here and changing to plain class
    * for testing purposes if necessary
 */


class Wave(val enemies: ArrayBuffer[Enemy]) {
   override def toString: String = s"Wave consisting of($enemies)"  
}
