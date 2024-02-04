package game

import utils.initialResources
import towers.{Tower}

class Player {
//figure out if it should be utils.constants.initialResources
  var resources: Int = utils.initialResources

   override def toString: String =
    s"Player with ($resources) amount of resources"

  def earnResources(r: Int) =
    resources += r

  def purchaseTower(tower: Tower): Boolean = ???

  def upgradeTower(tower: Tower): Boolean = ???
}