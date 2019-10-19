package ch.epfl.cs107.play.game.actor.bikegame;

import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.game.actor.general.Finish;
import ch.epfl.cs107.play.game.actor.general.GameWithLevels;
import ch.epfl.cs107.play.game.actor.general.Level;
import ch.epfl.cs107.play.game.actor.general.Peaks;
import ch.epfl.cs107.play.game.actor.general.Pickup;
import ch.epfl.cs107.play.game.actor.general.Rocket;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.game.actor.general.Wheel;
import ch.epfl.cs107.play.window.Canvas;

import ch.epfl.cs107.play.math.*;

import java.awt.Color;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.TextGraphics;


public class Level1 extends Level {
	
	Wheel wheelL,wheelR;
    Crate crate;
    Bike bike;
    Terrain terrain;
    Finish finish;
    Pickup pickUp;
    Peaks peaks;
    Rocket rocket;
    private TextGraphics message;
    float i;
    public Level1(ActorGame a){
    	super(a);
    	i=2f;
    	message= new  TextGraphics("", 0.3f, Color.RED , Color.WHITE , 0.02f,
   			 true , false , new Vector (0.5f, 0.5f), 1.0f, 100.0f) ;
   			 message.setParent(game.getCanvas()) ;
   			 message.setRelativeTransform(Transform.I.translated (0.0f, -1.0f)) ;

    }
    
   
    	
    
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		message.draw(canvas);
		
	}
		@Override
	public void createAllActors() {
			 rocket = new Rocket(game, true, new Vector(25f,6.5f), new Circle(2f));
			peaks=new Peaks(game, true, new Vector(0,-1));
			pickUp= new Pickup(this.game, true, new Vector(4.5f,1.5f), new Circle(1f));
		crate=new Crate(this.game, false, new Vector(-15f,5f));
		bike = new Bike(this.game, false, new Vector(-5.0f,5.0f),new Polygon(
	    		0.0f, 0.5f,
	    		0.5f, 1.0f,
	    		0.0f, 2.0f,
	    		-0.5f, 1.0f));
		terrain = new Terrain(this.game,true,new Vector(0f,0f), new Polyline(
				-1000.0f, -1000.0f,
				-1000.0f, 0.0f,
				0,0,
				0.0f, -1.0f,
				15f,-1,
				15f,0,
				//3.0f, 1.0f,
				//8.0f, 1.0f,
				//15.0f, 3.0f,
				16.0f, 1.0f,
				25.0f, 6.0f,
				35.0f, -5.0f,
				50.0f, -5.0f,
				55.0f, -4.0f,
				65.0f, 0.0f,
				200,0,
				6500.0f, -1000.0f
				));
		finish = new Finish(this.game, true, new Vector(200.5f,.5f), new Circle(1f));
		this.game.setViewCandidate(bike);
	}

     public void addAllActors(){
    	 game.addActor(rocket);
    	 this.game.addActor(crate);
    	game.addActor(peaks);
    	 game.addActor(pickUp);
    	 this.game.addActor(bike);
    	 this.game.addActor(terrain);
    	 this.game.addActor(finish);
    	 this.game.addActor(bike.getWheelL());
    	 this.game.addActor(bike.getWheelR());
    	 //System.out.println("lvl 1 adds");
     }
     public void removeAllActors()
     {
    	 game.removeActor(rocket);
    	 this.game.removeActor(crate);
    	 game.removeActor(peaks);
    	 game.removeActor(pickUp);
    	 this.game.removeActor(bike);
    	 this.game.removeActor(terrain);
    	 this.game.removeActor(finish);
    	 this.game.removeActor(bike.getWheelL());
    	 this.game.removeActor(bike.getWheelR());
    	 //System.out.println("lvl 1 removes");
     }

     public  void update(float deltaTime) {
    	 //System.out.println("update of lvl 1");
 		message.setText("Start");
 		message.setAlpha(message.getAlpha()-(i/1000));
 		//System.out.println("alpha"+message.getAlpha());
 		i=i-deltaTime;
 		//System.out.println("i of level1="+i+"and deltaTime"+ deltaTime);
 		if(i<=0)
 			game.removeActor(this);
 		
 	}

public Bike getBike(){
	return bike;
}


	




	



	

	
}
