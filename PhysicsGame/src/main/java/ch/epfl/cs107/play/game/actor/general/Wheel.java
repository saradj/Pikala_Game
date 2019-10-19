
package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.bikegame.GameLevel;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.ConstraintBuilder;
import ch.epfl.cs107.play.math.Contact;
import ch.epfl.cs107.play.math.ContactListener;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WheelConstraint;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.window.Canvas;


public class Wheel extends GameEntity implements Actor{

	PartBuilder partBuilder;
	WheelConstraint constraint;
	ContactListener listener;
	ImageGraphics graphics;
	float radius,speed;
	
	public Wheel(ActorGame game, boolean fixed, Vector position, float radius) {
		super(game, fixed, position);
		listener = new ContactListener () {
			@Override
			public void beginContact(Contact contact) {
			//if (contact.getOther().isGhost ())
			//return ;
			//if(contact.getOther().getCollisionSignature()==0x1)
			// si contact avec les roues :
			//return ;
			//else
				if(contact.getOther().getCollisionSignature()==0x2)
			{
			power(0);
				
				
				getEntity().applyAngularForce(200);
				getEntity().applyAngularImpulse(-100);
				//System.out.println("wheel contact listener angular force after"+getEntity().getAngularVelocity());
			}
				else return;
			}
			@Override
			public void endContact(Contact contact) {}
			} ;
			getEntity().addContactListener(listener);
			
		this.radius=radius;
		Circle circle = new Circle(radius) ;
		partBuilder= getEntity().createPartBuilder();
		partBuilder.setFriction(4f);
		partBuilder.setShape(circle);
		//partBuilder.setCollisionEffect(0xffff);
		partBuilder.setCollisionSignature(0x1);
		partBuilder.build();
		 graphics = new ImageGraphics("spinner.dead.png", 2.0f*radius, 2.0f * radius, new Vector(radius, radius));
		 graphics.setParent(getEntity());
		// TODO Auto-generated constructor stub
	}
	public void power(float speed)
	{
		this.speed=speed;
		
			constraint.setMotorEnabled(true);
		    constraint.setMotorSpeed(speed);
			
		
	}
	public void relax()
	{
		constraint.setMotorEnabled(false);
	}
	public void detach ()
	{
		constraint.destroy();
	}
	
	@Override
	 public void destroy() {
		
		
	       getEntity().destroy();
	       detach();
	    }
	public float getSpeed ()
	{
		return getEntity().getAngularVelocity()-constraint.getMotorSpeed();
		
		
	}
	public void attach(Entity vehicle , Vector anchor , Vector axis)
	{
		WheelConstraintBuilder constraintBuilder = getOwner().WheelCreator() ; 
		constraintBuilder.setFirstEntity(vehicle) ;
		// point d'ancrage du v»hicule :
		constraintBuilder.setFirstAnchor(anchor) ;
		// Entity associ»e á la roue :
		constraintBuilder.setSecondEntity(getEntity()) ;
		// point d'ancrage de la roue (son centre) :
		constraintBuilder.setSecondAnchor(Vector.ZERO) ;
		// axe le long duquel la roue peut se d»placer :
		constraintBuilder.setAxis(axis) ;
		// fr»quence du ressort associ»
		constraintBuilder.setFrequency (3.0f) ;
		constraintBuilder.setDamping (0.5f) ;
		// force angulaire maximale pouvant Õtre appliqu»e
		//á la roue pour la faire tourner :
		constraintBuilder.setMotorMaxTorque (10.0f) ;
		//constraintBuilder.setInternalCollision(false);
		constraint =constraintBuilder.build () ; 
	}
	@Override
	public Transform getTransform() {
		// TODO Auto-generated method stub
		return getEntity().getTransform();
	}
	@Override
	public Vector getVelocity() {
		// TODO Auto-generated method stub
	return	getEntity().getVelocity();
	}
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		graphics.draw(canvas);
	}
	
	//public void update(float deltaTime) ???
	
}
/*
package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.ConstraintBuilder;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WheelConstraint;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.window.Canvas;

public class Wheel extends GameEntity implements Actor{

	PartBuilder partBuilder;
	WheelConstraint constraint;
	ImageGraphics graphics;
	float radius,speed;
	
	public Wheel(ActorGame game, boolean fixed, Vector position, float radius) {
		super(game, fixed, position);
		this.radius=radius;
		Circle circle = new Circle(radius) ;
		partBuilder= getEntity().createPartBuilder();
		partBuilder.setFriction(4f);
		partBuilder.setShape(circle);
		partBuilder.build();
		 graphics = new ImageGraphics("explosive.11.png", 2.0f*radius, 2.0f * radius, new Vector(radius, radius));
		 graphics.setParent(getEntity());
		// TODO Auto-generated constructor stub
	}
	public void power(float speed)
	{
		this.speed=speed;
		
			constraint.setMotorEnabled(true);
		    constraint.setMotorSpeed(speed);
			
		
	}
	public void relax()
	{
		constraint.setMotorEnabled(false);
	}
	public void detach ()
	{
		constraint.destroy();
	}
	
	@Override
	 public void destroy() {
	      
	       getEntity().destroy();
	       detach();
	    }
	public float getSpeed ()
	{
		return getEntity().getAngularVelocity()-constraint.getMotorSpeed();
		
		
	}
	public void attach(Entity vehicle , Vector anchor , Vector axis)
	{
		WheelConstraintBuilder constraintBuilder = getOwner().WheelCreator() ; 
		constraintBuilder.setFirstEntity(vehicle) ;
		// point d'ancrage du v»hicule :
		constraintBuilder.setFirstAnchor(anchor) ;
		// Entity associ»e á la roue :
		constraintBuilder.setSecondEntity(getEntity()) ;
		// point d'ancrage de la roue (son centre) :
		constraintBuilder.setSecondAnchor(Vector.ZERO) ;
		// axe le long duquel la roue peut se d»placer :
		constraintBuilder.setAxis(axis) ;
		// fr»quence du ressort associ»
		constraintBuilder.setFrequency (3.0f) ;
		constraintBuilder.setDamping (0.5f) ;
		// force angulaire maximale pouvant Õtre appliqu»e
		//á la roue pour la faire tourner :
		constraintBuilder.setMotorMaxTorque (10.0f) ;
		//constraintBuilder.setInternalCollision(false);
		constraint =constraintBuilder.build () ; 
	}
	@Override
	public Transform getTransform() {
		// TODO Auto-generated method stub
		return getEntity().getTransform();
	}
	@Override
	public Vector getVelocity() {
		// TODO Auto-generated method stub
	return	getEntity().getVelocity();
	}
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		graphics.draw(canvas);
	}
	
	//public void update(float deltaTime) ???
	
}
*/

