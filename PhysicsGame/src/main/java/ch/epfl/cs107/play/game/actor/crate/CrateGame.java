package ch.epfl.cs107.play.game.actor.crate;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.math.Shape;
import java.util.ArrayList;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class CrateGame extends ActorGame {
	Crate crate1;
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		
		super.begin(window, fileSystem);
	 crate1=new Crate(this, false,new Vector(0.0f, 5.0f) );
	 
	this.addActor(crate1);
	
	return true;
	}
	

}

