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

	private PartBuilder partBuilder;
	private ImageGraphics graphics;
	public Crate(ActorGame game, boolean fixed, Vector position,float width,float height,String imageName) {
		super(game, fixed, position);
		if(width<=0)throw new IllegalArgumentException("Width smaller or equal to zero for a Crate");
		if(height<=0)throw new IllegalArgumentException("Height smaller or equal to zero for a Crate");
		if(imageName==null)throw new NullPointerException("Image name is null fo constructing a Crate");
		//creating the physical part of the crate
	partBuilder=getEntity().createPartBuilder();
	partBuilder.setShape( new Polygon(
		     new Vector(0.0f, 0.0f),
		     new Vector(width, 0.0f),
		     new Vector(width, height),
		     new Vector(0.0f, height)
		     ));
	partBuilder.build();
	//creating the visual representation of the crate
	graphics= new ImageGraphics(imageName, width, height,Vector.ZERO,1,1); 	
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
		if(canvas==null)throw new NullPointerException("Argument type Canvas null fo draw method in Crate");
		graphics.draw(canvas);

		
	}

	@Override
	public void destroy() {
		
	    getEntity().destroy();
	   
	 }
	



}
