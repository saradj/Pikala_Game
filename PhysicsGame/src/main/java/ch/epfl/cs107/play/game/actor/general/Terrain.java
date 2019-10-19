package ch.epfl.cs107.play.game.actor.general;


import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Terrain extends GameEntity implements Actor {
PartBuilder partBuilder;
	ShapeGraphics terrainGraphics;
	public Terrain(ActorGame game, boolean fixed, Vector position, Shape shape) {
		super(game, fixed, position);
		partBuilder=getEntity().createPartBuilder();
		partBuilder.setShape(shape);
		partBuilder.setFriction(0.5f);
		partBuilder.setCollisionEffect(0x1);
		partBuilder.setCollisionSignature(0xffff);


		 partBuilder.build();
		terrainGraphics= new ShapeGraphics(shape, Color.white, Color.black, .2f);
		terrainGraphics.setParent(getEntity());
		// TODO Auto-generated constructor stub
	}

	@Override
	public Transform getTransform() {
		// TODO Auto-generated method stub
		return getEntity().getTransform();
	}

	@Override
	public Vector getVelocity() {
		// TODO Auto-generated method stub
		return getEntity().getVelocity();
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		terrainGraphics.draw(canvas);
	}
	
@Override
public void destroy() {
    getEntity().destroy();
 }
}
