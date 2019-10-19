package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Rope extends GameEntity implements Actor {

	Entity ball=this.getEntity();
	Entity block;
	private float ballRadius=0.6f, blockHeight= 1.0f, blockWidth= 1.0f;
    private ShapeGraphics ballGraphics;
    private ImageGraphics blockGraphics;
    private PartBuilder ballPartBuilder,blockPartBuilder;
	public Rope(ActorGame fatima, boolean fixed, Vector position) 
	{
		super(fatima, fixed, position);
		Circle circle = new Circle(ballRadius) ;
		ballPartBuilder=ball.createPartBuilder();
		ballPartBuilder.setShape(circle);
		ballPartBuilder.build();
		 ballGraphics = new ShapeGraphics(circle , Color.BLUE, Color.RED,
	    		 .1f, 1.f, 0) ;
		  ballGraphics.setParent(ball);
		  blockPartBuilder=block.createPartBuilder();
		  Polygon polygon = new Polygon(
				    new Vector(0.0f, 0.0f),
				    new Vector(2.0f, 0.0f),
				    new Vector(2.0f, 2.0f),
				    new Vector(0.0f, 2.0f)
				    ) ;
		  blockPartBuilder.setShape(polygon);
		  blockPartBuilder.setFriction(0.5f) ;
		    blockPartBuilder.build();
		    blockGraphics = new ImageGraphics("stone.broken.4.png", 2, 2);
		       blockGraphics.setAlpha(1.0f) ;
		       blockGraphics.setDepth(0.0f) ;
		       blockGraphics.setParent(block);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Transform getTransform() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector getVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}
	

}
