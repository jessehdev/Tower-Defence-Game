package game

import towers.{Tower}
import game.{GameState}

class Player(gameState: GameState) {
 //using the same constants from game that are used in gamestate
  var constants = gameState.constants
  var resources = constants.initialResources

  override def toString: String =
    s"Player with ($resources) amount of resources"

  def earnResources(r: Int) =
    resources += r

  def purchaseTower(tower: Tower): Boolean = ???

  def upgradeTower(tower: Tower): Boolean = ???
}