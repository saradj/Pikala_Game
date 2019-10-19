package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.bikegame.GameLevel;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import sun.awt.image.ImageAccessException;

public class Pickup extends Triggers {
	
	private ImageGraphics graphics;
	protected ImageGraphics getGraphics()
	{
		return graphics;
	}

	public static void setScore(int score)
	{
		Pickup.score=score;
	}
	 public static int score=0;
	
	public Pickup(ActorGame game, boolean fixed, Vector position, Shape shape) {
		super(game, fixed, position,shape);
		graphics=new ImageGraphics("star.gold.png",1f,1f, new Vector(0.5f,0.5f));
		  graphics.setParent(getEntity());
 //timer=0.1f;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void draw(Canvas canvas) {
		
		graphics.draw(canvas);

	}
	public  void update(float deltaTime)
	{
		super.update(deltaTime);
		if(haveColided())
			{
			++score;
			((GameLevel)getOwner()).Score(score);
			game.removeActor(this);
			
			}
		
	}
	

}
