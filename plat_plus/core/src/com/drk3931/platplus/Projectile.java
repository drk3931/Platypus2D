package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;

public class Projectile extends Item {

    int radius = 15;
    Color bulletColor = Color.WHITE;

    public int xVel,yVel;
    public Circle circleRep;

    public Projectile() {
        
        super();
        setShape(circleRep);
        setColor(bulletColor);
    
    }
    
    public void setTrajectory(int xStart, int yStart, int xVel, int yVel)
    {
        this.xVel = xVel;
        this.yVel = yVel;
        circleRep = new Circle(xStart,yStart,radius);
        

    }

	@Override
    public GameEvent onCollision(Entity e) {
        return null;
    }


    @Override
    public void update(float delta)
    {
        
        circleRep.x += delta * this.xVel;
        circleRep.y += delta * this.yVel;

    }
}