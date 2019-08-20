package com.drk3931.platplus;

import java.util.Observable;

abstract class Item extends Observable
{
    public Item()
    {

    }


    public abstract GameEvent onCollision(Entity e);


}