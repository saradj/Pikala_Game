package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Vector;

public class Rocket extends Pickup{
	public Rocket(ActorGame game, boolean fixed, Vector position, Shape shape) {
		super(game, fixed, position, shape);
		getGraphics().setName("missile.blue.7.png");
		//giving this Trigger a unique signature, so when the bike picks it up it gets accelerated
		getPartBuilder().setCollisionSignature(0x4);
		getPartBuilder().setGhost(true);
		getPartBuilder().build();
		// TODO Auto-generated constructor stub
	}
	

}
