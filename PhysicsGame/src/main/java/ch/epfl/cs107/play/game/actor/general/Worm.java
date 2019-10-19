package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.math.Contact;
import ch.epfl.cs107.play.math.ContactListener;
import ch.epfl.cs107.play.math.Vector;

public class Worm extends Kinematic {
	private ContactListener listener;
	private int i=0;
	public Worm(ActorGame game, Vector position, Vector finalPosition, Vector speed, float width, float height) {
		super(game, position, finalPosition, speed, width, height, "worm.green.right.1.png");
		getPartBuilder().setCollisionSignature(0x2);
		getPartBuilder().build();
		listener = new ContactListener() {
			
			@Override
			public void endContact(Contact contact) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beginContact(Contact contact) {
				// TODO Auto-generated method stub
				contact.getOther().getEntity().destroy();
			}
		};
		getEntity().addContactListener(listener);
		// TODO Auto-generated constructor stub
	}
	
	public void update(float deltaTime)
	{
		
		
		if(getEntity().getPosition().getX()>finalPosition.getX()
				||getEntity().getPosition().getY()>finalPosition.getY()){
			
			getEntity().setVelocity(speed.opposite());
			//to create the ilusion that they are moving their bodies
			if(i%2==0)
			graphics.setName("worm.green.left.1.png");
			else
				
				graphics.setName("worm.green.left.2.png");
			i++;
			return;
		}
	if(getEntity().getPosition().getX()<position.getX()
			||getEntity().getPosition().getY()<position.getY()){
		
		getEntity().setVelocity(speed);
		if(i%2==0)
		graphics.setName("worm.green.right.1.png");
		else
			graphics.setName("worm.green.right.2.png");
		i++;
		return;
		
	}

	}

}
