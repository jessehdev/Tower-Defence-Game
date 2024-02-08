package game

import utils.{Constants}
import towers.{Tower}
import game.{GameState}

class Player(gameState: GameState) {
 //creating an instance of the class constants
  var constants = new Constants
  var resources = constants.initialResources

  override def toString: String =
    s"Player with ($resources) amount of resources"

  def earnResources(r: Int) =
    resources += r

  def purchaseTower(tower: Tower): Boolean = ???

  def upgradeTower(tower: Tower): Boolean = ???
}