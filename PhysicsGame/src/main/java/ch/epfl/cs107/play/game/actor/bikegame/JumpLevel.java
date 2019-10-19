package ch.epfl.cs107.play.game.actor.bikegame;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.game.actor.general.Bomb;
import ch.epfl.cs107.play.game.actor.general.Finish;
import ch.epfl.cs107.play.game.actor.general.FrictionlessTerrain;
import ch.epfl.cs107.play.game.actor.general.GravitationlessField;
import ch.epfl.cs107.play.game.actor.general.Kinematic;
import ch.epfl.cs107.play.game.actor.general.Level;
import ch.epfl.cs107.play.game.actor.general.Peaks;
import ch.epfl.cs107.play.game.actor.general.Pendulum;
import ch.epfl.cs107.play.game.actor.general.Pickup;
import ch.epfl.cs107.play.game.actor.general.Rocket;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.game.actor.general.Trampoline;
import ch.epfl.cs107.play.game.actor.general.Worm;
import ch.epfl.cs107.play.window.Canvas;

import ch.epfl.cs107.play.math.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;


public class JumpLevel extends Level {


		private Crate crate,crate1;
		private List<Peaks> peaks, peaks1;
		private List<Pendulum> pendulums;
		private List<Pickup> stars;
		private Bike bike;
		private Rocket rocket;
		private Terrain terrain1,terrain2,terrain3,terrain5;
		private FrictionlessTerrain frictionlessTerrain, frictionlessTerrain2;
		private Trampoline trampoline;
		private Finish finish;
		private Peaks peak;
		private TextGraphics message;
		private float timer;
		private float x=5;
		private float y=1; 
		private Kinematic block,block2;
		private List<Worm> worms;
	
	

		public JumpLevel(ActorGame a) {
			super(a);
			timer = 1f;
			
			message = new TextGraphics("", 0.3f, Color.RED, Color.WHITE, 0.02f, true, false, new Vector(0.5f, 0.5f), 1.0f,
					100.0f);
			message.setParent(game.getCanvas());
			message.setRelativeTransform(Transform.I.translated(0.0f, -1.0f));

		}

		@Override
		public void draw(Canvas canvas) {
			if(canvas==null)throw new NullPointerException("Argument type Canvas null fo draw method in Jumplevel");
			
			// TODO Auto-generated method stub
			message.draw(canvas);

		}

		@Override
		public void createAllActors() {
		
			timer=1f;
			peaks= new ArrayList<Peaks>();
			peaks1= new ArrayList<Peaks>();
			pendulums= new ArrayList<Pendulum>();
			stars= new ArrayList<>();
			worms= new ArrayList<Worm>();
			bike = new Bike(this.game,false,new Vector(0f,5f),new Polygon(0.0f, 0.5f, 0.5f, 1.0f, 0.0f, 2.0f, -0.5f, 1.0f));
			terrain1= new Terrain(this.game,true,new Vector(0,0),new Polyline(-1000f,0f,10f,0,20,2,30,2));
			block = new Kinematic(game, new Vector(2,0),new Vector(2,7),new Vector(0,5),3,0.5f,"wood.3.png");
	        rocket = new Rocket(game,true,new Vector(54f,8f),new Circle(1f));
	       for(int i=0;i<6;i++)
	    	   stars.add(new Pickup(game, true, new Vector(155+i*5,-4), new Circle(1)));
	        for(int i=0;i<3;i++)
				pendulums.add(new Pendulum(game, true,new Vector(180-i*10,1.2f)));
	    	frictionlessTerrain=  new FrictionlessTerrain(game, true, new Vector(0,0), new Polyline(30,2,39,5,41,5.5f,42,5.5f,46,4));
	    	terrain2= new Terrain(this.game,true,new Vector(0,0), new Polyline(46,4f,49,4,52,6,52,4,72,4,72,5,83,5,90,-1,94,-2,96,-3,100,-3.5f,105,-2f));
	    	
	       stars.add(new Pickup(game, true, new Vector(41.5f,6), new Circle(1)));
	    	
	    	 for(int i=0;i<4;i++)
	 			peaks.add(new Peaks(game, true,new Vector(67-i*5,4)));
	 	        
	    	 trampoline=new Trampoline(game, true, new Vector(114f,-6.2f),  new Polygon(
				     new Vector(0.0f, 0.0f),
				     new Vector(3.0f, 0.f),
				     new Vector(3.0f, 2.9f),
				     new Vector(0.0f, 2.9f)));
			   
		  for(int i=0;i<3;i++)
			  worms.add(new Worm(game, new Vector(105+i*5,-7), new Vector(109+i*5,-7), new Vector(4+i%2,0), 2, 0.5f));
		    terrain5=new Terrain(game, true, new Vector(0,0), new Polyline(121,-5.5f,200,-5.5f));
		    frictionlessTerrain2= new FrictionlessTerrain(game, true, new Vector(0,0), new Polyline(200,-5.5f,202,-8.5f));
		    block2= new Kinematic(game, new Vector(202,-8.5f), new Vector(220,-8.5f), new Vector(3,0), 6, 0.2f, "wood.3.png");
		    stars.add(new Pickup(game, true, new Vector(210,-7.5f), new Circle(1)));
		    for(int i=0;i<4;i++)
	 			peaks1.add(new Peaks(game, true,new Vector(202+i*5,-10)));


		    
		    finish = new Finish(game, true, new Vector(220,-6.5f), new Circle(1));
		    
		    game.setViewCandidate(bike);
		    game.setPayLoad(bike);
		
			}

		public void addAllActors() {
			this.game.addActor(rocket);
			game.addActor(block);
			game.addActor(block2);
			this.game.addActor(trampoline);
			this.game.addActor(bike);
			game.addActor(frictionlessTerrain);
			game.addActor(frictionlessTerrain2);
			this.game.addActor(terrain1);
			this.game.addActor(terrain2);
			//this.game.addActor(terrain3);
			game.addActor(terrain5);
		    this.game.addActor(finish);
			this.game.addActor(bike.getWheelL());
			this.game.addActor(bike.getWheelR());
			for(int i=0;i<peaks.size();i++)
			{
				game.addActor(peaks.get(i));
			}
			for(int i=0;i<peaks1.size();i++)
			{
				game.addActor(peaks1.get(i));
			}
			for(int i=0;i<pendulums.size();i++)
			{
				game.addActor(pendulums.get(i));
			}
			for(int i=0;i<worms.size();i++)
				game.addActor(worms.get(i));
			for(int i=0;i<stars.size();i++)
				game.addActor(stars.get(i));
		
		}


		public void update(float deltaTime) {
			
		
		message.setText("Start");
		//the timer goes from 1 to 0 meaning the text fades
		message.setAlpha(timer);
		timer = timer - deltaTime;
			//when the text has faded the this actor is removed
			if (timer <= 0)
				game.removeActor(this);

		}

		

	}


