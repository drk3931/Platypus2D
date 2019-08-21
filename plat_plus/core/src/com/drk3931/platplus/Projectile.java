package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Item {

    Color bulletColor = Color.WHITE;

    int radius = 15;
    private Vector2 vel;
    int velScale = 500;
    public Circle circleRep;

    public Projectile() {
        
        super();
        setShape(circleRep);
        setColor(bulletColor);
    
    }
    
    public void setTrajectory(int xStart, int yStart, int xDir, int yDir)
    {
        this.vel = new Vector2(xDir,yDir).nor().setLength(velScale);
        circleRep = new Circle(xStart,yStart,radius);
        setShape(circleRep);

    }

	@Override
    public GameEvent onCollision(Entity e) {
        return null;
    }


    @Override
    public void update(float delta)
    {
        
        circleRep.x += delta * this.vel.x;
        circleRep.y += delta * this.vel.y;

    }
}