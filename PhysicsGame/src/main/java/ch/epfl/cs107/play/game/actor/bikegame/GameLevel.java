package ch.epfl.cs107.play.game.actor.bikegame;

import java.util.List;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.actor.general.GameWithLevels;
import ch.epfl.cs107.play.game.actor.general.Level;
import ch.epfl.cs107.play.game.actor.general.Pickup;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;


public class GameLevel extends ActorGame implements GameWithLevels{
	
	
	TextGraphics message,scoreMessage;
	int i=0,score=0;
	 List<Level> list;
	 int actualLevel=0;
	 boolean hasFallen;
	 public void Score(int i )
	 {
		 score=i;
	 }
	 public void setHasFallen(boolean hasFallen)
	 {
		 this.hasFallen=hasFallen;
	 }
	boolean levelPassed;
	public void setLevelPassed(boolean l)
	{
		levelPassed=l; 
	}
	protected List<Level> createLevelList() {
		return  Arrays.asList(new Level1(this),new Level2(this), new Level3(this));
	}
	
	
	
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window,fileSystem);
		 list= this.createLevelList();
		 list.get(0).createAllActors();
		 list.get(0).addAllActors();
		 scoreMessage=new  TextGraphics("Score: "+ score, 0.1f, Color.RED , Color.WHITE , 0.02f, true , false , new Vector (-1f, -2f), 1.0f, 100.0f) ;
		 scoreMessage.setParent(getCanvas()) ;
		 scoreMessage.setRelativeTransform(Transform.I.translated (0.0f, -1.0f)) ;
		 
		
		 message=new  TextGraphics("", 0.3f, Color.RED , Color.WHITE , 0.02f, true , false , new Vector (0.5f, 0.5f), 1.0f, 100.0f) ;
		 message.setParent(getCanvas()) ;
		 message.setRelativeTransform(Transform.I.translated (0.0f, -1.0f)) ;
		 addActor(list.get(0));
		return true ;
		
	}
	public void update(float deltaTime){
		super.update(deltaTime);
	
		scoreMessage.setText("Score: " + score);
		scoreMessage.draw(getCanvas());
		message.draw(getCanvas());
		if(getKeyboard().get(KeyEvent.VK_R).isPressed())
		{
			
			resetlevel();
			return;
		}
		if(hasFallen)
		{
			message.setText("Game over");
			scoreMessage.setText("");
			
		}
if(levelPassed)
{
	
	
	
	nextLevel();
	levelPassed=false;
	
}

	
		}
		  


	@Override
	public void nextLevel() {
		
		
		if(i==list.size()-1)
		{
			
			message.setText("You win");
			this.list.get(i).getBike().handUp=true;
			System.out.println("i new = "+i);
			
			return;
		}

		levelPassed=false;
		if(i>-1)
		this.list.get(i).removeAllActors();
		//k++;
		//i=(++i)%list.size();
		++i;
		this.list.get(i).createAllActors();
		this.list.get(i).addAllActors();
		addActor(list.get(i));

		
	}



	@Override
	public void resetlevel() {
		list.get(i).removeAllActors();
			i=-1;
			message.setText("");
			hasFallen=false;
		Pickup.setScore(0);
		score=0;
			
		nextLevel();
		
	}



	
	
	
	
		
	}
