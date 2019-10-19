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
	 protected Entity entity;
	// protected PartBuilder partBuilder ;
	 protected ActorGame game;
	 protected EntityBuilder entityBuilder;
	 
	 
	 public Entity getEntity()
	{
		return entity;
	}
	protected ActorGame getOwner() {
		return game;
	}
	public GameEntity(ActorGame fatima, boolean fixed, Vector position)
	{
		this.game=fatima;
		entityBuilder=game.EntityCreator();
		entityBuilder.setFixed(fixed);
        entityBuilder.setPosition(position);
        entity=entityBuilder.build();
        
       //  partBuilder = entity.createPartBuilder() ;
		
	//	game.addActor((Actor)this);
		
	}
	/*public GameEntity(ActorGame game, boolean fixed, Vector position, Shape shape) {
		this(game,fixed,position);
		partBuilder=entity.createPartBuilder();
		 partBuilder.setShape(shape) ;
		 partBuilder.build() ;
		 }
		*/
	public GameEntity(ActorGame game, boolean fixed) 
	{
		this(game,fixed,Vector.ZERO);
		
	}
	 public void destroy() {
	       entity.destroy();
	    }

}
