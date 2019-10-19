package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.bikegame.GameLevel;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.ContactListener;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

/*public class Finish extends GameEntity implements Actor{
	private PartBuilder partBuilder;
	private ImageGraphics graphics;
	private BasicContactListener contactListener;
	private boolean end;
	public boolean getEnd()
	{
		return end;
	}
	private float radius=0.5f;
	@Override
	public void destroy() {
	    getEntity().destroy();
	 }
	public Finish(ActorGame game, boolean fixed, Vector position) {
		super(game, fixed, position);
		contactListener = new BasicContactListener(); 
	    
		
		partBuilder=getEntity().createPartBuilder();
		Circle circle = new Circle(radius);
		partBuilder.setShape(circle);
		partBuilder.setGhost(true);
		 partBuilder.build();
		  graphics = new ImageGraphics("flag.red.png",1f,1f, new Vector(0.5f,0.5f));
		  graphics.setParent(getEntity());
		 getEntity().addContactListener(contactListener);
		
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
		// TODO Auto-generated method stub
		graphics.draw(canvas);
		
	}

	public  void update(float deltaTime)
	{
		int numberOfCollisions = contactListener.getEntities().size() ;
		if (numberOfCollisions > 0){
		System.out.println("colllision");
		end=true;
	
		}
	}

	

}*/
public class Finish extends Triggers{

	

	private ImageGraphics graphics;
	

	public Finish(ActorGame game, boolean fixed, Vector position, Shape shape)
	
	{
		super(game, fixed, position,shape);
		
		 graphics = new ImageGraphics("flag.red.png",1f,1f, new Vector(0.5f,0.5f));
		  graphics.setParent(getEntity());
		  
		// TODO Auto-generated constructor stub
	}
	@Override
	public void draw(Canvas canvas) {
		
		graphics.draw(canvas);

	}
	public void update(float deltaTime)
	{
		super.update(deltaTime);
		if(haveColided())
			((GameLevel)game).setLevelPassed(true);
	}
}

