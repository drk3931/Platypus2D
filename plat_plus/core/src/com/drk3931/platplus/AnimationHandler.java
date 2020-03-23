package com.drk3931.platplus;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationHandler {

    private float animationTime;
    private Animation<TextureRegion> animation;


    public AnimationHandler(Animation<TextureRegion> animation){
        this.animation = animation;
   
    }

    public TextureRegion getCurrentRegion(){
        return animation.getKeyFrame(animationTime, true);
    }

    public void incrementTime(float delta){
        animationTime+=delta;
        
    }




}