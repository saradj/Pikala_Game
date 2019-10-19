
package ch.epfl.cs107.play.game.actor.bikegame;
/*
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

	private boolean hit;
	Vector handLocation;
	
	protected boolean getHit()
	{
		return hit;
	}
	protected void setHit(boolean hit)
	{
		this.hit=hit;
	}
	EntityBuilder entityBuilder;
	PartBuilder partBuilder;
	Wheel wheelR, wheelL;
	static Polygon polygon=new Polygon(
	    		0.0f, 0.5f,
	    		0.5f, 1.0f,
	    		0.0f, 2.0f,
	    		-0.5f, 1.0f);
	
	private final float MAX_WHEEL_SPEED = 20.0f;
	private boolean lookRight=false;
 boolean handUp=false;
	public Bike(ActorGame game, boolean fixed, Vector position,Shape shape) {
		super(game,fixed,position);
		partBuilder=getEntity().createPartBuilder();
		partBuilder.setShape(shape);
		partBuilder.setGhost(true);
	partBuilder.build();
		ContactListener listener = new ContactListener () {
			@Override
			public void beginContact(Contact contact) {
			if (contact.getOther().isGhost ())
			return ;
			if(contact.getOther().getCollisionSignature()==0x1)
			// si contact avec les roues :
			return ;
			else
			{
				hit = true ;
				((GameLevel)getOwner()).setHasFallen(true);
			}
			}
			@Override
			public void endContact(Contact contact) {}
			} ;
			getEntity().addContactListener(listener);
			
		
		 wheelR = new Wheel(game, false, getEntity().getPosition().add(-1.0f,0.0f), 0.5f);
		 wheelL = new Wheel(game, false, getEntity().getPosition().add(1.0f,0.0f), 0.5f);
		 wheelR.attach(getEntity(), new Vector (-1.0f, 0.0f), new
				 Vector (-0.5f, -1.0f));
		 wheelL.attach(getEntity() ,new Vector (1.0f, 0.0f), new
					Vector (0.5f, -1.0f));
	
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
		Circle head = new Circle (0.2f, getHeadLocation ()) ;
		
		Polyline arm = new Polyline(
				getShoulderLocation (),
				getHandLocation()) ;
		canvas.drawShape(arm , this.getTransform (), Color.YELLOW , Color.GREEN ,
				0.1f, 1f, 1.f) ;
		canvas.drawShape(head , this.getTransform (), Color.YELLOW , Color.black ,0.1f, 1f, 1.f);
		canvas.drawShape(polygon , this.getTransform (), Color.YELLOW , Color.black ,
	    0.1f, 0.5f, 0.5f);
		
	}
	@Override
	public void destroy() {
		super.destroy();
	    getEntity().destroy();
	    
	    wheelL.destroy();
	    wheelR.destroy();
	    
	 }
	//just destroy the hitbox, the entity
	public void destroyTheBike()
	{
		super.destroy();
	}
	
		
		// TODO Auto-generated method stub
		private Vector getHeadLocation () {
			return new Vector (0.0f,1.75f) ;
		}
		private Vector getShoulderLocation()
		{
			return new Vector(0.0f, 1.55f);
			
		}
		public void setHandLocation(Vector handLocation)
		{
				
			this.handLocation=handLocation;
		}
		public Vector getHandLocation()
		
		{	if(!handUp) {
			if(lookRight)
			
		return new Vector(1.5f,1.3f);
				
			
					else
			return new Vector(-1.5f, 1.3f);
			}
			else
			{
				if(lookRight)
				return new Vector(1.5f,2.3f);
				else
					return new Vector(-1.5f,2.3f);
			}
				
		}
			
		
	
		public void setPosition(Vector position)
		{
			getEntity().setPosition(position);
		}
	

	public  void update(float deltaTime) { 
		wheelL.relax();
		wheelR.relax();
		if(hit)
			{
			
			destroyTheBike();
			
			}
		if (getOwner().getKeyboard().get(KeyEvent.VK_SPACE).isPressed()) { 
			if(lookRight)
			{
				lookRight=false;
				
			}
			else
				lookRight=true;
		}
		
		//wheelL.power(0);
		//wheelR.power(0);

		if(getOwner().getKeyboard().get(KeyEvent.VK_DOWN).isDown())
		{
			wheelL.power(0);
			
			wheelR.power(0);
		}
		if(getOwner().getKeyboard().get(KeyEvent.VK_UP).isDown())
		{
			if(lookRight)
			{
				if(wheelL.getSpeed()>-MAX_WHEEL_SPEED)
				{
				wheelL.power(-MAX_WHEEL_SPEED);
				}
			}
			else
				if(wheelR.getSpeed()<MAX_WHEEL_SPEED)
					
				{
				wheelR.power(MAX_WHEEL_SPEED);
				}
			
	
		}
		if(getOwner().getKeyboard().get(KeyEvent.VK_LEFT).isDown())
		{
			getEntity().applyAngularForce (10.0f) ;
		}
		if(getOwner().getKeyboard().get(KeyEvent.VK_RIGHT).isDown())
		{
			getEntity().applyAngularForce (-10.0f) ;
		}
		if(handUp)
		{
			wheelL.power(0);
			
			wheelR.power(0);
		}
		
		// By default, actors have nothing to update 
	} 
}
*/


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
    int i =0;
	private boolean hit;
	Vector handLocation;
	Vector feetDLocation= new Vector(-0.3f,-0.15f);
	Vector feetGLocation = new Vector(0.3f,-0.15f);
	final static Vector feetG=new Vector(0.3f,-0.15f);
	final static Vector feetD=new Vector(-0.3f,-0.15f);
	
	Vector middleLocation = new Vector(0f,-0.15f);
	Vector feetRLocation;
	
	protected boolean getHit()
	{
		return hit;
	}
	protected void setHit(boolean hit)
	{
		this.hit=hit;
	}
	EntityBuilder entityBuilder;
	PartBuilder partBuilder;
	private Wheel wheelR;
	private Wheel wheelL;
	static Polygon polygon=new Polygon(
	    		0.0f, 0.5f,
	    		0.5f, 1.0f,
	    		0.0f, 2.0f,
	    		-0.5f, 1.0f);
	
	private final float MAX_WHEEL_SPEED = 20.0f;
	private boolean lookRight=true;
 boolean handUp=false;
	public Bike(ActorGame game, boolean fixed, Vector position,Shape shape) {
		super(game,fixed,position);
		partBuilder=getEntity().createPartBuilder();
		partBuilder.setShape(shape);
		partBuilder.setGhost(true);
	    partBuilder.build();
		ContactListener listener = new ContactListener () {
			@Override
			public void beginContact(Contact contact) {
		
				if (contact.getOther().isGhost ())
					if(contact.getOther().getCollisionSignature()==0x4)
						{
						getEntity().applyAngularForce(50);
						//getEntity().applyAngularImpulse(-50);
						return;
						}
					else
			return ;
			if(contact.getOther().getCollisionSignature()==0x1)
			// si contact avec les roues :
			return ;
			else
			{
				hit = true ;
				((GameLevel)getOwner()).setHasFallen(true);
			}
			}
			@Override
			public void endContact(Contact contact) {}
			} ;
			getEntity().addContactListener(listener);
			
		
		 setWheelL(new Wheel(game, false, getEntity().getPosition().add(-1.0f,0.0f), 0.5f));
		 setWheelR(new Wheel(game, false, getEntity().getPosition().add(1.0f,0.0f), 0.5f));
		 getWheelL().attach(getEntity(), new Vector (-1.0f, 0.0f), new
				 Vector (-0.5f, -1.0f));
		 getWheelR().attach(getEntity() ,new Vector (1.0f, 0.0f), new
					Vector (0.5f, -1.0f));
	
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
		Circle head = new Circle (0.2f, getHeadLocation ()) ;
		//System.out.println("position of right joit"+ getJointRightLocation() + "  and length  "+ getJointRightLocation().getLength());
		Polyline arm = new Polyline(
				getShoulderLocation () ,
				getHandLocation()) ;
		Polyline saddle = new Polyline(getHeadLocation (),
				getSaddleLocation());
		Polyline jointRight = new Polyline(getSaddleLocation(),getJointRightLocation());
		Polyline feetD= new Polyline(getJointRightLocation(),getFeetDLocation());
		Polyline feetG = new Polyline(getJointLeftLocation(), getFeetGLocation());
		Polyline jointLeft = new Polyline(getSaddleLocation(),getJointLeftLocation());
		canvas.drawShape(arm , this.getTransform (), Color.YELLOW , Color.GREEN ,
				0.1f, 1f, 1.f) ;
		canvas.drawShape(head , this.getTransform (), Color.GREEN , Color.GREEN ,0.1f, 1f, 1.f);
		canvas.drawShape(saddle , this.getTransform (), Color.YELLOW , Color.GREEN ,
				0.1f, 1f, 1.f) ;
		canvas.drawShape(jointRight , this.getTransform (), Color.YELLOW , Color.GREEN ,
				0.1f, 1f, 1.f) ;
		canvas.drawShape(jointLeft , this.getTransform (), Color.YELLOW , Color.GREEN ,
			0.1f, 1f, 1.f) ;
		canvas.drawShape(feetD , this.getTransform (), Color.YELLOW , Color.GREEN ,
			0.1f, 1f, 1.f) ;
		canvas.drawShape(feetG , this.getTransform (), Color.YELLOW , Color.GREEN ,
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
	//just destroy the hitbox, the entity
	public void destroyTheBike()
	{
		super.destroy();
	}
	
		
		// TODO Auto-generated method stub
		private Vector getHeadLocation () {
			return new Vector (0.0f,1.9f) ;
		}
		private Vector getShoulderLocation()
		{
			return new Vector(-.1f, 1.6f);
			
		}
		private Vector getSaddleLocation(){
			if(lookRight)
				
				return new Vector(-0.4f,1f);
			else
				return new Vector(0.4f,1f);
			
		}
		
		private Vector getJointLeftLocation()
		{
			if(lookRight)
				return  new Vector((float) ((0.1+0.2*(Math.cos(Math.PI-wheelL.getEntity().getAngularPosition())))),
						(float) ( (0.7+0.2*(Math.sin(-(wheelL.getEntity().getAngularPosition()))))));
				//return new Vector((float) ((-0.4+(float)0.61*(Math.cos( Math.abs(wheelL.getEntity().getAngularPosition())%(Math.PI/4))))),
					//(float) ((float) (1+0.61*(Math.sin(- Math.abs(wheelL.getEntity().getAngularPosition())%(Math.PI/4))))));
				//return new Vector(0.2f,0.8f);
				//return (getJointRightLocation().mirrored(new Vector(0,1))).add(new Vector(0,1));
		else
				//return new Vector((float) (-(-0.4+(float)0.61*(Math.cos( -Math.abs(wheelR.getEntity().getAngularPosition())%(Math.PI/4))))),
					//	(float) ((float) (1+0.61*(Math.sin(- Math.abs(wheelR.getEntity().getAngularPosition())%(Math.PI/4))))));
			return  new Vector((float) ((0.1+0.2*(Math.cos( Math.PI-wheelR.getEntity().getAngularPosition())))),
					(float) ( (0.7+0.2*(Math.sin(-(wheelR.getEntity().getAngularPosition()))))));	
		}
		//////
		
		
		//*****
		private Vector getJointRightLocation(){
			if(lookRight)
				
			{	
				
	return  new Vector((float) ((0.1+0.2*(Math.cos( wheelL.getEntity().getAngularPosition())))),
				(float) ( (0.7+0.2*(Math.sin((wheelL.getEntity().getAngularPosition()))))));
			}
				//return (new Vector())
			else
			
				return  new Vector((float) ((0.1+0.2*(Math.cos( wheelR.getEntity().getAngularPosition())))),
						(float) ( (0.7+0.2*(Math.sin((wheelR.getEntity().getAngularPosition()))))));	
		}
		
		
		private Vector getFeetDLocation()
		{
				if(lookRight)
			return getJointRightLocation().add(new Vector(-0.1f,-0.4f));
					//return getJointRightLocation().x+
				else 
					return getJointRightLocation().add(new Vector(0.1f,-0.4f));	
		}
		
		
		
		private Vector getFeetGLocation(){
			if(lookRight)
				return getJointLeftLocation().add(new Vector(-0.1f,-0.4f));
			else 
				return getJointLeftLocation().add(new Vector(0.1f,-0.4f));	
		}
		
	
	
		public void setHandLocation(Vector handLocation)
		{
				
			this.handLocation=handLocation;
		}
		public Vector getHandLocation()
		
		{	if(!handUp) {
			if(lookRight)
			
		return new Vector(0.5f,1.1f);
				
			
					else
			return new Vector(-0.5f, 1.1f);
			}
			else
			{
				if(lookRight)
				return new Vector(0.5f,2.1f);
				else
					return new Vector(-0.5f,2.1f);
			}
				
		}
			
		
	
		public void setPosition(Vector position)
		{
			getEntity().setPosition(position);
			
		}  
		
		/*private void pedal(){
		  	this.setFeetDLocation(middleLocation);
			this.setFeetGLocation(middleLocation);
			
			
		
			
		}
		private void pedal1(){
			
			this.setFeetDLocation(this.feetG);
			this.setFeetGLocation(this.feetD);
		}
		
	*/

	public  void update(float deltaTime) { 
		
		
		getWheelL().relax();
		getWheelR().relax();
		
		
		
		
		if(hit)
			{
			
			destroyTheBike();
			
			}
		
		if (getOwner().getKeyboard().get(KeyEvent.VK_SPACE).isPressed()) { 
			if(lookRight)
			{
				lookRight=false;
				
			}
			else
				lookRight=true;
		}
		
		
		//wheelL.power(0);
		//wheelR.power(0);

		if(getOwner().getKeyboard().get(KeyEvent.VK_DOWN).isDown())
		{
			getWheelL().power(0);
			
			getWheelR().power(0);
		}
		if(getOwner().getKeyboard().get(KeyEvent.VK_UP).isDown())
		{
			
			if(lookRight)
			{
				if(getWheelL().getSpeed()>-MAX_WHEEL_SPEED)
				{
					//System.out.println("wheel left an angular position "+(wheelL.getEntity().getAngularPosition()%(2*Math.PI))*(180/Math.PI));
					//System.out.println("wheel right an angular position "+wheelR.getEntity().getAngularPosition());
				getWheelL().power(-MAX_WHEEL_SPEED);
				}
			}
			else
				if(getWheelR().getSpeed()<MAX_WHEEL_SPEED)
					
				{
					//System.out.println("wheel right an angular position "+wheelR.getEntity().getAngularPosition());
				getWheelR().power(MAX_WHEEL_SPEED);
				}
			
	
		}
		if(getOwner().getKeyboard().get(KeyEvent.VK_LEFT).isDown())
		{
			getEntity().applyAngularForce (10.0f) ;
		}
		if(getOwner().getKeyboard().get(KeyEvent.VK_RIGHT).isDown())
		{
			getEntity().applyAngularForce (-10.0f) ;
		}
		if(handUp)
		{
			getWheelL().power(0);
			
			getWheelR().power(0);
		}
		// By default, actors have nothing to update 
	}
	public Wheel getWheelL() {
		return wheelL;
	}
	public void setWheelL(Wheel wheelL) {
		this.wheelL = wheelL;
	}
	public Wheel getWheelR() {
		return wheelR;
	}
	public void setWheelR(Wheel wheelR) {
		this.wheelR = wheelR;
	} 
}





