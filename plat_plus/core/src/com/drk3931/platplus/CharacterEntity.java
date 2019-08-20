package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public abstract class CharacterEntity extends Entity implements DrawableComponent, UpdateableEntity{



    public Rectangle rectangleRepresentation;
    private boolean gravityEnabled;
    public TextureRegion characterTexture;
    private CharacterRoutine characterRoutine; 


    public int xVelocity = 0,yVelocity=0, yVelocityCap = 335;
    public float lastX,lastY;

    public CharacterEntity(float x,float y, float w, float h, Color c, boolean gravityEnabled, TextureRegion texture, int health)
    {
        super(c,health);
        this.shapeRepresentation = new Rectangle(x,y,w,h);
        this.rectangleRepresentation = (Rectangle)this.shapeRepresentation;
        this.gravityEnabled = gravityEnabled;
        this.characterTexture = texture;

    }




    public void setCharacterRoutine(CharacterRoutine routine)
    {
        this.characterRoutine = routine;
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

        //you can jump the frame after a collision resolution sets yVelocity = 0
        //and accelaration due to gravity is added for one frame. 
        return yVelocity == World.gravityAcceleration;
    }
    
    public void update(float delta){



        if(gravityEnabled && this.yVelocity > (yVelocityCap * -1))
        {
            this.yVelocity += World.gravityAcceleration;
        }
        characterRoutine.routine(delta);
        translate(xVelocity * delta, yVelocity * delta);

 
        

        


    }

    public int dx()
    {
        return (int)(this.rectangleRepresentation.x - this.lastX);
    }

    public int dy()
    {
        return  (int)(this.rectangleRepresentation.x - this.lastX);
    }

    public void setCoordinatesBeforeCollisionResolution()
    {
        this.lastX = rectangleRepresentation.x;
        this.lastY = rectangleRepresentation.y;
    }


    public void drawShapeRenderer(ShapeRenderer shapeRenderer)
    {

        Rectangle rectRep = this.rectangleRepresentation;
        shapeRenderer.setColor(this.color);
        shapeRenderer.rect(rectRep.x, rectRep.y, rectRep.width, rectRep.height);

    }

    public void drawSpriteBatch(SpriteBatch spriteBatch)
    {   



        if (this.characterTexture != null) {
            Rectangle rectRep = this.rectangleRepresentation;
            spriteBatch.draw(this.characterTexture, rectRep.x, rectRep.y, rectRep.width, rectRep.height);
            

        }
   
    }


}