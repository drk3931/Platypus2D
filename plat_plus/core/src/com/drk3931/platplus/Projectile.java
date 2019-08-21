package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Entity implements DrawableComponent {

    Color bulletColor = Color.WHITE;

    int radius = 15;
    int velScale = 500;
    public Circle circleRep;

    public Projectile() {
        
        geometricRepresentation.setColor(bulletColor);
    
    }
    
    public void setTrajectory(int xStart, int yStart, int xDir, int yDir)
    {
        Vector2 vel = new Vector2(xDir,yDir).nor().setLength(velScale);
        circleRep = new Circle(xStart,yStart,radius);
        geometricRepresentation.setShape(circleRep);
        setVelocity(vel.x, vel.y);

    }

	@Override
    public GameEvent onCollision(Entity e) {
       return null;
    }


    @Override
    public void update(float delta)
    {
        move(delta);

    }

    @Override
    public void drawShapeRenderer(ShapeRenderer r) {
        Circle cRep = (Circle)geometricRepresentation.shapeRepresentation;
        r.circle(cRep.x, cRep.y, cRep.radius);
    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {
		
	}

   
}