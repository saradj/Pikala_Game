
	package ch.epfl.cs107.play.game.actor;

	import java.util.ArrayList;
	import java.util.List;

	import ch.epfl.cs107.play.game.Game;
	import ch.epfl.cs107.play.io.FileSystem;
	import ch.epfl.cs107.play.math.EntityBuilder;
	import ch.epfl.cs107.play.math.Positionable;
	import ch.epfl.cs107.play.math.Transform;
	import ch.epfl.cs107.play.math.Vector;
	import ch.epfl.cs107.play.math.WheelConstraintBuilder;
	import ch.epfl.cs107.play.math.World;
	import ch.epfl.cs107.play.window.Canvas;
	import ch.epfl.cs107.play.window.Keyboard;
	import ch.epfl.cs107.play.window.Window;

	public abstract class ActorGame implements Game {

		public World world;
		protected List<Actor> actors;
		
		Window window;
		private FileSystem fileSystem;
		  EntityBuilder entityBuilder;
		
		// Viewport properties
		private Vector viewCenter;
		private Vector viewTarget;
		private Positionable viewCandidate;
		private static final float VIEW_TARGET_VELOCITY_COMPENSATION = 0.2f;
		
		private static final float VIEW_INTERPOLATION_RATIO_PER_SECOND = 0.1f; 
		private static final float VIEW_SCALE = 10.0f;
		public Keyboard getKeyboard(){ return window.getKeyboard(); }
		public Canvas getCanvas(){ return window; }
		
		
	   public void removeActor(int index)
	{
		actors.remove(index);
		
	}
	public void removeActor(Actor actor)
	{
		
		actors.remove(actor);
		actor.destroy();
		
	}

	public void removeAll()
	{
				actors.removeAll(actors);

				for(int i=0;i<actors.size();++i)
				{
				actors.get(i).destroy();
				
				}
			
			

		
	}
	
	public void addActor(Actor actor)
	{
		actors.add(actor);
	}
	public void setViewCandidate(Actor a){
		viewCandidate=a;
		
	}
	public Actor getViewCandidate()
	{
		return (Actor)viewCandidate;
	}
	public WheelConstraintBuilder WheelCreator(){
		return world.createWheelConstraintBuilder();
		
	}
	public EntityBuilder EntityCreator(){
		return world.createEntityBuilder();
	}


		@Override
		public boolean begin(Window window, FileSystem fileSystem) {
			this.window = window;
	        world = new World();
	        world.setGravity(new Vector(0.0f, -9.81f));
	        this.setFileSystem(fileSystem);
	        viewCenter=Vector.ZERO;
	        viewTarget=Vector.ZERO;
	        actors = new ArrayList<Actor>();
	        entityBuilder = world.createEntityBuilder();
	       
	          
			
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public void update(float deltaTime) {
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
			// TODO Auto-generated method stub

		}

		@Override
		public void end() {
			// TODO Auto-generated method stub

		}
		public FileSystem getFileSystem() {
			return fileSystem;
		}
		public void setFileSystem(FileSystem fileSystem) {
			
			this.fileSystem = fileSystem;
		}

	}

	