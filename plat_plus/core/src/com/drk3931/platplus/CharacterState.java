package com.drk3931.platplus;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.drk3931.platplus.behaviors.Behavior;

public class CharacterState implements Updateable {

    public enum State {
        DEFAULT, DEAD, MOVING, DAMAGED

        /*
         * ATTACKING, DEFENDING, SPAWNING, ALERTED, DAMAGED
         */
    }

    private State currentState;
    private Behavior currentBehavior;
    private HashMap<String, Integer> stats;

    private AnimationHandler movingAnimation;
    private AnimationHandler deathAnimation;
    private AnimationHandler defaultAnimation;

    private TextureRegion currentTexture;

    private Character controllingCharacter;

    public State getCurrentState() {
        return this.currentState;
    }

    public TextureRegion getCurrentRegion() {
        return currentTexture;
    }

    public void setDefaultAnimation(Animation<TextureRegion> a) {

        this.defaultAnimation = new AnimationHandler(a);

    }

    public CharacterState(Character c) {

        this.stats = new HashMap<String, Integer>();
        this.currentState = State.DEFAULT;
        this.controllingCharacter = c;
    }

    public void setHealth(int amount) {
        stats.put("health", amount);
    }

    public void setBehavior(Behavior b) {
        this.currentBehavior = b;
    }

    public Behavior getCurrentBehavior() {
        return this.currentBehavior;
    }

    public void modifyHealth(int amount) {
        stats.put("health", stats.get("health") + amount);
    }

    int lastHealth;

    long damagedTimer = 250;
    long lastDamaged;

    @Override
    public void update(float delta) {

        currentTint = transparent;

        if (stats.get("health") < lastHealth) {
            currentState = State.DAMAGED;
            lastDamaged = System.currentTimeMillis();
        }

        if (stats.get("health") <= 0) {
            this.currentState = State.DEAD;
        }

        if (currentState == State.DEAD) {

        } else {
            this.defaultAnimation.incrementTime(delta);
            this.currentTexture = defaultAnimation.getCurrentRegion();
        }

        if (currentState == State.DAMAGED && System.currentTimeMillis() - lastDamaged < damagedTimer) {
            currentTint = Color.RED;
        }
        else{
            currentTint = transparent;
            currentState = State.DEFAULT;
            
        }

        this.lastHealth = stats.get("health");

    }

    private Color currentTint;
    private Color transparent = new Color(1,1,1,1);
    
    public Color getTint(){

        return currentTint;
    }

}