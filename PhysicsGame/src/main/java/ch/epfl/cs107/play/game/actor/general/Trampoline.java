package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Trampoline extends GameEntity implements Actor{
	private ImageGraphics graphics;
	private PartBuilder partBuilder;
	private float timer=1;
	private BasicContactListener contactListener;
	public Trampoline(ActorGame game, boolean fixed, Vector position, Shape shape) {
		
		super(game, fixed, position);
		if(shape==null)throw new NullPointerException("Given shape null while constucting a Trampoline");
		
		timer=1f;
		partBuilder=getEntity().createPartBuilder();
		 partBuilder.setShape(shape);
		 //unique collision signature used by the wheels to recognise a jump
		 partBuilder.setCollisionSignature(0x6);
		 partBuilder.build();
	      graphics = new ImageGraphics("jumper.extended.png",3f,3f,Vector.ZERO,1,1);
		  graphics.setParent(getEntity());
		  contactListener = new BasicContactListener(); 
		  getEntity().addContactListener(contactListener);
}
	@Override
		public void draw( Canvas canvas) {
		if(canvas==null)throw new NullPointerException("Argument type Canvas null to draw method in Trampoline");
		
			
			graphics.draw(canvas);
	
		}
	public  void update(float deltaTime)
	{
		
		int numberOfCollisions = contactListener.getEntities().size();
		
		//making the trampoline look like it it extending after the jump and going back to normal
		if(numberOfCollisions>0)
		{
			
			graphics.setName("jumper.normal.png");
			timer=timer-35*deltaTime;
			return;
			}
		else
			{if(timer<0||timer==1)
				{graphics.setName("jumper.extended.png");
				timer=1;
				return;}
			timer=timer-3*deltaTime;
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
	
}