package ch.epfl.cs107.play.game.actor.bikegame;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.game.actor.general.Finish;
import ch.epfl.cs107.play.game.actor.general.Level;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Level2 extends Level  {
	Crate crate2;
	Terrain terrain2;
	Bike bike2;
	Finish finish2;
	TextGraphics message;
	float i;
	public Level2(ActorGame a){
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
	public  void update(float deltaTime){
		 System.out.println("update of lvl 2");
		message.setText("Next Level");
		message.setAlpha(message.getAlpha()-(i/1000));
 		i=i-deltaTime;
 		if(i<=0)
 			game.removeActor(this);
 		/*if(this.finish2.haveColided())
 		
 		{
 			message.setText(" next Level");
 		}
 			*/
 		
		
	}

	@Override
	public void createAllActors() {
		// TODO Auto-generated method stub

		crate2=new Crate(this.game, false, new Vector(10f,5f));
		bike2 = new Bike(this.game, false, new Vector(4.0f,5.0f),new Polygon(
	    		0.0f, 0.5f,
	    		0.5f, 1.0f,
	    		0.0f, 2.0f,
	    		-0.5f, 1.0f));
		terrain2 = new Terrain(this.game,true,new Vector(0f,0f), new Polyline(
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
		finish2 = new Finish(this.game, true, new Vector(-10.5f,1f),new Circle(0.5f));
		this.game.setViewCandidate(bike2);
	}
	public void addAllActors(){
		this.game.addActor(bike2);
		this.game.addActor(bike2.getWheelL());
		this.game.addActor(bike2.getWheelR());
		this.game.addActor(terrain2);
		this.game.addActor(finish2);
		this.game.addActor(crate2);
		System.out.println("lvl 2 adds");
	}
	 public void removeAllActors()
     {
    	 this.game.removeActor(crate2);
    	 this.game.removeActor(bike2);
    	 this.game.removeActor(terrain2);
    	 this.game.removeActor(finish2);
    	 this.game.removeActor(bike2.getWheelL());
    	 this.game.removeActor(bike2.getWheelR());
    	 System.out.println("lvl 2 removes");
     }
public Bike getBike()
{
	return bike2;
}
	

}
