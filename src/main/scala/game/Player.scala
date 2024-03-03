package game

import towers.{Tower, Basic}
import game.{GameState}
import utils.{GridPos}

case class TowerException(description: String)
    extends java.lang.Exception(description)

class Player(game: TowerDefenceGame, gameState: GameState) {
 //using the same constants from game that are used in gamestate
  var constants = game.constants
  var resources = constants.initialResources

  override def toString: String =
    s"Player with ($resources) amount of resources"

  def earnResources(r: Int) =
    resources += r

// both purchaseTower and upgradeTower returned boolean in initial plan
// changed it to return it again (:D) (at least for testing purposes)
  def purchaseTower(tower: Tower): Boolean = 
    if resources >= tower.cost then
   //   game.gameBoard.placeTower(tower, gridPos)
      game.gameBoard.updateAvailable()
      game.placeableTowers += tower
      resources -= tower.cost
      true
    else 
      throw TowerException(
        s"Not enough resources to purchase tower, resources: ${resources} cost: ${tower.cost}")
      false
  end purchaseTower

  def upgradeTower(tower: Tower): Boolean = 
    if resources >= tower.upgradeCost then
      tower.ugrade()
      resources -= tower.upgradeCost
      true
    else
      throw TowerException(
        s"Not enough resources to upgrade tower, resources: ${resources} cost: ${tower.cost}"
      )
      false
  end upgradeTower
}