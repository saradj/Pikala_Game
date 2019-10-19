
package ch.epfl.cs107.play.game.actor.bikegame;



import java.awt.Color;
import java.awt.event.KeyEvent;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.general.Wheel;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Contact;
import ch.epfl.cs107.play.math.ContactListener;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Bike extends GameEntity implements Actor {
	
    private float timer=0;
    private int i=0;
	private boolean rocket, hit, lookRight=true, handUp=false, floatB;
	private Vector headAbsolutePosition;
	private ContactListener listener;
	private PartBuilder partBuilder;
	private Wheel wheelR, wheelL;
	private Polygon polygon=new Polygon(
	    		0.0f, 0.5f,
	    		0.5f, 1.0f,
	    		0.0f, 2.0f,
	    		-0.5f, 1.0f);
	private Polyline arm, feetG, feetD, jointRight, jointLeft, saddle;
	private Circle head;
	private final float MAX_WHEEL_SPEED = 20.0f;
	
	
	
	public Vector getHeadAbsolutePosition()
	{
		return headAbsolutePosition;
	}
	//called by the games to check if the game is over
	protected boolean hasFallen()
	{
		return hit;
	}
	
	
	
	private Vector getHeadLocation () {
		return new Vector (0.0f,1.9f);
	}
	
	private Vector getShoulderLocation(){
		return new Vector(-.1f, 1.6f);
	}
	
	private Vector getSaddleLocation(){
		if(lookRight)
			
			return new Vector(-0.4f,1f);
		else
			return new Vector(0.4f,1f);
		
	}
	
	private Vector getJointLeftLocation()
	
	{//if the player is pushing the brakes the biker is not pedalling, 
		//it's joint location is constant in the moving coordinate system
		if(getOwner().getKeyboard().get(KeyEvent.VK_DOWN).isDown()){
			
			if (lookRight)
				return new Vector(-0.1f,0.7f);
			else
				return new Vector(0.1f,0.7f);
			
		}
		else {
			//the biker is pedalling using the fact that his joint is drawing a circle around the joint location
			//as an origin and a radius 0.2, using the equation x=a+r*cos(angle of the motor wheel) y=b+r*sin(angle of motor wheel
		if(lookRight)
			return  new Vector((float) ((0.1+0.2*(Math.cos(Math.PI-wheelL.getEntity().getAngularPosition())))),
					(float) ( (0.7+0.2*(Math.sin(-(wheelL.getEntity().getAngularPosition()))))));
			
	else
			
		return  new Vector((float) ((0.1+0.2*(Math.cos( Math.PI-wheelR.getEntity().getAngularPosition())))),
				(float) ( (0.7+0.2*(Math.sin(-(wheelR.getEntity().getAngularPosition()))))));	
		}	
	}
	
	private Vector getJointRightLocation(){
		if(getOwner().getKeyboard().get(KeyEvent.VK_DOWN).isDown()){
			if (lookRight)
				return new Vector(0.3f,0.7f);
			else
				return new Vector(-0.3f,0.7f);
			
		}
		else{
		if(lookRight){	
			//using the opposite values for the sin and cos than the ones for the other joint
			//to achieve the proper visual effect, one joint starts from down, the other from up
			
			return  new Vector((float) ((0.1+0.2*(Math.cos( wheelL.getEntity().getAngularPosition())))),
			(float) ( (0.7+0.2*(Math.sin((wheelL.getEntity().getAngularPosition()))))));
		}
			
		else
		
			return  new Vector((float) ((0.1+0.2*(Math.cos( wheelR.getEntity().getAngularPosition())))),
					(float) ( (0.7+0.2*(Math.sin((wheelR.getEntity().getAngularPosition()))))));	
	}
	
	}
	//the location of the foot is following the movement of the joint with a certain translation
	private Vector getFeetDLocation(){
	
	
		if(lookRight)
		return getJointRightLocation().add(new Vector(-0.1f,-0.4f));
				
			else 
				return getJointRightLocation().add(new Vector(0.1f,-0.4f));	
	}
	

	private Vector getFeetGLocation(){
		if(lookRight)
			return getJointLeftLocation().add(new Vector(-0.1f,-0.4f));
		else 
			return getJointLeftLocation().add(new Vector(0.1f,-0.4f));	
	}
	
	public Vector getHandLocation(){	
		
		if(!handUp) {
			if(lookRight)
		
				return new Vector(0.5f,1.1f);
			
			else
				return new Vector(-0.5f, 1.1f);
		}
		//if she has won the hand goes up, by increasing the y coordinate
		else{
			if(lookRight)
				return new Vector(0.5f,2.1f);
			else
				return new Vector(-0.5f,2.1f);
		}		
	}
	
	
		//constructor
	public Bike(ActorGame game, boolean fixed, Vector position,Shape shape) {
		super(game,fixed,position);
		if(shape==null)throw new NullPointerException("Argument shape given to Bike is null");
		//initialising the timer
		timer=0.5f;
		//creating the physical entity of the hitbox
		partBuilder=getEntity().createPartBuilder();
		partBuilder.setShape(shape);
		partBuilder.setGhost(true);
	    partBuilder.build();
	    i=0;
	    //adding sensors to the bike
		 listener = new ContactListener () {
			
			@Override
			public void beginContact(Contact contact) {
		
				if (contact.getOther().isGhost ())
					{
					//contact with the rocket
					if(contact.getOther().getCollisionSignature()==0x4)	{
						rocket=true;
						return;
						}
					//contact with the finishLine
					if(contact.getOther().getCollisionSignature()==0x5){
					handUp=true;
					return;
					}//contact with  gravity field
					if(contact.getOther().getCollisionSignature()==0x7){
						floatB=true;
						return;
					}
				}
					
			
			if(contact.getOther().getEntity().equals(wheelR.getEntity())||contact.getOther().getEntity().equals(wheelL.getEntity()))
			// if contact with wheels
			return ;
			//contact with everything else that is physically present will kill the biker
		if(!contact.getOther().isGhost()){
				
				hit=true;
				 return;
			}
		}
			
			@Override
			public void endContact(Contact contact) {
				
				}
			} ;
			getEntity().addContactListener(listener);
			
		//creating the wheels
		 setWheelL(new Wheel(getOwner(), false, getEntity().getPosition().add(-1.0f,0.0f), 0.5f));
		 setWheelR(new Wheel(getOwner(), false, getEntity().getPosition().add(1.0f,0.0f), 0.5f));
		 //attaching the wheels to the hitbox
		 getWheelL().attach(getEntity(), new Vector (-1.0f, 0.0f), new Vector (-0.5f, -1.0f));
		 getWheelR().attach(getEntity() ,new Vector (1.0f, 0.0f), new Vector (0.5f, -1.0f));
	
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
		head = new Circle (0.2f, getHeadLocation ()) ;
		arm = new Polyline(getShoulderLocation (),getHandLocation()) ;
		saddle = new Polyline(getHeadLocation (),getSaddleLocation());
		jointRight = new Polyline(getSaddleLocation(),getJointRightLocation());
		feetD= new Polyline(getJointRightLocation(),getFeetDLocation());
		feetG = new Polyline(getJointLeftLocation(), getFeetGLocation());
		jointLeft = new Polyline(getSaddleLocation(),getJointLeftLocation());
		canvas.drawShape(arm , this.getTransform (), Color.PINK , Color.PINK ,
				0.1f, 1f, 1.f) ;
		canvas.drawShape(head , this.getTransform (), Color.PINK , Color.PINK ,0.1f, 1f, 1.f);
		canvas.drawShape(saddle , this.getTransform (), Color.PINK , Color.PINK ,
				0.1f, 1f, 1.f) ;
		canvas.drawShape(jointRight , this.getTransform (), Color.PINK , Color.PINK ,
				0.1f, 1f, 1.f) ;
		canvas.drawShape(jointLeft , this.getTransform (), Color.PINK , Color.PINK ,
			0.1f, 1f, 1.f) ;
		canvas.drawShape(feetD , this.getTransform (), Color.PINK , Color.PINK ,
			0.1f, 1f, 1.f) ;
		canvas.drawShape(feetG , this.getTransform (), Color.PINK , Color.PINK ,
			0.1f, 1f, 1.f) ;
		
		
		canvas.drawShape(polygon , this.getTransform (), Color.BLACK , Color.black ,
	   0.1f, 0.1f, 0.5f);
		
	}
	
	@Override
	public void destroy() {
		super.destroy();
	    getEntity().destroy();
	    
	    getWheelL().destroy();
	    getWheelR().destroy();
	   
	 }
	//just destroy the hitbox, the entity, to detach the wheels, for a visual effect
	public void destroyTheBike()
	{
		super.destroy();
	}
	
	
		public void setPosition(Vector position)
		{
			getEntity().setPosition(position);
			
		}  
		
		

	public  void update(float deltaTime) { 
		
		getWheelL().relax();
		getWheelR().relax();
		headAbsolutePosition = this.getTransform ().onPoint(getHeadLocation ()) ;
				if(rocket){
		//applying impulse to make it move further if collision with the rocket
					getEntity().applyImpulse(new Vector(7,15), null);
					rocket=false;
				}
		//getting the notification from the wheels that they have hit the trampoline
		if(wheelR.getJump()||wheelL.getJump()){
			
			//to make a cleaner jump
			getEntity().setAngularPosition(0);
			getEntity().setAngularVelocity(0);
			getEntity().setVelocity(new Vector(0,0));
			//apply the impulse only in the y direction
	    	getEntity().applyImpulse(new Vector(0f,20f),null);
			wheelR.setJump(false);
			wheelL.setJump(false);
			return;
		
		}
		if(floatB){
		//getEntity().applyForce(new Vector(0,0), null);	
		getEntity().cancelGravity();
		wheelR.getEntity().cancelGravity();
		wheelL.getEntity().cancelGravity();
		}
		//knowing that the gravity field starts at x=96 and ends x=110 
		//if the bike is outside it, it doesn't float anymore
		if(this.getPosition().x>110||this.getPosition().x<96) {
			getEntity().restoreGravity();
			wheelR.getEntity().restoreGravity();
			wheelL.getEntity().restoreGravity();
			floatB=false;
		}
		//if it has fallen or is hit by smth
		if(hit){
			
			destroyTheBike();
			timer=timer-deltaTime;
			//do the actual destroying and removing from the actors list after the timer has counted, just for visual effect
			if(timer<0){
			destroy();
			getOwner().removeActor(this);
			getOwner().removeActor(getWheelL());
			getOwner().removeActor(getWheelR());
			}
		
		}
		//changing the direction of the bike
		if (getOwner().getKeyboard().get(KeyEvent.VK_SPACE).isPressed()) { 
			if(lookRight){
				
				lookRight=false;
			}
			else
				lookRight=true;
		}
		

		//stopping the bike
		if(getOwner().getKeyboard().get(KeyEvent.VK_DOWN).isDown()){
			
			getWheelL().power(0);
			getWheelR().power(0);
			
		}
		//speeding the bike up
		if(getOwner().getKeyboard().get(KeyEvent.VK_UP).isDown())
		{
			
			if(lookRight)
			{
				if(getWheelL().getSpeed()>-MAX_WHEEL_SPEED)
				{
					
				getWheelL().power(-MAX_WHEEL_SPEED);
				}
			}
			else
				if(getWheelR().getSpeed()<MAX_WHEEL_SPEED)
					
				{
					
				getWheelR().power(MAX_WHEEL_SPEED);
				}
			
	
		}
		if(getOwner().getKeyboard().get(KeyEvent.VK_LEFT).isDown()){
			getEntity().applyAngularForce(10.0f) ;
		}
		if(getOwner().getKeyboard().get(KeyEvent.VK_RIGHT).isDown()){
			getEntity().applyAngularForce(-10.0f) ;
		}
		if(handUp){
			getWheelL().power(0);
			getWheelR().power(0);
		}
		
	}
	//to give access to the games to add the wheels
	public Wheel getWheelL() {
		return wheelL;
	}
	
	private void setWheelL(Wheel wheelL) {
		this.wheelL = wheelL;
	}
	public Wheel getWheelR() {
		return wheelR;
	}
	private void setWheelR(Wheel wheelR) {
		this.wheelR = wheelR;
	} 
}





