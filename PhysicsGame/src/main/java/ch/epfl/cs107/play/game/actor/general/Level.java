package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.bikegame.Bike;
import ch.epfl.cs107.play.math.Node;

public abstract class Level extends  Node implements Actor{
	protected ActorGame game;
	
	 public Level(ActorGame fatima)
	 {
	    	this.game=fatima;}
	 
	

	public abstract void createAllActors();
	public abstract void addAllActors();
	public abstract void removeAllActors();
	public abstract Bike getBike();
}
