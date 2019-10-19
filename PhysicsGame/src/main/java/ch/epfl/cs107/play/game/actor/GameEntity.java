package ch.epfl.cs107.play.game.actor;

import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;

/**
 * @author Sara Djambazovska
 *
 */


/**
 * 
 */

/**
 * @author Sara Djambazovska
 *
 */
public abstract class GameEntity {
	 private Entity entity;
	 private ActorGame game;
	 private EntityBuilder entityBuilder;
	 
	 
	 public Entity getEntity()
	{
		return entity;
	}
	protected ActorGame getOwner() {
		return game;
	}
	public GameEntity(ActorGame fatima, boolean fixed, Vector position) 
	{
		if(fatima==null)throw new NullPointerException("Argument of type ActorGame null in GameEntity");
		if(position==null)throw new NullPointerException("Given position null in GameEntity");
		//creating the Entities
		this.game=fatima;
		entityBuilder=game.EntityCreator();
		entityBuilder.setFixed(fixed);
        entityBuilder.setPosition(position);
        entity=entityBuilder.build();

		 
	}


	public GameEntity(ActorGame game, boolean fixed) 
	{
		this(game,fixed,Vector.ZERO);
		
	}
	 public void destroy() {
	       entity.destroy();
	    }

}
