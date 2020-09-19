package com.drk3931.platplus.effects;

import com.badlogic.gdx.Gdx;
import com.drk3931.platplus.Entity;

public class GravityEffect implements Effect{

    final int gravAcceleration = 50;

    public void apply(Entity e)
    {



        e.setVelocityY(e.getVelocityY() -  gravAcceleration * Gdx.graphics.getDeltaTime());

    }
}
