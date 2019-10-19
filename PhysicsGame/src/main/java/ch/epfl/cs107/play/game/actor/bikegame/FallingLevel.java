package ch.epfl.cs107.play.game.actor.bikegame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.game.actor.general.Bomb;
import ch.epfl.cs107.play.game.actor.general.CheckPoint;
import ch.epfl.cs107.play.game.actor.general.Finish;
import ch.epfl.cs107.play.game.actor.general.GravitationlessField;
import ch.epfl.cs107.play.game.actor.general.Kinematic;
import ch.epfl.cs107.play.game.actor.general.Level;
import ch.epfl.cs107.play.game.actor.general.Lever;
import ch.epfl.cs107.play.game.actor.general.Peaks;
import ch.epfl.cs107.play.game.actor.general.Pendulum;
import ch.epfl.cs107.play.game.actor.general.Pickup;
import ch.epfl.cs107.play.game.actor.general.Terrain;
import ch.epfl.cs107.play.game.actor.general.Trampoline;
import ch.epfl.cs107.play.game.actor.general.Wood;
import ch.epfl.cs107.play.game.actor.general.Worm;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;


public class FallingLevel extends Level{

	private TextGraphics message;
	private Kinematic  horisontalBlock1,verticalBlock;
	private Crate crate,crate1;
	private Wood wood;
	private Bike bike;
	private Terrain terrain1;
	private GravitationlessField gravitationalField;
	private Pendulum pendulum;
	private Lever lever;
	private List<Kinematic> blocks1;
	private List<Worm> worms;
	private Trampoline trampoline;
	private Finish finish;
	private Bomb bomb;
	private List<Pickup> stars;
	private float timer;
	private List<Peaks> peaks;
	
	public FallingLevel(ActorGame fatima) {
		super(fatima);
		timer=1f;
		message = new TextGraphics("", 0.3f, Color.RED, Color.WHITE, 0.02f, true, false, new Vector(0.5f, 0.5f), 1.0f,
				100.0f);
		message.setParent(game.getCanvas());
		message.setRelativeTransform(Transform.I.translated(0.0f, -1.0f));
	
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void createAllActors() {
		timer=1f;
		// TODO Auto-generated method stub
		blocks1= new ArrayList<>();
		worms= new ArrayList<>();
		peaks= new ArrayList<>();
		stars=new ArrayList<>();
		for(int i=0;i<10;i++)
			stars.add(new Pickup(game,true, new Vector(-100+i*5,1), new Circle(1)));
		for(int i=0;i<4;i++)
		{
			blocks1.add(new Kinematic(game, new Vector(-100+i*15,0), new Vector(-100+i*15,6F), new Vector(0,4), 5, 0.5f, "metal.2.png"));
		}
		for(int i=0;i<9;i++)
			peaks.add(new Peaks(game, true, new Vector(37+i*5,-20)));
		
		bomb= new Bomb(game, false, new Vector(165.5f,-1.5f));
		pendulum= new Pendulum(game, true, new Vector(-10,5));
		bike = new Bike(this.game,false,new Vector(-110f,0f),new Polygon(0.0f, 0.5f, 0.5f, 1.0f, 0.0f, 2.0f, -0.5f, 1.0f));
		terrain1= new Terrain(this.game,true,new Vector(0,0),new Polyline(-1000f,0f,10f,0,20,2,37,2,37,-20,80,-20,80,-2f,
				83,-2f,89,-7,93,-7,97,-5.3f,97,-7,165.5f,-7,170.5f,-12,178,-12,178,-7,170.5f,-7,170,100));
		horisontalBlock1= new Kinematic(game, new Vector(36,1),new Vector(70,1),new Vector(4,0),10,0.5f,"metal.2.png");
		stars.add(new Pickup(game, true, new Vector(49, 2), new Circle(1)));
		verticalBlock= new Kinematic(game, new Vector(142,-7), new Vector(142,-1f),new Vector(0,3),6,0.5f,"metal.2.png");
		gravitationalField=new GravitationlessField(game, true, new Vector(96,-7));
		stars.add(new Pickup(game, true, new Vector(97, -4.5f), new Circle(1)));
		crate=new Crate(game, true, new Vector(111,-4), 13, 1,"stone.broken.4.png");
		trampoline=new Trampoline(game, true, new Vector(125,-6),new Polygon(
			     new Vector(0.0f, 0.0f),
			     new Vector(3.0f, 0.f),
			     new Vector(3.0f, 2.9f),
			     new Vector(0.0f, 2.9f)));
		crate1=new Crate(game, true, new Vector(129,-2), 13, 1,"stone.broken.4.png");
		lever = new Lever(game, true, new Vector(163,-6.5f), new Circle(1f));
		   
	 for(int i=0;i<5;i++)
		 worms.add(new Worm(game, new Vector(37+i*5,-1), new Vector(44+i*5,-1), new Vector(5+i%2,0), 4, 0.5f));
	 	stars.add(new Pickup(game, true, new Vector(168, -8.5f), new Circle(1)));
	    wood = new Wood(game,true, new Vector(165.5f,-7));
	    finish = new Finish(game, true, new Vector(176.5f,-11.5f), new Circle(1f));
	    
	}

	@Override
	public void addAllActors() {
		// TODO Auto-generated method stub
		
		game.addActor(pendulum);
		game.addActor(lever);
		game.addActor(trampoline);
		game.addActor(horisontalBlock1);
		game.addActor(bike);
		game.addActor(terrain1);
		game.addActor(crate);
		game.addActor(bomb);
		game.addActor(crate1);
		game.addActor(wood);
		this.game.addActor(bike.getWheelL());
		this.game.addActor(bike.getWheelR());
		game.addActor(verticalBlock);
		game.addActor(gravitationalField);
		for(int i=0;i<blocks1.size();i++)
		{
			game.addActor(blocks1.get(i));
		}
		for(int i=0;i<worms.size();i++)
			game.addActor(worms.get(i));
		game.addActor(finish);
		for(int i=0;i<stars.size();i++)
			game.addActor(stars.get(i));
		for(int i=0;i<peaks.size();i++)
			game.addActor(peaks.get(i));
	
	
		game.setPayLoad(bike);
		game.setViewCandidate(bike);
	
	}
	
	@Override
	public void draw(Canvas canvas) {
		if(canvas==null)throw new NullPointerException("Argument type Canvas null fo draw method in Falling level");
		message.draw(canvas);
		// TODO Auto-generated method stub
		
	}
	public void update(float deltaTime) {
		
		message.setText("Next Level");
		//the timer goes from 1 to 0 meaning the text fades
		message.setAlpha(timer);
		
		timer = timer - deltaTime;
			//when the text has faded the this actor is removed
			if (timer <= 0)
				game.removeActor(this);

	}

}
