package ch.epfl.cs107.play.game.actor.general;

import java.awt.font.ImageGraphicAttribute;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Kinematic  implements Actor{

		private Entity entity;
		// protected PartBuilder partBuilder ;
		 private ActorGame game;
		 private EntityBuilder entityBuilder;
		 private PartBuilder partBuilder;
		 protected ImageGraphics graphics;
		 public Entity getEntity()
		{
			return entity;
		}
		protected ActorGame getOwner() {
			return game;
		}
		protected PartBuilder getPartBuilder()
		{
			return partBuilder;
		}
		protected Vector position;
		protected Vector finalPosition;
		protected Vector speed;
		
	
		//A new Actor containing a kinematic body, meaning it is not affected by gravity and once given an initial velocity
		//it keeps moving with constant speed, so we will use it mostly for transporting the bike
		public Kinematic(ActorGame game,Vector position,Vector finalPosition, Vector speed,float width, float height, String imageName)
		{
			if(game==null)throw new NullPointerException("Argument of type ActorGame null while constructing Kinematic");
			if(position==null)throw new NullPointerException("Given position vector null while contructing a Kinematic");
			if(finalPosition==null)throw new NullPointerException("Given final position vector null while contructing a Kinematic");
			if(speed==null)throw new NullPointerException("Given speed vector null while contructing a Kinematic");
			if(imageName==null)throw new NullPointerException("Given image name null while contructing a Kinematic");
			if(width<=0)throw new IllegalArgumentException("Given width of picture smaller or equal to zero in Kinematic");
			if(height<=0)throw new IllegalArgumentException("Given height of picture smaller or equal to zero in Kinematic");
			
			this.game=game;
			this.position=position;
			this.speed=speed;
			this.finalPosition=finalPosition;
			entityBuilder=game.EntityCreator();
			//setting the Entity to kinematic, using a method defined in EntityBuilder
			entityBuilder.setKinematic();
	        entityBuilder.setPosition(position);
	        entity=entityBuilder.build();
	      //physical parts
	        partBuilder=entity.createPartBuilder();
	        partBuilder.setShape( new Polygon(
	   		     new Vector(0.0f, 0.0f),
	   		     new Vector(width, 0.0f),
	   		     new Vector(width, height),
	   		     new Vector(0.0f, height)
	   		     ));
	    //high friction, so when the biker gets on it he doesn't slide easily, and can use it to be transported
	        partBuilder.setFriction(10);
	        partBuilder.build();
	        //the visual representation
	        graphics= new ImageGraphics(imageName, width, height, new Vector(0,0), 1, 1);
	        graphics.setParent(getEntity());
	        entity.setVelocity(speed);
	     
		}
	        
		public void update(float deltaTime)
		{
			//it moves between the given initial and final position with constan given (positive) speed
			if(getEntity().getPosition().getX()>finalPosition.getX()
					||getEntity().getPosition().getY()>finalPosition.getY()){
				
				entity.setVelocity(speed.opposite());
				
				return;
			}
		if(getEntity().getPosition().getX()<position.getX()
				||getEntity().getPosition().getY()<position.getY()){
			
			entity.setVelocity(speed);
			return;
			
		}
	
		}
		
		public void destroy()
		{
			entity.destroy();
		
		}
	@Override
	public Transform getTransform() {
		// TODO Auto-generated method stub
		return getEntity().getTransform();
	}

	@Override
	public Vector getVelocity() {
		// TODO Auto-generated method stub
		return getEntity().getVelocity();
	}

	@Override
	public void draw(Canvas canvas) {
		if(canvas==null)throw new NullPointerException("Argument type Canvas null fo draw method in Kinematic");
		// TODO Auto-generated method stub
		graphics.draw(canvas);
	}

}
