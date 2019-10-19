package ch.epfl.cs107.play.game.tutorial;

import java.awt.Color;
import java.awt.event.KeyEvent;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.RevoluteConstraintBuilder;
import ch.epfl.cs107.play.math.RopeConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

public class ScaleGame implements Game {
	 private Window window;
	    private World world;
	    
	    
	    private Entity  ball,block, plank;
	    private float ballRadius=0.5f, blockHeight= 1.0f, blockWidth= 10.0f, plankWidth=5.0f, plankHeight=.2f;
	    
	    private ImageGraphics graphics,graphics2, ballGraphics;
	    @Override
	    public boolean begin(Window window, FileSystem fileSystem) {
	        
	        // Store context
	        this.window = window;
	        world = new World();
	        world.setGravity(new Vector(0.0f, -9.81f));
	 
	        Circle circle = new Circle(ballRadius) ;
	  
	        EntityBuilder entityBuilder2 = world.createEntityBuilder();
	        entityBuilder2.setFixed(false);
	        entityBuilder2.setPosition(new Vector(0.5f, 4.f));
	        ball = entityBuilder2.build();
	        PartBuilder partBuilder2 = ball.createPartBuilder() ;
	    
	     partBuilder2.setShape(circle) ;
	     // Finally , do not forget the following line.
	     partBuilder2.build() ;
	     //ballGraphics = new ImageGraphics("explosive.11.png", 1, 1);
	    ballGraphics = new ImageGraphics("explosive.11.png", 2.0f*ballRadius, 2.0f * ballRadius, new Vector(ballRadius, ballRadius));
	       ballGraphics.setParent(ball);
	       
	       
	       
	       
	       
	       EntityBuilder entityBuilder = world.createEntityBuilder();
	       entityBuilder.setFixed(true);
	       entityBuilder.setPosition(new Vector(-5.0f, -1.0f));
	       Entity block = entityBuilder.build();
	       PartBuilder partBuilder = block.createPartBuilder() ;
	    // Create a square polygon , and set the shape of the builder to
	   // this polygon
	    Polygon polygon = new Polygon(
	    new Vector(0.0f, 0.0f),
	    new Vector(10.0f, 0.0f),
	    new Vector(10.0f, 1.0f),
	    new Vector(0.0f, 1.0f)
	    ) ;
	    partBuilder.setShape(polygon) ;
	    // Finally , do not forget the following line.
	    partBuilder.setFriction(0.5f) ;
	    partBuilder.build() ;

	       graphics = new ImageGraphics("stone.broken.4.png", 10, 1);
	
	       graphics.setParent(block);
	       
	       
	       Polygon polygon2 = new Polygon(
	    		    new Vector(0.0f, 0.0f),
	    		    new Vector(5.0f, 0.0f),
	    		    new Vector(5.0f, .2f),
	    		    new Vector(0.0f, .2f)
	    		    ) ;
	       		EntityBuilder entityBuilder3 = world.createEntityBuilder();
	       entityBuilder3.setFixed(false);
	       entityBuilder3.setPosition(new Vector(-2.5f, 0.8f));
	       Entity plank = entityBuilder3.build();
	       PartBuilder partBuilder3 = plank.createPartBuilder() ;
	       partBuilder3.setShape(polygon2) ;
		    // Finally , do not forget the following line.
		  //  partBuilder3.setFriction(0.5f) ;
		    partBuilder3.build() ;
		    
		    graphics2 = new ImageGraphics("wood.3.png",5f, .2f);
	
		       graphics2.setParent(plank);
	       // TO BE COMPLETED
	        // Successfully initiated
		       RevoluteConstraintBuilder revoluteConstraintBuilder =
		    		   world.createRevoluteConstraintBuilder() ;
		    		   revoluteConstraintBuilder.setFirstEntity(block) ;
		    		   revoluteConstraintBuilder.setFirstAnchor(new Vector(blockWidth/2,
		    		   (blockHeight*7)/4)) ;
		    		   revoluteConstraintBuilder.setSecondEntity(plank) ;
		    		   revoluteConstraintBuilder.setSecondAnchor(new Vector(plankWidth/2,
		    		   plankHeight/2)) ;
		    		   revoluteConstraintBuilder.setInternalCollision(true) ;
		    		   revoluteConstraintBuilder.build() ;


	       return true;
	    }

	    // This event is called at each frame
	    @Override
	    public void update(float deltaTime) {
	    	if (window.getKeyboard().get(KeyEvent.VK_LEFT).isDown()) { ball.applyAngularForce(10.0f);
	    	
	    	} else if (window.getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) { ball.applyAngularForce(-10.0f); }

	    	world.update(deltaTime);
	    	ballGraphics.draw(window);
	    	graphics.draw(window);
	    	graphics2.draw(window);
	    	
	    	window.setRelativeTransform(Transform.I.scaled(10.0f));

	        // The actual rendering will be done now, by the program loop
	    }

	    // This event is raised after game ends, to release additional resources
	    @Override
	    public void end() {
	        // Empty on purpose, no cleanup required yet
	    }
	    

}
