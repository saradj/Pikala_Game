package ch.epfl.cs107.play.game.tutorial;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.RopeConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

public class RopeGame implements Game{

	 private Window window;
    private World world;
    
    // And we need to keep references on our game objects
    private Entity  ball,block;
    private float ballRadius=0.6f, blockHeight= 1.0f, blockWidth= 1.0f;
    private ShapeGraphics ballGraphics;
    private ImageGraphics graphics;

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        
        // Store context
        this.window = window;
        world = new World();
        world.setGravity(new Vector(0.0f, -9.81f));
     
     
        Circle circle = new Circle(ballRadius) ;
  
        EntityBuilder ballEntityBuilder = world.createEntityBuilder();
        ballEntityBuilder.setFixed(false);
        ballEntityBuilder.setPosition(new Vector(0.6f, 4.0f));
        ball = ballEntityBuilder.build();
        PartBuilder ballPartBuilder = ball.createPartBuilder() ;
    

     ballPartBuilder.setShape(circle) ;
     // Finally , do not forget the following line.
     ballPartBuilder.build() ;
     ballGraphics = new ShapeGraphics(circle , Color.BLUE, Color.RED,
    		 .1f, 1.f, 0) ;

       ballGraphics.setParent(ball);
       
       EntityBuilder blockEntityBuilder = world.createEntityBuilder();
       blockEntityBuilder.setFixed(true);
       blockEntityBuilder.setPosition(new Vector(1f, 1f));
       Entity block = blockEntityBuilder.build();
       PartBuilder blockPartBuilder = block.createPartBuilder() ;
    
    Polygon polygon = new Polygon(
    new Vector(0.0f, 0.0f),
    new Vector(2.0f, 0.0f),
    new Vector(2.0f, 2.0f),
    new Vector(0.0f, 2.0f)
    ) ;
    blockPartBuilder.setShape(polygon) ;
    // Finally , do not forget the following line.
    blockPartBuilder.setFriction(0.5f) ;
    blockPartBuilder.build() ;

       graphics = new ImageGraphics("stone.broken.4.png", 2, 2);
       graphics.setAlpha(1.0f) ;
       graphics.setDepth(0.0f) ;
       graphics.setParent(block);
       // TO BE COMPLETED
        // Successfully initiated
       RopeConstraintBuilder ropeConstraintBuilder =
    		   world.createRopeConstraintBuilder() ;
    		   ropeConstraintBuilder.setFirstEntity(block) ;
    		   ropeConstraintBuilder.setFirstAnchor(new Vector(blockWidth/2,
    		   blockHeight/2)) ;
    		   ropeConstraintBuilder.setSecondEntity(ball) ;
    		   ropeConstraintBuilder.setSecondAnchor(Vector.ZERO) ;
    		   ropeConstraintBuilder.setMaxLength(3.0f) ;
    		   ropeConstraintBuilder.setInternalCollision(true) ;
    		   ropeConstraintBuilder.build() ;

       return true;
    }

    // This event is called at each frame
    @Override
    public void update(float deltaTime) {
        
    	world.update(deltaTime);
    	ballGraphics.draw(window);
    	graphics.draw(window);
    	window.setRelativeTransform(Transform.I.scaled(10.0f));

        // The actual rendering will be done now, by the program loop
    }

    // This event is raised after game ends, to release additional resources
    @Override
    public void end() {
        // Empty on purpose, no cleanup required yet
    }
    
}
