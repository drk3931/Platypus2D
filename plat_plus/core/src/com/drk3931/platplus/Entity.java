package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Entity implements DrawableComponent {

    private GeometricRepresentation geoRep;

    private Vector2 velocity;

    private TextureRegion currentTextureRegion;

    private Vector2 lastVelocity;

    public TextureRegion getCurrentTextureRegion(){
        return this.currentTextureRegion;
    }

    public void setCurrentTextureRegion(TextureRegion tex){
        this.currentTextureRegion = tex;
    }


    public float getVelocityX() {
        return velocity.x;
    }

    public float getVelocityY() {
        return velocity.y;
    }

    public Entity() {
        velocity = new Vector2();
    }

    public Entity setGeoRep(GeometricRepresentation geo) {
        this.geoRep = geo;
        lastVelocity = new Vector2();
        return this;
    }

    public GeometricRepresentation getGeoRep() {
        return this.geoRep;
    }

    public void moveX() {
        this.geoRep.translate(this.velocity.x, 0);
    }

    public void moveY() {
        this.geoRep.translate(0, this.velocity.y);
    }

    public void setVelocityX(float vx) {
        lastVelocity.x=velocity.x;
        this.velocity.x = vx;
    }

    public void setVelocityY(float vy) {
        lastVelocity.y=velocity.y;
        this.velocity.y = vy;
    }

    public void setVelocity(float x, float y) {
        this.velocity.x = x;
        this.velocity.y = y;
    }

    public Vector2 getVelocity() {
        return this.velocity;
    }

    public void move(float delta) {
        this.geoRep.translate(delta * velocity.x, delta * velocity.y);
    }

    @Override
    public void drawShapeRenderer(ShapeRenderer r) {
      
        //this.geoRep.drawShapeRenderer(r);

    }

    Color tint = new Color(1,1,1,1);

    public void setTint(Color tint) {
        this.tint = tint;
    }

    private boolean shouldFlip = false;

    @Override
    public void drawSpriteBatch(SpriteBatch b) {

        if(currentTextureRegion == null){
            return;
        }
        
    
        b.setColor(tint); 

        if(getVelocityX() < 0 ){
            shouldFlip = true;
        }

        if(getVelocityX() > 0 ){
            shouldFlip = false;
        }
    


        
        if(shouldFlip && !currentTextureRegion.isFlipX())
        {
            currentTextureRegion.flip(true, false);
        }
        
        if(!shouldFlip && currentTextureRegion.isFlipX())
        {
            currentTextureRegion.flip(true, false);
        }


        if(overrideFlip){
            currentTextureRegion.flip(true, false);
        }



        b.draw(currentTextureRegion,geoRep.getX(),geoRep.getY(),geoRep.getWidth(),geoRep.getHeight());

      
        

    }


    boolean overrideFlip = false;

    void setOverrideFlip(boolean flip){
        this.overrideFlip = flip;
    }


    
}