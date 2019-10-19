
package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.bikegame.Bike;

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

	private PartBuilder partBuilder;
	private WheelConstraint constraint;
	private ContactListener listener;
	private ImageGraphics graphics;
	private float radius,speed,timer;
	private boolean jump,broken, floatW;
	//the bike calls this method to know if it should jump, meaning the wheels have hit the trampoline
	public boolean getJump()
	{
		return jump;
	}
	public void setJump(boolean jump)
	{
		this.jump=jump;
	}
	public Wheel(ActorGame game, boolean fixed, Vector position, float radius) {
		super(game, fixed, position);
		if(radius<=0)throw new IllegalArgumentException("Given Radius smaller or equal to 0 for a Wheel");
		timer=1;
		listener = new ContactListener () {
			@Override
			public void beginContact(Contact contact) {
		
				if(contact.getOther().getCollisionSignature()==0x2){
					//contact with the peaks
					power(0);
					broken=true;
			}
				if(contact.getOther().getCollisionSignature()==0x6){
				//contact with the trampoline
					jump=true;
					
				}
				if(contact.getOther().getCollisionSignature()==0x7) {
				//contact with the gravity field
					floatW=true;
					return;
				}
			}
			@Override
			public void endContact(Contact contact) {
				if(contact.getOther().getCollisionSignature()==0x6)
					jump=false;
			
			
			}
			
			} ;
			getEntity().addContactListener(listener);
			
		this.radius=radius;
		Circle circle = new Circle(radius) ;
		partBuilder= getEntity().createPartBuilder();
		//setting a friction for better movement
		partBuilder.setFriction(4f);
		partBuilder.setShape(circle);
		partBuilder.build();
		 graphics = new ImageGraphics("spinner.dead.png", 2.0f*radius, 2.0f * radius, new Vector(radius, radius));
		 graphics.setParent(getEntity());
		// TODO Auto-generated constructor stub
	}
	public void power(float speed){
		
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
		
		constraintBuilder.setFirstAnchor(anchor) ;
		
		constraintBuilder.setSecondEntity(getEntity()) ;
		
		constraintBuilder.setSecondAnchor(Vector.ZERO) ;
		
		constraintBuilder.setAxis(axis) ;
		
		constraintBuilder.setFrequency (3.0f) ;
		constraintBuilder.setDamping (0.5f) ;
		
		constraintBuilder.setMotorMaxTorque (10.0f) ;
		
		constraint =constraintBuilder.build () ; 
	}
	@Override
	public Transform getTransform() {


		return getEntity().getTransform();
	}
	@Override
	public Vector getVelocity() {


	return	getEntity().getVelocity();
	}
	@Override
	public void draw(Canvas canvas) {
		if(canvas==null)throw new NullPointerException("Argument type Canvas null to draw method in Wheel");
		
		
		graphics.draw(canvas);
	}
	
	public void update(float deltaTime) 
	{//it has fallen on the peaks, so the wheel is detached from the bike
		if(broken) {
			detach();
			
		}
		
	}
	
}
