package com.drk3931.platplus;

class GravityEffect implements Effect{

    final int gravAcceleration = 50;

    public void apply(Entity e,float delta)
    {

        if(e.getVelocityY() <= -30)
        {
            return;
        }

        e.setVelocityY(e.getVelocityY() -  (gravAcceleration * delta));

    }
}
