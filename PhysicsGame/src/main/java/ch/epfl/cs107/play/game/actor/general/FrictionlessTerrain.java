package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Vector;

public class FrictionlessTerrain extends Terrain{

	public FrictionlessTerrain(ActorGame game, boolean fixed, Vector position, Shape shape) {
		super(game, fixed, position, shape);
		partBuilder.setFriction(0f);
		partBuilder.build();
		terrainGraphics.setOutlineColor(Color.LIGHT_GRAY);
		terrainGraphics.setFillColor(Color.white);
		// TODO Auto-generated constructor stub
	}
	

}
