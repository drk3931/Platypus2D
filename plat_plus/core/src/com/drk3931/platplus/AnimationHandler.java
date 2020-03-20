package com.drk3931.platplus;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationHandler {

    private float animationTime;
    private Animation<TextureRegion> animation;
    private Entity e;


    public AnimationHandler(Animation<TextureRegion> animation, Entity e){
        this.animation = animation;
        this.e = e; 
    }

    public void incrementTime(float delta){
        animationTime+=delta;
    }


    private TextureRegion lastFlipped;

    public void draw(SpriteBatch b){
        TextureRegion currentFrame = animation.getKeyFrame(animationTime, true);
    
        currentFrame.setRegionWidth(e.getGeoRep().getWidth());
        currentFrame.setRegionHeight(e.getGeoRep().getHeight());
        if(e.getVelocityX() < 0)
        {
            currentFrame.flip(true, false);
            lastFlipped = currentFrame;
        }
        else{
            lastFlipped = null;
        }

  
    
        b.draw(currentFrame, e.getGeoRep().getX(),e.getGeoRep().getY());

        if(lastFlipped != null){
            lastFlipped.flip(true, false);
                
        }
    }

    
 
  



}