package ch.epfl.cs107.play.game.actor.bikegame;


import java.awt.Color;
import java.awt.event.KeyEvent;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.game.actor.general.CheckPoint;
import ch.epfl.cs107.play.game.actor.general.Finish;
import ch.epfl.cs107.play.game.actor.general.Pickup;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.game.actor.general.Wheel;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;




public class BikeGame extends ActorGame{
	Crate crate1;
	Terrain terrain;
	Bike bike;
	CheckPoint checkPoint;
	Pickup pickUp;
	Circle circle;
	TextGraphics message;
	Finish finish;
	float i;
	private boolean collision, levelPassed;
	public void setCollision(boolean collision)
	{
		this.collision=collision;
	}
	public void setLevelPassed(boolean l)
	{
		this.levelPassed=l;
	}
	FileSystem fileSystem;
	public void reset()
	{
circle=new Circle(0.5f);

		i=2;
		checkPoint=new CheckPoint(this, true, new Vector(0.5f,0.5f), circle);
		pickUp= new Pickup(this, true, new Vector(-1f,1f), circle);
		terrain= new Terrain(this,true,new Vector(0f,0f), new Polyline(
				-1000.0f, -1000.0f,
				-1000.0f, 0.0f,
				0.0f, 0.0f,
				3.0f, 1.0f,
				8.0f, 1.0f,
				15.0f, 3.0f,
				16.0f, 3.0f,
				25.0f, 6.0f,
				35.0f, -5.0f,
				50.0f, -5.0f,
				55.0f, -4.0f,
				65.0f, 0.0f,
				6500.0f, -1000.0f
				));
	
		finish = new Finish(this, true, new Vector(8.5f,1.5f), circle);
	 bike= new Bike(this, false, new Vector(4.0f,5.0f),new Polygon(
	    		0.0f, 0.5f,
	    		0.5f, 1.0f,
	    		0.0f, 2.0f,
	    		-0.5f, 1.0f));
	
	 setViewCandidate(bike); 
	 message = new TextGraphics("", 0.3f, Color.RED , Color.WHITE , 0.02f,
			 true , false , new Vector (0.5f, 0.5f), 1.0f, 100.0f) ;
			 message.setParent(getCanvas()) ;
			 message.setRelativeTransform(Transform.I.translated (0.0f, -1.0f)) ;
	
	
	this.addActor(terrain);
	this.addActor(bike);
	this.addActor(bike.getWheelR());
	this.addActor(bike.getWheelL());
	this.addActor(finish);
	this.addActor(checkPoint);
	this.addActor(pickUp); 
	}
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		i=2;
		this.fileSystem=fileSystem;
		super.begin(window, fileSystem);
		reset();
	
	return true;
	}

	public void update(float deltaTime)
	{
		
		super.update(deltaTime);
	
		message.draw(getCanvas());
		if(bike.getHit())
		{
			message.setText("Game over");
		i=i-deltaTime;
		if(i<=0)
		{
			
			bike.destroy();
			removeActor(bike);
			removeActor(bike.getWheelL());
			removeActor(bike.getWheelR());
			message.setText("Press R to restart");
			
		}
		
		if(getKeyboard().get(KeyEvent.VK_R).isPressed())
			{
			bike.setHit(false);
			message.setText("");
			
			this.removeAll();
			removeActor(bike);
			removeActor(terrain);
			this.removeActor(bike.getWheelR());
			this.removeActor(bike.getWheelL());
			this.removeActor(finish);
			this.removeActor(checkPoint);
			this.removeActor(pickUp); 
			removeActor(bike);
			removeActor(bike.getWheelL());
			reset();
			System.out.println("bike is   "+bike.getEntity().isAlive());
			System.out.println("finish is   "+finish.getEntity().isAlive());
			System.out.println("terrain is   "+terrain.getEntity().isAlive());
			}
		}
		
		if(finish.haveColided())
		{
			i=i-deltaTime;
			bike.handUp=true;
			message.setText("You won");
			if(i<=0)
				{message.setText("Press R to restart");
			//bike.destroy();
				}
			if(getKeyboard().get(KeyEvent.VK_R).isPressed())
			{
			message.setText("");
			finish.setHaveCollided(false);
			this.removeAll();
			removeActor(bike);
			removeActor(terrain);
			this.removeActor(bike.getWheelR());
			this.removeActor(bike.getWheelL());
			this.removeActor(finish);
			this.removeActor(checkPoint);
			this.removeActor(pickUp); 
			
			System.out.println("bike is   "+bike.getEntity().isAlive());
		System.out.println("finish is   "+finish.getEntity().isAlive());
			System.out.println("terrain is   "+terrain.getEntity().isAlive());
			reset();
			}
}
	}
}