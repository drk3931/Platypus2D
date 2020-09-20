package com.drk3931.platplus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationHandler {

    private float animationTime, defaultDuration,timeScale = 1.0f;
    private Animation<TextureRegion> animation;
    private boolean loops;


    public AnimationHandler(Animation<TextureRegion> animation,boolean loops){
        this.animation = animation;
        this.loops = loops;
        this.defaultDuration = animation.getAnimationDuration();
        Gdx.app.log("Animation Speed ", "" + defaultDuration);
   
    }

    public TextureRegion getCurrentRegion(){
       return animation.getKeyFrame(animationTime, loops);
    }

    public void setTimeScale(float scale){
        this.timeScale = scale;
    }


    public void incrementTime(float delta){
        animationTime+=delta*timeScale*2.1f;
        
    }

    public boolean isFinished(){
        return animationTime - this.animation.getAnimationDuration() >= 0;
    }

    public void setAnimation(Animation<TextureRegion> newAnimation){
        animationTime = 0;
        this.animation = newAnimation;
    }

    



}