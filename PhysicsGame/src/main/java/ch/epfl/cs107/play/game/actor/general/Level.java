package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.bikegame.Bike;
import ch.epfl.cs107.play.math.Node;

public abstract class Level extends  Node implements Actor{
	
	 protected ActorGame game;
	 //giving the level an argument the game it will be part of, when constructing it
	 public Level(ActorGame fatima)
	 {
		 if(fatima==null)throw new NullPointerException("Given argument of type ActorGame null while creating a Level");
	    	this.game=fatima;}

	public abstract void createAllActors();
	
	public abstract void addAllActors();

}
