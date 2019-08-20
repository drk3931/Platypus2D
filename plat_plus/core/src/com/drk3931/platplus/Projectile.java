package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;

public class Projectile extends Item {

    int radius = 15;
    Color bulletColor = Color.WHITE;

    public int xDir, yDir;

    public Projectile(int x, int y) {
        
        super();
        setShape(new Circle(x,y,radius));
        setColor(bulletColor);
	}

	@Override
    public GameEvent onCollision(Entity e) {
        return null;
    }

}