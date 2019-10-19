package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.bikegame.Bike;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Contact;
import ch.epfl.cs107.play.math.ContactListener;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Peaks extends GameEntity implements Actor {

	private float i;
	private  ContactListener contactListener;
	private PartBuilder partBuilder;
	private ImageGraphics imageGraphics, graphics;
	public Peaks(ActorGame fatima, boolean fixed, Vector position) {
		super(fatima, fixed, position);
		i=2f;
		partBuilder=getEntity().createPartBuilder();
		partBuilder.setShape( new Polygon(0,0,5,0,5,1,0,1));
		partBuilder.setGhost(false);
		partBuilder.setCollisionSignature(0x2);
		partBuilder.setFriction(100);
		partBuilder.build();
		imageGraphics= new ImageGraphics("spikes.png", 5f, 1);
		imageGraphics.setParent(getEntity());
		
		
		contactListener= new ContactListener() {
			
			@Override
			public void endContact(Contact contact) {
				
				
			}
			
			@Override
			public void beginContact(Contact contact) {
				
				contact.getOther().getEntity().destroy();
				
			}
		};
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
		if(canvas==null)throw new NullPointerException("Argument type Canvas null fo draw method in Peaks");
		imageGraphics.draw(canvas);
	
		// TODO Auto-generated method stub
	}
	
}
