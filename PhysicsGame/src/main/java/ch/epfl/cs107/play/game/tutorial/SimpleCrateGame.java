package ch.epfl.cs107.play.game.tutorial;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

public class SimpleCrateGame implements Game {
	 private Window window;
	    


	    private World world;
	    
	    // And we need to keep references on our game objects
	    private Entity block, crate;
	    private ImageGraphics graphics, graphics2;

	    @Override
	    public boolean begin(Window window, FileSystem fileSystem) {
	        
	        // Store context
	        this.window = window;
	        world = new World();
	        world.setGravity(new Vector(0.0f, -9.81f));
	        EntityBuilder entityBuilder = world.createEntityBuilder();
	        entityBuilder.setFixed(true);
	        entityBuilder.setPosition(new Vector(1.0f, 0.5f));
	        block = entityBuilder.build();
	        PartBuilder partBuilder = block.createPartBuilder() ;
	     // Create a square polygon , and set the shape of the builder to
	    // this polygon
	     Polygon polygon = new Polygon(
	     new Vector(0.0f, 0.0f),
	     new Vector(1.0f, 0.0f),
	     new Vector(1.0f, 1.0f),
	     new Vector(0.0f, 1.0f)
	     ) ;
	     partBuilder.setShape(polygon) ;
	     // Finally , do not forget the following line.
	     partBuilder.setFriction(0.5f) ;
	     partBuilder.build() ;

	        graphics = new ImageGraphics("stone.broken.4.png", 1, 1);
	        graphics.setAlpha(1.0f) ;
	        graphics.setDepth(0.0f) ;
	        graphics.setParent(block);
	        EntityBuilder entityBuilder2 = world.createEntityBuilder();
	        entityBuilder2.setFixed(false);
	        entityBuilder2.setPosition(new Vector(0.2f, 4.0f));
	        crate = entityBuilder2.build();
	        PartBuilder partBuilder2 = crate.createPartBuilder() ;
	     // Create a square polygon , and set the shape of the builder to
	     //this polygon
	
	     partBuilder2.setShape(polygon) ;
	     // Finally , do not forget the following line.
	     partBuilder2.build() ;

	        graphics2 = new ImageGraphics("box.4.png", 1, 1);
	        graphics2.setAlpha(1.0f) ;
	        graphics2.setDepth(0.0f) ;
	        graphics2.setParent(crate);
	       // TO BE COMPLETED
	        // Successfully initiated
	        return true;
	    }

	    // This event is called at each frame
	    @Override
	    public void update(float deltaTime) {
	        
	    	world.update(deltaTime);
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
