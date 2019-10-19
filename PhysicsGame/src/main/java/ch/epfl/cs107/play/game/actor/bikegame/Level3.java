package ch.epfl.cs107.play.game.actor.bikegame;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.game.actor.general.CheckPoint;
import ch.epfl.cs107.play.game.actor.general.Finish;
import ch.epfl.cs107.play.game.actor.general.FrictionlessTerrain;
import ch.epfl.cs107.play.game.actor.general.Level;
import ch.epfl.cs107.play.game.actor.general.Pickup;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.game.actor.general.Wheel;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Level3 extends Level {

	Wheel wheelL,wheelR;
    Crate crate;
    Bike bike;
    Terrain terrain, terrainContinue;
    Finish finish;
    CheckPoint checkPoint1,checkPoint2;
    Pickup pickUp1,pickUp2;
    FrictionlessTerrain frictionlessTerain;
    private TextGraphics message;
    float i;
    Circle circle;
	public Level3(ActorGame fatima) {
		super(fatima);
	    	i=2f;
	    	circle=new Circle(1f);
	    	message= new  TextGraphics("", 0.3f, Color.RED , Color.WHITE , 0.02f,
	   			 true , false , new Vector (0.5f, 0.5f), 1.0f, 100.0f) ;
	   			 message.setParent(game.getCanvas()) ;
	   			 message.setRelativeTransform(Transform.I.translated (0.0f, -1.0f)) ;
	
	}

	@Override
	public void draw(Canvas canvas) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createAllActors() {
		// TODO Auto-generated method stub
		checkPoint1=new CheckPoint(this.game, true, new Vector(30.5f,1.5f), circle);
		checkPoint2=new CheckPoint(this.game, true, new Vector(40.5f,-4.5f), circle);
		pickUp1= new Pickup(this.game, true, new Vector(20.5f,4.5f), circle);
		pickUp2= new Pickup(this.game, true, new Vector(65.5f,0.5f), circle);
		crate=new Crate(this.game, false, new Vector(0f,5f));
		bike = new Bike(this.game, false, new Vector(4.0f,5.0f),new Polygon(
	    		0.0f, 0.5f,
	    		0.5f, 1.0f,
	    		0.0f, 2.0f,
	    		-0.5f, 1.0f));
		terrain = new Terrain(this.game,true,new Vector(0f,0f), new Polyline(
				-1000.0f, -1000.0f,
				-1000.0f, 0.0f,
			0,0,
			1,0.7f,
			3,0,
			8,0,
			15,3));
		terrainContinue = new Terrain(this.game,true,new Vector(0f,0f), new Polyline(
			
				
				25.0f, 3.0f,
				35.0f, -5.0f,
				50.0f, -5.0f,
				55.0f, -4.0f,
				65.0f, 0.0f,
				74,0,
				6500.0f, -1000.0f
			));		
		frictionlessTerain= new FrictionlessTerrain(this.game, true, new Vector(0f,0f), new Polyline(false,
				15,3,
				16,3,20,4,25,3));
		
		finish = new Finish(this.game, true, new Vector(70.5f,0.5f), circle);
		this.game.setViewCandidate(bike);
	}

	@Override
	public void addAllActors() {
		game.addActor(terrainContinue);
		this.game.addActor(frictionlessTerain);
		this.game.addActor(crate);
		game.addActor(pickUp1);
		game.addActor(pickUp2);
		game.addActor(checkPoint1);
		game.addActor(checkPoint2);
		this.game.addActor(bike);
   	 this.game.addActor(terrain);
   	 this.game.addActor(finish);
   	 this.game.addActor(bike.getWheelL());
   	 this.game.addActor(bike.getWheelR());
   	 System.out.println("lvl 3 adds");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAllActors() {
		game.removeActor(terrainContinue);
		game.removeActor(checkPoint1);
		game.removeActor(checkPoint2);
		 this.game.removeActor(crate);
		 this.game.removeActor(frictionlessTerain);
    	 this.game.removeActor(bike);
    	 this.game.removeActor(terrain);
    	 this.game.removeActor(finish);
    	 this.game.removeActor(bike.getWheelL());
    	 game.removeActor(pickUp1);
    	 game.removeActor(pickUp2);
 		
    	 this.game.removeActor(bike.getWheelR());
    	 System.out.println("lvl 3 removes");
		
		
	}
	 public  void update(float deltaTime) 
	 {
    	
 		message.setText("NextLevel");
 		message.setAlpha(message.getAlpha()-(i/1000));
 	
 		i=i-deltaTime;
 	//	System.out.println("i of level1="+i+"and deltaTime"+ deltaTime);
 		if(i<=0)
 			game.removeActor(this);
 	}
	@Override
	public Bike getBike() {
		// TODO Auto-generated method stub
		return bike;
	}

}
