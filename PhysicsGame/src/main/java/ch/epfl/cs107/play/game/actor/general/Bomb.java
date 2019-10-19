package ch.epfl.cs107.play.game.actor.general;

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

public class Bomb extends GameEntity implements Actor{
	private PartBuilder partBuilder;
	private ImageGraphics graphics,smoke;
	private float timer;
	private ContactListener contactListener;
	boolean exploded;
	
	public Bomb(ActorGame fatima, boolean fixed, Vector position) {
		super(fatima, fixed, position);
		timer=2;
		//physical parts
		partBuilder=getEntity().createPartBuilder();
		 partBuilder.setShape(new Polygon(0,0,2,0,2,2,0,2)) ;
		 partBuilder.setCollisionSignature(0x8);
		 partBuilder.build() ;
		 graphics = new ImageGraphics("bomb.png",2f,2f, new Vector(0,0));
		  graphics.setParent(getEntity());
		 //making it sensitive to contact
		contactListener = new ContactListener () {

			@Override
			public void beginContact(Contact contact) {
				
				if(contact.getOther().getCollisionSignature()==0x1)
					//if contact with crate, because we need it to stand on it 
					return;
				contact.getOther().getEntity().applyImpulse(new Vector(-10,0), null);
							//if contact with anything else
				exploded=true;
				
				
			}

			@Override
			public void endContact(Contact contact) {
				
				
				
			}
			  
		  };
		  getEntity().addContactListener(contactListener);
		// TODO Auto-generated constructor stub

	}
	

	public void update(float deltaTime)
	{
		//slowly disapearing into smoke if it has exploded, using a timer
		if(exploded)
		{
			
			graphics.setAlpha(timer);
			graphics.setWidth(timer*4);
			graphics.setHeight(timer*4);
			timer=timer-deltaTime;
			graphics.setName("smoke.gray.3.png");
				if(timer<0)	
					//removing it after it has exploded
				getOwner().removeActor(this);		
		}
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
		if(canvas==null)throw new NullPointerException("Argument type Canvas null fo draw method in Bomb");
		// TODO Auto-generated method stub
		graphics.draw(canvas);

	}
	

}
