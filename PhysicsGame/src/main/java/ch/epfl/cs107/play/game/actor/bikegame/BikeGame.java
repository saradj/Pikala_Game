package ch.epfl.cs107.play.game.actor.bikegame;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.game.actor.general.CheckPoint;
import ch.epfl.cs107.play.game.actor.general.Finish;
import ch.epfl.cs107.play.game.actor.general.GameWithLevels;
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




public class BikeGame extends ActorGame {
	private List<Crate> crates;
	private Terrain terrain;
	private Bike bike;
	private Circle circle;
	private TextGraphics message, restartMessage;
	private Finish finish;

	
	
	
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		
		super.begin(window, fileSystem);
		resetlevel();
	
		return true;
	}

	
	public void update(float deltaTime)
	{
		
		super.update(deltaTime);
	
		message.draw(getCanvas());
		restartMessage.draw(getCanvas());
		if(bike.hasFallen()) 
		{
			message.setText("Game over");
			restartMessage.setText("Press R to restart");
			
		
			//if the keyboard button R is pressed the game is restarted after the death of the player
			if(getKeyboard().get(KeyEvent.VK_R).isPressed())
			{
			message.setText("");
			this.removeAll();
		
			resetlevel();}		}
		
		
}
	//the Actor Finish calls nextLevel() in the game to notify it that there is victory
	@Override
	public void nextLevel() {
		
		message.setText("You won");
		restartMessage.setText("Press R to restart");
		//if the keyboard button R is pressed the game is restarted after the win
		if(getKeyboard().get(KeyEvent.VK_R).isPressed())
		{
		message.setText("");
		this.removeAll();
	
		resetlevel();}
	
		
	}
	@Override
	public void resetlevel() {
		//Initialising the actors
		circle=new Circle(1f);
		crates=new ArrayList<>();
		crates.add(new Crate(this, false, new Vector(0.0f, 5.0f), 1, 1, "stone.broken.4.png"));
		crates.add(new Crate(this, false, new Vector(0.2f, 7.0f), 1, 1, "stone.broken.4.png"));
		crates.add(new Crate(this, false, new Vector(2.0f, 6.0f), 1, 1,"stone.broken.4.png"));
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
		
	
		finish = new Finish(this, true, new Vector(40.5f,-4.5f), circle);
		bike= new Bike(this, false, new Vector(4.0f,5.0f),new Polygon(
	    		0.0f, 0.5f,
	    		0.5f, 1.0f,
	    		0.0f, 2.0f,
	    		-0.5f, 1.0f));
	
		
		message = new TextGraphics("", 0.3f, Color.RED , Color.WHITE , 0.02f,
			 true , false , new Vector (0.5f, 0.5f), 1.0f, 100.0f) ;
		message.setParent(getCanvas()) ;
		message.setRelativeTransform(Transform.I.translated (0.0f, -1.0f)) ;
		restartMessage = new TextGraphics("", 0.2f, Color.black , Color.WHITE , 0.02f,
			 true , false , new Vector (0.5f, 2f), 1.0f, 100.0f) ;
		restartMessage.setParent(getCanvas()) ;
		restartMessage.setRelativeTransform(Transform.I.translated (0.0f, -1.0f)) ;
	
	//adding the actors
	this.addActor(terrain);
	this.addActor(bike);
	this.addActor(bike.getWheelR());
	this.addActor(bike.getWheelL());
	this.addActor(finish);
	for(int i=0;i<crates.size();i++)
		this.addActor(crates.get(i));
	setPayLoad(bike);
	setViewCandidate(bike); 
	}// TODO Auto-generated method stub
		
	}
