package ch.epfl.cs107.play.game.actor.general;


import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.bikegame.Bike;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Terrain extends GameEntity implements Actor {

	private PartBuilder partBuilder;
	protected PartBuilder getPartBuilder() {
		return partBuilder;
	}
	private ShapeGraphics terrainGraphics;
	protected ShapeGraphics getTerrainGraphics() {
		return terrainGraphics;
	}
	public Terrain(ActorGame game, boolean fixed, Vector position, Shape shape) {
		super(game, fixed, position);
		if(shape==null)throw new NullPointerException("Given shape null while constucting a Terrain");
		//physical part
		partBuilder=getEntity().createPartBuilder();
		partBuilder.setShape(shape);
		partBuilder.setFriction(0.5f);
		partBuilder.setCollisionEffect(0x1);
		//does not collide with anything by default
		partBuilder.setCollisionSignature(0xffff);
		partBuilder.build();
		terrainGraphics= new ShapeGraphics(shape, Color.BLACK, Color.WHITE, .2f,1,-1);
		
		terrainGraphics.setParent(getEntity()); 
		// TODO Auto-generated constructor stub
	}

	public void update(float deltaTime)
	{
		
			
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
		if(canvas==null)throw new NullPointerException("Argument type Canvas null to draw method in Terrain");
		// TODO Auto-generated method stub
		terrainGraphics.draw(canvas);
	}
	
@Override
public void destroy() {
    getEntity().destroy();
   // getOwner().removeActor(this);
 }
}
