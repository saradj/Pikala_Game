package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.bikegame.BikeGame;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Triggers extends GameEntity implements Actor {

	private PartBuilder partBuilder;
	
	//in case some of the classes extending it need to change their signature for example
	protected PartBuilder getPartBuilder()
	{
		return partBuilder;
	}
	private boolean haveColided;
	private  float timer=0;
	private int numberOfCollisions=0;
	
	//allowing the classes that extend it to know if they have collided with the main actor
	protected boolean haveColided(){
		return haveColided;
	}
	
	private  BasicContactListener contactListener;
	
	public Triggers(ActorGame game, boolean fixed, Vector position,Shape shape) {
		
		super(game, fixed, position);

		if(shape==null)throw new NullPointerException("Given shape null while constucting in Triggers");
		 numberOfCollisions=0;
		 timer=0f;
		 haveColided=false;
		 partBuilder=getEntity().createPartBuilder();
		 partBuilder.setShape(shape);
		 partBuilder.setGhost(true);
		 partBuilder.build();
		 //making the triggers sensitive to collisions
		 contactListener = new BasicContactListener(); 
		 getEntity().addContactListener(contactListener);
			
		// TODO Auto-generated constructor stub
	}
	@Override
	public Transform getTransform() {
		
		return getEntity().getTransform();
	}
	@Override
	public Vector getVelocity() {
		
		return getEntity().getVelocity();
	}
	

	@Override
	public void destroy() {
	    getEntity().destroy();
	    
	 }

	@Override
	public void draw(Canvas canvas) {
		
		
	}
	public  void update(float deltaTime)
	{
		 numberOfCollisions = contactListener.getEntities().size();
		//checking if the main actor has collide with it
		if (numberOfCollisions >0
				&&(contactListener.getEntities().contains(((GameEntity)getOwner().getPayLoad()).getEntity()))){
	//or contactListener.hasContactWith(((GameEntity)getOwner().getPayLoad()).getEntity());
		haveColided=true;
		
		}
		
	}
}
