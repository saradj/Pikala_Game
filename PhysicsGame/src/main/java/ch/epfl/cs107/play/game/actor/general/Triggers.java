package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.bikegame.BikeGame;
import ch.epfl.cs107.play.game.actor.bikegame.GameLevel;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Triggers extends GameEntity implements Actor {

	private PartBuilder partBuilder;
	protected PartBuilder getPartBuilder()
	{
		return partBuilder;
	}
	private boolean haveColided;
	protected static float  timer=0;
	static int numberOfCollisions=0;
	public boolean haveColided()
	{
		return haveColided;
	}
public void setHaveCollided(boolean end)
{
	this.haveColided=end;
}
	private  BasicContactListener contactListener;
	
	public Triggers(ActorGame game, boolean fixed, Vector position,Shape shape) {
		super(game, fixed, position);
		numberOfCollisions=0;
		timer=0f;
		haveColided=false;
		partBuilder=getEntity().createPartBuilder();
		 partBuilder.setShape(shape) ;
		 partBuilder.setGhost(true);
		 partBuilder.build() ;
		 contactListener = new BasicContactListener(); 
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
		// TODO Auto-generated method stub
		
	}
	public  void update(float deltaTime)
	{
		 numberOfCollisions = contactListener.getEntities().size();
		//System.out.println("call update nb collisons"+numberOfCollisions+"  have colllided "+ haveColided);
//System.out.println("contact listener list"+contactListener.getEntities()+" view entity "+((GameEntity)game.getViewCandidate()).getEntity());
		if (numberOfCollisions >0&&(contactListener.getEntities().contains(((GameEntity)game.getViewCandidate()).getEntity()))){
		
		 
		//System.out.println("collision");
		haveColided=true;
		
		//game.removeActor(this);
		}
		/*if(haveColided)
		{
			timer=timer-deltaTime;
		System.out.println("timer is "+ timer+"delta time "+deltaTime);
		if(timer==0)
			{
			System.out.println("it is in ");
			game.addActor(this);
			timer=Float.POSITIVE_INFINITY;
			}
		}*/
	}
}
