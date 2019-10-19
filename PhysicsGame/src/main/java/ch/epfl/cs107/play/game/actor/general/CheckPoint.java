package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class CheckPoint extends Triggers {

	private ImageGraphics graphics;
	static int checked=0;
	public CheckPoint(ActorGame game, boolean fixed, Vector position, Shape shape) {
		super(game, fixed, position,shape);
		
		 graphics = new ImageGraphics("torch.png",1f,1f, new Vector(0.5f,0.5f));
		  graphics.setParent(getEntity());
		  
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
			if(graphics.getName().equals("torch.png"))
				{
				checked++;
					
				graphics.setName("torch.lit.2.png");
				
				}
		System.out.println("checked "+checked);
		
	}
}
