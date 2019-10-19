package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.bikegame.PikalaGame;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;


public class Pickup extends Triggers {
	
	private ImageGraphics graphics;
	protected ImageGraphics getGraphics()
	{
		return graphics;
	}
	//the game calls this method to reset the score after restarting
	static public void resetScore()
	{
		score=0;
	}
	//a static counter for the score that counts every pick up collected
	 private static int score=0;
	
	public Pickup(ActorGame game, boolean fixed, Vector position, Shape shape) {
		super(game, fixed, position,shape);
		graphics=new ImageGraphics("star.gold.png",1f,1f, new Vector(0.5f,0.5f));
		graphics.setParent(getEntity());
 
	}
	@Override
	public void draw(Canvas canvas) {
		if(canvas==null)throw new NullPointerException("Argument type Canvas null fo draw method in PickUp");
		
		graphics.draw(canvas);

	}
	public  void update(float deltaTime)
	{
		super.update(deltaTime); 
		if(haveColided()) {
			//if the inherited method from Triggers says that the biker picked it up, the score increases by 1 
			++score;
			//notifying the game about the score change
			((PikalaGame)getOwner()).Score(score);
			getOwner().removeActor(this);
			
			}
		
	}
	

}
