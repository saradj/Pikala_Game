package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;
import java.awt.font.ImageGraphicAttribute;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.RopeConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Pendulum extends GameEntity implements Actor {
 
	private PartBuilder blockPartBuilder,ballPartBuilder;
	private ShapeGraphics graphics;
	private ImageGraphics blockGraphics,ballGraphics;
	private Entity ball;
	private Polyline rope;
	private EntityBuilder entityBuilder;
	//An Actor with two Entities, one inherited from GameEntity-block and one added-ball both connected with a ropa constraint
	public Pendulum(ActorGame fatima, boolean fixed, Vector position) {
		super(fatima, fixed, position);
		//creating the ball
		entityBuilder=getOwner().EntityCreator();
		entityBuilder.setPosition(position.add(new Vector(-5,0)));
		ball=entityBuilder.build();
		//physical part
		blockPartBuilder=getEntity().createPartBuilder();
		blockPartBuilder.setShape(new Polygon(0,0,1,0,1,1,0,1));
		blockPartBuilder.build();
		ballPartBuilder=ball.createPartBuilder();
		ballPartBuilder.setShape(new Circle(0.5f));
		ballPartBuilder.build();
		//visual part
		blockGraphics=new ImageGraphics("stone.broken.1.png", 1, 1,new Vector(0,0),1,1);
		blockGraphics.setParent(getEntity());
		ballGraphics=new ImageGraphics("sun.1.png", 1, 1, new Vector(0.5f,0.5f), 1, 1);
		ballGraphics.setParent(ball);
		//creating the constraint
		RopeConstraintBuilder ropeConstraintBuilder =
	     		   getOwner().ropeCreator() ;
	     		   ropeConstraintBuilder.setFirstEntity(getEntity());
	     		   ropeConstraintBuilder.setFirstAnchor(new Vector(0.5f,
	     				   0.5f));
	     		   ropeConstraintBuilder.setSecondEntity(ball);
	     		   ropeConstraintBuilder.setSecondAnchor(Vector.ZERO) ;
	     		   ropeConstraintBuilder.setMaxLength(4.5f) ;
	     		   ropeConstraintBuilder.setInternalCollision(true) ;
	     		   ropeConstraintBuilder.build();
		
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
		if(canvas==null)throw new NullPointerException("Argument type Canvas null fo draw method in Peaks");
        
		ballGraphics.draw(canvas);
		blockGraphics.draw(canvas);
		//drawing a rope that connects them
		rope = new Polyline(ball.getPosition().add(0, 0.5f),this.getEntity().getPosition().add(new Vector(0.5f,0f)));
		graphics = new ShapeGraphics(rope,Color.PINK,Color.PINK,0.1f,1,1);
		graphics.draw(canvas);
		
	}

	@Override
	public void destroy() {
		//destroying both entities
	    getEntity().destroy();
	    ball.destroy();
	   
	 }

}
