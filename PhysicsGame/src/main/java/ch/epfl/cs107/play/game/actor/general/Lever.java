package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Lever extends Triggers implements Actor {

	private ImageGraphics graphics;
	private Crate crate;
	private boolean checked=false;
	//Actor with another Actor Crate inside it, thus has two entities the crate's entity and the lever
	//that are connected in such a way that when something collides with the lever, the crate disappears and is removed from the game
	public Lever(ActorGame game, boolean fixed, Vector position, Shape shape) {
		super(game, fixed, position, shape);
		 crate= new Crate(game, true,position.add(new Vector(2,4)), 3, 0.5f,"stone.broken.4.png");
		 checked=false;
		 graphics = new ImageGraphics("lever.blue.left.png",1f,1f, new Vector(0.5f,0.5f),1,1);
		 graphics.setParent(getEntity());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Canvas canvas) {
		if(canvas==null)throw new NullPointerException("Argument type Canvas null fo draw method in Lever");
		
		if(!checked)
		crate.draw(canvas);
		graphics.draw(canvas);

	}
	public  void update(float deltaTime)
	{
		super.update(deltaTime);
		if(haveColided())
			if(graphics.getName().equals("lever.blue.left.png"))
				{
				checked=true;
				graphics.setName("lever.blue.right.png");
				crate.destroy();
				getOwner().removeActor(crate);
				
				}
		
		
	}
	public void destroy()
	{
		super.destroy();
		
		crate.destroy();
		getOwner().removeActor(crate);
	}
}
