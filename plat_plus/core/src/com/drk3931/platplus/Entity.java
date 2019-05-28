package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Shape2D;

abstract class Entity{

    protected Shape2D shapeRepresentation;
    public Color color;

    public Entity(Color col)
    {
        this.color = col;
    }

    public abstract void setXY(int x,int y);
    public abstract void setWH(int w,int h);
    public abstract void translate(int dx,int dy,float delta);
}