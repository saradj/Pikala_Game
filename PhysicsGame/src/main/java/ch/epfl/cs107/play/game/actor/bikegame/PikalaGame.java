package ch.epfl.cs107.play.game.actor.bikegame;

import java.util.List;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.game.actor.general.GameWithLevels;
import ch.epfl.cs107.play.game.actor.general.Level;
import ch.epfl.cs107.play.game.actor.general.Pickup;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class PikalaGame extends ActorGame implements GameWithLevels {

	private TextGraphics message, scoreMessage, restartMessage;
	private int actualLevel = 0, score = 0, lastLevel;
	private List<Level> list;
	private boolean win, gameOver;
	private float timer;
	//the PickUps use this method to print the player's score on the screen
	public void Score(int i) {
		score = i;
	}
	protected List<Level> createLevelList() {
		return Arrays.asList(new JumpLevel(this), new FallingLevel(this));
	}

	public boolean begin(Window window, FileSystem fileSystem) {
		
		super.begin(window, fileSystem);
		timer=1.5f;
		list = this.createLevelList();
		
		list.get(0).createAllActors();
		list.get(0).addAllActors();
		lastLevel=list.size()-1;
		scoreMessage = new TextGraphics("Score: " + score, 0.1f, Color.pink, Color.WHITE, 0.02f, true, false,
				new Vector(-1f, -2f), 1.0f, 100.0f);
		scoreMessage.setParent(getCanvas());
		scoreMessage.setRelativeTransform(Transform.I.translated(0.0f, -1.0f));
		restartMessage = new TextGraphics("", 0.1f, Color.RED, Color.WHITE, 0.02f, true, false,
				new Vector(0.5f, 2f), 1.0f, 100.0f);
		restartMessage.setParent(getCanvas());
		restartMessage.setRelativeTransform(Transform.I.translated(0.0f, -1.0f));
		
		message = new TextGraphics("", 0.3f, Color.BLACK, Color.WHITE, 0.02f, true, false, new Vector(0.5f, 0.5f), 1.0f,
				100.0f);
		message.setParent(getCanvas());
		message.setRelativeTransform(Transform.I.translated(0.0f, -1.0f));
		addActor(list.get(0));
		
		return true;

	}

	public void update(float deltaTime) {
		
		super.update(deltaTime);
		
		//knowing that there is danger in the first level after 278m a warning is printed for the player
		if(actualLevel==1&&this.getPayLoad().getPosition().x>20&&timer>0)
			{timer=timer-deltaTime;
			message.setText("Slow down");
			
			message.setAlpha(timer);}
		
		//adding the possibility of pausing and continuing the game
		if (getKeyboard().get(KeyEvent.VK_P).isPressed()) {
			//using the score message to print the stage
			scoreMessage.setText("Game Paused, press U to continue");
			scoreMessage.setAnchor(new Vector(0.5f, 0.5f));
			//a boolean inherited from ActorGame 
			pause=true;
		}
		
		if (getKeyboard().get(KeyEvent.VK_U).isPressed()) {
			pause=false;
			scoreMessage.setAnchor(new Vector(-1f, -2f));
		}
		
		
		if(!pause)
		scoreMessage.setText("Score: " + score);
		
		scoreMessage.draw(getCanvas());
		message.draw(getCanvas());
		restartMessage.draw(getCanvas());
		//getting the information from the main actor in this case the Bike if he has fallen
		if (((Bike)this.getPayLoad()).hasFallen()) {
			
			message.setAlpha(1);
			message.setDepth(1);
			message.setText("Game over");
			restartMessage.setText("Press R to restart the level");
			gameOver=true;
		
		}
		if(win){
			restartMessage.setText("Press R to restart the game");
			scoreMessage.setText("Final score: "+ score);
		}
		//Possibility to restart the level(if gameOver) of game if you won
		if (getKeyboard().get(KeyEvent.VK_R).isPressed()) {
			if(win){
				actualLevel=0;
				resetlevel();
			}
			if(gameOver)
				resetlevel();
		
		}
		

	}
	//method called by the Actor Finish if it has been triggered and the game is won
	@Override
	public void nextLevel() {
		
		
		if (actualLevel == lastLevel) {
			message.setAlpha(1);
			message.setText("You win");
			win=true;
			scoreMessage.setText("Final score: "+ score);
			return;
		}

		
		removeAll();
		++actualLevel;
		this.list.get(actualLevel).createAllActors();
		this.list.get(actualLevel).addAllActors();
		this.addActor(list.get(actualLevel));

	}

	
	@Override
	public void resetlevel() {
		removeAll();  
		--actualLevel;
		message.setText("");
		restartMessage.setText("");
		win=false;
		gameOver=false;
		//reseting the score by calling a static method in Pickup
		Pickup.resetScore();
		score = 0;
		nextLevel();

	}
	

}
