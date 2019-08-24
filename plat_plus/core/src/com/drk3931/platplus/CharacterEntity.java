package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public abstract class CharacterEntity extends Entity implements DrawableComponent{



    public Rectangle rectangleRepresentation;
    private boolean gravityEnabled;
    public TextureRegion characterTexture;
    private CharacterRoutine characterRoutine; 
    private CharacterStats characterStats;


    public int yVelocityCap = 335;
    public float lastX,lastY;

    public CharacterEntity(float x,float y, float w, float h, Color c, boolean gravityEnabled, TextureRegion texture, int health)
    {
        this.geometricRepresentation = new GeometricRepresentation();
        this.geometricRepresentation.shapeRepresentation = new Rectangle(x,y,w,h);
        this.geometricRepresentation.color = c;
        this.rectangleRepresentation = (Rectangle)this.geometricRepresentation.shapeRepresentation;
        this.gravityEnabled = gravityEnabled;
        this.characterTexture = texture;
        this.characterStats = new CharacterStats(health);
        loadStats(characterStats);

    }

    public CharacterStats getStats()
    {
        return this.characterStats;
    }



    public void setCharacterRoutine(CharacterRoutine routine)
    {
        this.characterRoutine = routine;
    }

    public void setTexture(TextureRegion texture)
    {
        this.characterTexture = texture;
    }

   
    public void translate(float dx,float dy)
    {
      this.geometricRepresentation.translate(dx, dy);

    }

    public boolean canJump()
    {

        //you can jump the frame after a collision resolution sets yVelocity = 0
        //and accelaration due to gravity is added for one frame. 
        return yVelocity == World.gravityAcceleration;
    }
    
    public void update(float delta){

        if(gravityEnabled)
        {

            this.yVelocity += World.gravityAcceleration;

            if(this.yVelocity < (yVelocityCap * -1))
            {
                this.yVelocity = yVelocityCap * -1;
            }
         
        }
        characterRoutine.routine(delta);
        move(delta);

    }

    public int dx()
    {
        return  (int)Math.abs((this.geometricRepresentation.getX() - this.lastX));
    }

    public int dy()
    {
        return  (int)Math.abs((this.geometricRepresentation.getY() - this.lastY));
    }

    public void setCoordinatesBeforeCollisionResolution()
    {
        this.lastX = geometricRepresentation.getX();
        this.lastY = geometricRepresentation.getY();
    }


    public void drawShapeRenderer(ShapeRenderer shapeRenderer)
    {

        Rectangle rectRep = (Rectangle)this.geometricRepresentation.shapeRepresentation;
        shapeRenderer.setColor(this.getGeometricRepresentation().color);
        shapeRenderer.rect(rectRep.x, rectRep.y, rectRep.width, rectRep.height);

    }

   

    public void drawSpriteBatch(SpriteBatch spriteBatch)
    {   



        if (this.characterTexture != null) {
            Rectangle rectRep = (Rectangle)this.geometricRepresentation.shapeRepresentation;
            spriteBatch.draw(this.characterTexture, rectRep.x, rectRep.y, rectRep.width, rectRep.height);
            

        }
   
    }


}