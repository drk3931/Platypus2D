package com.drk3931.platplus;

import java.util.Observable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Shape2D;

abstract class Entity {

    protected Shape2D shapeRepresentation;
    public Color color;
    public EntityStats entityStats;
    public Item items;



    public Entity(Color col, int health)
    {
        this.color = col;
        this.entityStats = new EntityStats(health);
    }

    public Entity(Color col)
    {
        this.color = col;
        this.entityStats = new EntityStats();
    }

    public abstract void setXY(int x,int y);
    public abstract void setWH(int w,int h);
    public abstract void translate(float dx,float dy);


}