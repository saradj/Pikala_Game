package ch.epfl.cs107.play.game.actor.crate;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Image;

public  class Crate extends GameEntity implements Actor {
PartBuilder partBuilder;
	ImageGraphics graphics;
	public Crate(ActorGame game, boolean fixed, Vector position) {
		super(game, fixed, position);
		
	partBuilder=getEntity().createPartBuilder();
	partBuilder.setShape( new Polygon(
		     new Vector(0.0f, 0.0f),
		     new Vector(1.0f, 0.0f),
		     new Vector(1.0f, 1.0f),
		     new Vector(0.0f, 1.0f)
		     ));
	partBuilder.build();
		 graphics= new ImageGraphics("stone.broken.4.png", 1, 1);
		 graphics.setParent(getEntity());
		 
		
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

		graphics.draw(canvas);
	
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		//getOwner().removeActor(this);
	    getEntity().destroy();
	 }
	

	
		// TODO Auto-generated constructor stub
	

}
