package ch.epfl.cs107.play.game.tutorial;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

/**
 * Simple game, to show basic the basic architecture
 */
public class HelloWorldGame implements Game {

    // Store context
    private Window window;
    
    // We need our physics engine
    private World world;
    
    // And we need to keep references on our game objects
    private Entity body;
    private ImageGraphics graphics, graphics2;


    // This event is raised when game has just started
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        
        // Store context
        this.window = window;
        world = new World();
        world.setGravity(new Vector(0.0f, -9.81f));
        EntityBuilder entityBuilder = world.createEntityBuilder();
        entityBuilder.setFixed(true);
        entityBuilder.setPosition(new Vector(1.f, 1.5f));
        body = entityBuilder.build();
        graphics = new ImageGraphics("stone.broken.4.png", 1, 1);
        graphics.setAlpha(1.0f) ;
        graphics.setDepth(0.5f) ;
        
        graphics2 = new ImageGraphics("bow.png", 1, 1);
        graphics2.setAlpha(1.0f) ;
        graphics2.setDepth(0.5f) ;
        graphics2.setParent(body);
        graphics.setParent(body);
  
        return true;
    }

    // This event is called at each frame
    @Override
    public void update(float deltaTime) {
        
    	world.update(deltaTime);
    	graphics.draw(window);
    	graphics2.draw(window);
    	window.setRelativeTransform(Transform.I.scaled(10.0f));
    }
       

    // This event is raised after game ends, to release additional resources
    @Override
    public void end() {
        // Empty on purpose, no cleanup required yet
    }
    
}
