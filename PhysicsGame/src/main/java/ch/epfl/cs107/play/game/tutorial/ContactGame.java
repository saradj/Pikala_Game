package ch.epfl.cs107.play.game.tutorial;

import java.awt.Color;
import java.awt.event.KeyEvent;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

public class ContactGame implements Game {

	private Window window;
    private World world;
    private BasicContactListener contactListener;

    
   
    private Entity  ball, plank;
    private float ballRadius=0.5f, blockHeight= 1.0f, blockWidth= 10.0f, plankWidth=5.0f, plankHeight=.2f;
    
    private ImageGraphics graphics;
    private ShapeGraphics ballGraphics;
    private Circle circle;
    
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
    	contactListener = new BasicContactListener(); 
    

       
        this.window = window;
        world = new World();
        world.setGravity(new Vector(0.0f, -9.81f));
 
        circle = new Circle(ballRadius) ;
  
        EntityBuilder ballEntityBuilder = world.createEntityBuilder();
        ballEntityBuilder.setFixed(false);
        ballEntityBuilder.setPosition(new Vector(0.0f,2f));
        ball = ballEntityBuilder.build();
        
     PartBuilder ballPartBuilder = ball.createPartBuilder() ;
     ballPartBuilder.setShape(circle) ;
     ballPartBuilder.build() ;
    
    ballGraphics =  new ShapeGraphics(circle,Color.BLUE, Color.BLUE, .1f, 1, 0);

    ballGraphics.setParent(ball);
   	ball.addContactListener(contactListener);
       
       //creating the block entity
       EntityBuilder entityBuilder = world.createEntityBuilder();
       entityBuilder.setFixed(true);
       entityBuilder.setPosition(new Vector(-5.0f, -1.0f));
       Entity block = entityBuilder.build();
       
  //creating the block part
       PartBuilder partBuilder = block.createPartBuilder() ;
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
//visual representation of the block
       graphics = new ImageGraphics("stone.broken.4.png", 10, 1);

       graphics.setParent(block);
       
       
       return true;
    }

    @Override
    public void update(float deltaTime) {
    
    	
    	int numberOfCollisions = contactListener.getEntities().size();
    	System.out.println("nb coll"+ numberOfCollisions);
    	if (numberOfCollisions > 0)
    	{ 
    		ballGraphics.setFillColor(Color.RED); 
    		}

    	world.update(deltaTime);
    	ballGraphics.draw(window);
    	graphics.draw(window);
 
    	
    	window.setRelativeTransform(Transform.I.scaled(10.0f));

       
    }

    // This event is raised after game ends, to release additional resources
    @Override
    public void end() {
        // Empty on purpose, no cleanup required yet
    }
    

}
