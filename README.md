## sbt project compiled with Scala 3

#
The project is a tower defence game implemented in Scala. The central idea is to create a fun and strategic, level-based game where waves of enemies attack advancing a predetermined path with an objective to reach a goal area. The player defends by placing upgradable towers along the way of the enemy with the objective to annihilate the enemy. The enemies, respectively, endeavor to demolish the placed towers. 
If an enemy reaches the goal area the game is lost. If enough waves of enemies are eliminated, the game is won and the player can advance to the next level.

Eliminating enemies gives the player resources (most likely gold), which the player can use either to buy new towers or to upgrade the existing ones. There will be three types of towers: basic, long range and splash damage. The program will keep track of the number of eliminated enemies, the amount of resources the player has and how many waves of enemies are left for the level.

# Find out
1) Creating instances of TowerDefenceGame class (multiple vs. one)
2) Having "game-wide" variables in game vs. constants
3) Should I create a new GameState class in TowerDefenceGame (var gameState = new GameState)

# Todo
1) Change all new game to using only variables and methods from the towerdefencegame class
2) Consider moving all the new constant to towerdefencegame
3) Consider how player, gamestate and towerdefencegame need to and will interact