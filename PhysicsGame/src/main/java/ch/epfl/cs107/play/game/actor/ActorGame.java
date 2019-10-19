
	package ch.epfl.cs107.play.game.actor;

	import java.util.ArrayList;
	import java.util.List;

	import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.general.GameWithLevels;
import ch.epfl.cs107.play.io.FileSystem;
	import ch.epfl.cs107.play.math.EntityBuilder;
	import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RopeConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
	import ch.epfl.cs107.play.math.Vector;
	import ch.epfl.cs107.play.math.WheelConstraintBuilder;
	import ch.epfl.cs107.play.math.World;
	import ch.epfl.cs107.play.window.Canvas;
	import ch.epfl.cs107.play.window.Keyboard;
	import ch.epfl.cs107.play.window.Window;

	public abstract class ActorGame implements Game, GameWithLevels {

		private Actor payLoad;
		private World world;
		protected List<Actor> actors;
		
		private Window window;
		private FileSystem fileSystem;
		private EntityBuilder entityBuilder;
		
		// Viewport properties
		private Vector viewCenter;
		private Vector viewTarget;
		private Positionable viewCandidate;
		private static final float VIEW_TARGET_VELOCITY_COMPENSATION = 0.2f;
		
		private static final float VIEW_INTERPOLATION_RATIO_PER_SECOND = 0.1f; 
		private static final float VIEW_SCALE = 10.0f;
		public Keyboard getKeyboard(){ return window.getKeyboard(); }
		public Canvas getCanvas(){ return window; }
		//to pause the game, can be changed by any ActorGame
		protected boolean pause;
		
		
		
	   public void removeActor(int index)
	{
		actors.remove(index);
		
	}
	public void removeActor(Actor actor)
	{
		
		actor.destroy();
		actors.remove(actor);
		
	}
//removing every actor from the game
	public void removeAll()
	{
		for(int i=0;i<actors.size();++i)
		{
		actors.get(i).destroy();
		}
		actors.removeAll(actors);

	} 
	
	
	
	public void addActor(Actor actor)
	{
		actors.add(actor);
	}
	public void setViewCandidate(Actor a){
		viewCandidate=a;
		
	}
	public void setPayLoad(Actor payLoad)
	{
		this.payLoad=payLoad;
	}
	public Actor getPayLoad()
	{
		return payLoad;
	}
	//using this methods to avoid having to use the world in the games to create constraints
	public WheelConstraintBuilder WheelCreator(){
		return world.createWheelConstraintBuilder();
		
	}
	
	public EntityBuilder EntityCreator(){
		return world.createEntityBuilder();
	}
	
	public RopeConstraintBuilder ropeCreator() {
		// TODO Auto-generated method stub
		return world.createRopeConstraintBuilder();
	}
	

		@Override
		public boolean begin(Window window, FileSystem fileSystem) {
			if(window==null)throw new NullPointerException("Argument window for method beging in ActorGame is null");
			if(fileSystem==null)throw new NullPointerException("Argument fileSystem for method begiin in ActorGame is null");
			this.window = window;
	        world = new World();
	        world.setGravity(new Vector(0.0f, -9.81f));
	        this.fileSystem=fileSystem;
	        viewCenter=Vector.ZERO;
	        viewTarget=Vector.ZERO;
	        actors = new ArrayList<Actor>();
	        entityBuilder = world.createEntityBuilder();
	   
			return true;
		}

		@Override
		public void update(float deltaTime) {
			
			//if the game is in pause mode, it does not update
			if(pause)
				return;
			world.update(deltaTime);
			for(int i =0;i<actors.size();++i) {
				actors.get(i).update(deltaTime);
				}
			if (viewCandidate != null) {
				viewTarget = viewCandidate.getPosition().add(viewCandidate.getVelocity() .mul(VIEW_TARGET_VELOCITY_COMPENSATION));
				}
			// Interpolate with previous location 
			float ratio = (float)Math.pow(VIEW_INTERPOLATION_RATIO_PER_SECOND, deltaTime); 
			viewCenter = viewCenter.mixed(viewTarget, ratio);
			// Compute new viewport 
			Transform viewTransform = Transform.I.scaled(VIEW_SCALE).translated(viewCenter); 
			window.setRelativeTransform(viewTransform);
			
			for(Actor actor: actors) {
				actor.draw(window);
			}

		}

		@Override
		public void end() {

		}
		

	}

	