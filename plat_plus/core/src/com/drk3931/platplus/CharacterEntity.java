package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


abstract class CharacterEntity extends Entity implements DrawableComponent{



    public Rectangle rectangleRepresentation;
    private boolean gravityEnabled;
    private boolean canJump;
    public TextureRegion characterTexture;



    public int xVelocity = 0,yVelocity=0, yVelocityCap = 335;

    public CharacterEntity(float x,float y, float w, float h, Color c, boolean gravityEnabled, TextureRegion texture)
    {
        super(c);
        this.shapeRepresentation = new Rectangle(x,y,w,h);
        this.rectangleRepresentation = (Rectangle)this.shapeRepresentation;
        this.gravityEnabled = gravityEnabled;
        this.characterTexture = texture;

    }


    public void setTexture(TextureRegion texture)
    {
        this.characterTexture = texture;
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

    public boolean canJump()
    {
        return yVelocity == World.gravityAcceleration;
    }

    public void update(float delta){

        if(gravityEnabled && this.yVelocity > yVelocityCap * -1)
        {
            this.yVelocity += World.gravityAcceleration;
        }
        translate(0, this.yVelocity * delta);
    }


}