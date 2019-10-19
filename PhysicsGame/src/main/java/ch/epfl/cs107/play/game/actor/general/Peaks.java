package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.bikegame.Bike;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Peaks extends GameEntity implements Actor {

	float i;
	private  BasicContactListener contactListener;
	private PartBuilder partBuilder;
	private ImageGraphics imageGraphics;
	public Peaks(ActorGame fatima, boolean fixed, Vector position) {
		super(fatima, fixed, position);
		i=2f;
		partBuilder=getEntity().createPartBuilder();
		partBuilder.setShape( new Polygon(
			     new Vector(0.0f, 0.0f),
			     new Vector(15f, 0.0f),
			     new Vector(15f, 1.0f),
			     new Vector(0.0f, 1.0f)
			     ));
		partBuilder.setGhost(false);
		partBuilder.setCollisionSignature(0x2);
		partBuilder.build();
		imageGraphics= new ImageGraphics("sand.png", 15f, 1);
		imageGraphics.setParent(getEntity());
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
		imageGraphics.draw(canvas);
		// TODO Auto-generated method stub
	}
	
public void update(float deltaTime)
{
	int numberOfCollisions = contactListener.getEntities().size();
	//if (numberOfCollisions >0&&(contactListener.getEntities().contains(((Bike)((GameEntity)getOwner().getViewCandidate())).getWheelL().getEntity())))
	{
		
		//if(i>0)
		{//((GameEntity)getOwner().getViewCandidate()).getEntity().applyForce(new Vector(-20,0), null);
		//	((Bike)((GameEntity)getOwner().getViewCandidate())).getWheelL().power(0);
		//	((Bike)((GameEntity)getOwner().getViewCandidate())).getWheelR().power(0);}
		//i=i-deltaTime;
	}
}
}}
