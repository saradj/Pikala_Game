package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Contact;
import ch.epfl.cs107.play.math.ContactListener;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class GravitationlessField extends GameEntity implements Actor{

	private ShapeGraphics shapeGraphics;
	private PartBuilder partBuilder;
	private Polygon polygon;
	private ContactListener listener;
	
	public GravitationlessField(ActorGame fatima, boolean fixed, Vector position) {
		super(fatima, fixed, position);
		//creating the physical parts
		polygon= new Polygon(0,-1,20,-1,20,10000,0,10000);
		partBuilder=getEntity().createPartBuilder();
		partBuilder.setShape(polygon);
		partBuilder.setGhost(true);
		//giving it a unique signature so the bike can recognise when it has entered it
		partBuilder.setCollisionSignature(0x7);
		partBuilder.build();
		//giving it an appearance as alight gray area
		shapeGraphics=new ShapeGraphics(polygon, Color.LIGHT_GRAY, Color.LIGHT_GRAY, 0.1f, 0.5f, 0.1f);
		shapeGraphics.setParent(getEntity());
		
		 
		listener = new ContactListener () {



			@Override
			public void beginContact(Contact contact) {
				//applies force muting gravity, on anything that enters in it
				contact.getOther().getEntity().applyForce(new Vector(0,contact.getOther().getEntity().getMass()*9.81f), null);
				
				
				
			}
			
			

			@Override
			public void endContact(Contact contact) {
				
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
		if(canvas==null)throw new NullPointerException("Argument type Canvas null fo draw method in GravitationlessField");
		shapeGraphics.draw(canvas);		
	}

}
