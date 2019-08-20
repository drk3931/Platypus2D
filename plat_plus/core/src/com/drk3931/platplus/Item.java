package com.drk3931.platplus;

import java.util.Observable;

abstract class Item extends Observable
{

    int xVelocity,yVelocity; 

    public Item()
    {
        
    }

    public GeometricRepresentation geometricRepresentation;
    public abstract GameEvent onCollision(Entity e);


}