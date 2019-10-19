package ch.epfl.cs107.play.game.tutorial;

import java.awt.event.KeyEvent;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.RevoluteConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;


public class ScaleGame implements Game {
	private Window window;
	private World world;
	private Entity ball,plank,block;
	ImageGraphics blockGraphics,plankGraphics,ballGraphics;
float plankH=.2f,plankW=5f, blockH=1f, blockW=10f;
	

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		this.window=window;
		world = new World();
		world.setGravity(new Vector(0.0f, -9.81f));
		//creation du block
		Polygon polygon = new Polygon( new Vector(0.0f, 0.0f), new Vector(10f, 0.0f), new Vector(10.0f, 1.0f),
    	    	new Vector(0.0f, 1.0f) );
		EntityBuilder entityBlock=  world.createEntityBuilder();
		entityBlock.setFixed(true);
		entityBlock.setPosition(new Vector(-5f,-1f));
		block=entityBlock.build();
		PartBuilder partBlock = block.createPartBuilder();
		partBlock.setShape(polygon);
        partBlock.build();		
		blockGraphics= new ImageGraphics("stone.broken.4.png", 10, 1);
		blockGraphics.setParent(block);
		
		//creation du ball
		Circle circle = new Circle(0.5f);
		EntityBuilder entityBall = world.createEntityBuilder();
		entityBall.setFixed(false);
		entityBall.setPosition(new Vector(0.5f,4f));
		ball=entityBall.build();
		PartBuilder partBall=ball.createPartBuilder();
		partBall.setShape(circle);
		partBall.build();
		ballGraphics= new ImageGraphics("explosive.11.png",1f,1f,new Vector(0.5f,0.5f));
		ballGraphics.setParent(ball);
		
		
		//Creation de la plank
		Polygon polygonPlank = new Polygon( new Vector(0.0f, 0.0f), new Vector(5.0f, 0.0f), new Vector(5.0f, 0.2f),
    	    	new Vector(0.0f, 0.2f) );
		EntityBuilder entityPlank = world.createEntityBuilder();
		entityPlank.setFixed(false);
		entityPlank.setPosition(new Vector(-2.5f,0.8f));
		plank=entityPlank.build();
		PartBuilder partPlank=plank.createPartBuilder();
		partPlank.setShape(polygonPlank);
		partPlank.build();
		plankGraphics = new ImageGraphics("wood.3.png",5f,0.2f);
		plankGraphics.setParent(plank);
		//rotation
        
        
		RevoluteConstraintBuilder revoluteConstraintBuilder =
				world.createRevoluteConstraintBuilder() ;
				
				revoluteConstraintBuilder.setFirstEntity(block) ;
				revoluteConstraintBuilder.setFirstAnchor(new Vector(10/2,
				(1*7)/4)) ;
				revoluteConstraintBuilder.setSecondEntity(plank) ;
				revoluteConstraintBuilder.setSecondAnchor(new Vector((float)(5/2),
				(float) (.2f/2))) ;
				revoluteConstraintBuilder.setInternalCollision(true) ;
				revoluteConstraintBuilder.build() ;
		
		
		return true;
	}

	@Override
	public void update(float deltaTime) {
		if (window.getKeyboard().get(KeyEvent.VK_LEFT).isDown()) { 
			ball.applyAngularForce(10.0f);
    	
    	} else
    		if (window.getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) { 
    			ball.applyAngularForce(-10.0f); }

		world.update(deltaTime);
    	window.setRelativeTransform(Transform.I.scaled(10.0f));
    	ballGraphics.draw(window);
    	plankGraphics.draw(window);
    	blockGraphics.draw(window);
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}

	



}
