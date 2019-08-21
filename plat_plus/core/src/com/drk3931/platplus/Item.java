package com.drk3931.platplus;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;

abstract class Item implements Updateable
{

    public int xVelocity,yVelocity; 

    

    public Item( Shape2D shapeRep, int x, int y, Color c)
    {
        this.geometricRepresentation = new GeometricRepresentation();
        this.geometricRepresentation.shapeRepresentation = shapeRep;
        this.geometricRepresentation.color = c;

    }



    
    public Item()
    {
        this.geometricRepresentation = new GeometricRepresentation();

    }

    public void setShape(Shape2D shape)
    {
        this.geometricRepresentation.shapeRepresentation = shape;
    }


    public void setColor(Color c)
    {
        this.geometricRepresentation.color = c;
    }




    public GeometricRepresentation geometricRepresentation;
    public abstract GameEvent onCollision(Entity e);


}