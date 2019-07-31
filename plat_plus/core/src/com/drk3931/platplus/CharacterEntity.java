package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

class CharacterEntity extends Entity{



    public Rectangle rectangleRepresentation;

    public CharacterEntity(float x,float y, float w, float h, Color c)
    {
        super(c);
        this.shapeRepresentation = new Rectangle(x,y,w,h);
        this.rectangleRepresentation = (Rectangle)this.shapeRepresentation;

    }

    public  void setXY(int x,int y)
    {
        this.rectangleRepresentation.x = x;
        this.rectangleRepresentation.y = y;

    }
    public  void setWH(int w,int h)
    {
        this.rectangleRepresentation.width = w;
        this.rectangleRepresentation.height = h;

    }
    public void translate(float dx,float dy)
    {
        this.rectangleRepresentation.x +=   dx ;
        this.rectangleRepresentation.y +=   dy ;

    }

    public void update(float delta){}

}