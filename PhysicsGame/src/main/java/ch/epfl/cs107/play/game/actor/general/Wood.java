package ch.epfl.cs107.play.game.actor.general;

import java.awt.font.ImageGraphicAttribute;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Contact;
import ch.epfl.cs107.play.math.ContactListener;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Wood extends GameEntity implements Actor{

	private PartBuilder partBuilder;
	private ImageGraphics graphics;
	private ContactListener listener;
	private boolean explode;
	//An actor wood that disappears when the bomb falls on it, and thus makes space for the biker to move further
	public Wood(ActorGame fatima, boolean fixed, Vector position) {
		super(fatima, fixed, position);
		partBuilder=getEntity().createPartBuilder();
		partBuilder.setShape( new Polygon(0,0,5,0,5,0.3f,0,0.3f));
		partBuilder.setCollisionSignature(0x10);
		partBuilder.build();
			 graphics= new ImageGraphics("wood.3.png", 5,0.3f,Vector.ZERO,1,1); 
			
			 graphics.setParent(getEntity());
		listener= new ContactListener() {
			
			@Override
			public void endContact(Contact contact) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beginContact(Contact contact) {
				// TODO Auto-generated method stub
				if(contact.getOther().getCollisionSignature()==0x8)
				{
					//if contact with the bomb
					explode=true;
					
				}
			}
		};
		getEntity().addContactListener(listener);
		// TODO Auto-generated constructor stub
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
		if(canvas==null)throw new NullPointerException("Argument type Canvas null to draw method in Wood");
		

		graphics.draw(canvas);

		
	}

	
	public void update(float deltaTime)
	{
		//the game removes it as an actor
		if(explode)
		getOwner().removeActor(this);
		
		
	}

}
