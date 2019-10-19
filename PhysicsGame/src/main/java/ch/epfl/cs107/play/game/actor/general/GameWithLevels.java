package ch.epfl.cs107.play.game.actor.general;

public interface GameWithLevels {
	
 abstract void nextLevel();
 
 abstract void resetlevel();
 
default void setLevelPassed(boolean levelPassed) {};

default void GameOver(boolean gameOver) {}; 

}
