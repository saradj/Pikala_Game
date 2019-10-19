package ch.epfl.cs107.play.game.actor.crate;



import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.ActorGame;

import ch.epfl.cs107.play.io.FileSystem;

import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class CrateGame extends ActorGame {
	
	//creating a list of Crates to store the 3 crates
	private List<Crate> crates;
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		
		
		super.begin(window, fileSystem);
		
		crates=new ArrayList<>();
		crates.add(new Crate(this, false, new Vector(0.0f, 5.0f), 1, 1, "stone.broken.4.png"));
		crates.add(new Crate(this, false, new Vector(0.2f, 7.0f), 1, 1, "stone.broken.4.png"));
		crates.add(new Crate(this, false, new Vector(2.0f, 6.0f), 1, 1,"stone.broken.4.png"));
		
		for(int i=0;i<crates.size();i++)
			this.addActor(crates.get(i));
	
	
	
		
		return true;

}
	
	@Override
	public void nextLevel() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resetlevel() {
		// TODO Auto-generated method stub
		
	}
	}

