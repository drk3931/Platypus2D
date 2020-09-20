package com.drk3931.platplus.effects;

import com.badlogic.gdx.Gdx;
import com.drk3931.platplus.Entity;

public class GravityEffect {

    final int gravAcceleration = 220;
    Entity e; 

    public GravityEffect(Entity e){
        this.e  = e;
    }

    public void apply(float delta)
    {

        e.setVelocityY(e.getVelocityY() -  gravAcceleration * delta);

    }
}
