package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;

import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.ContactListener;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Finish extends Triggers{


	private ImageGraphics graphics;
	

	public Finish(ActorGame game, boolean fixed, Vector position, Shape shape)
	
	{
		super(game, fixed, position,shape);
		
		  graphics = new ImageGraphics("flag.red.png",1f,1f, new Vector(0.5f,0.5f));
		  graphics.setParent(getEntity());
		  //setting a unique signature
		  getPartBuilder().setCollisionSignature(0x5);
		  getPartBuilder().build();
		  
	
	}
	@Override
	public void draw(Canvas canvas) {
		if(canvas==null)throw new NullPointerException("Argument type Canvas null fo draw method in Finish");
		
		graphics.draw(canvas);

	}
	public void update(float deltaTime)
	{
		super.update(deltaTime);
		if(haveColided())
		//notifying the game that the level is passed, because the player has reached the finish line of the level
			//using the inherited method haveColideded() from Triggers
		getOwner().nextLevel();
	}
}

