package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Vector;

public class FrictionlessTerrain extends Terrain{

	//it is a Terrain, only it's  friction is 0 and has a lighter color
	public FrictionlessTerrain(ActorGame game, boolean fixed, Vector position, Shape shape) {
		super(game, fixed, position, shape);
		getPartBuilder().setFriction(0f);
		getPartBuilder().build();
		getTerrainGraphics().setOutlineColor(Color.PINK);
		getTerrainGraphics().setFillColor(Color.BLACK);
		// TODO Auto-generated constructor stub
	}
	

}
