package com.drk3931.platplus;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationHandler {

    private float animationTime;
    private Animation<TextureRegion> animation;
    private boolean loops;


    public AnimationHandler(Animation<TextureRegion> animation,boolean loops){
        this.animation = animation;
        this.loops = loops;
   
    }

    public TextureRegion getCurrentRegion(){
       return animation.getKeyFrame(animationTime, loops);
    }

    public void incrementTime(float delta){
        animationTime+=delta;
        
    }

    public boolean isFinished(){
        return animationTime - this.animation.getAnimationDuration() >= 0;
    }

    public void setAnimation(Animation<TextureRegion> newAnimation){
        animationTime = 0;
        this.animation = newAnimation;
    }




}