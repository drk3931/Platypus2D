package com.drk3931.platplus;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.drk3931.platplus.behaviors.Behavior;

public class CharacterState implements Updateable
{


    public enum State{
        ALIVE,
        DEAD,
        MOVING

        /*
        ATTACK,
        DEFENSE,
        SPAWN,
        ALERT,
          */ 
    }

    private State currentState;
    private Behavior currentBehavior;
    private HashMap<String,Integer> stats;

    private AnimationHandler movingAnimation;
    private AnimationHandler deathAnimation;

    private TextureRegion defaultAppearence;
    private TextureRegion currentTexture;

    private Character controllingCharacter;

    public State getCurrentState(){
        return this.currentState;
    }
    public TextureRegion getCurrentRegion(){
        return currentTexture;
    }

    public CharacterState(Character c){

        this.stats = new HashMap<String,Integer>();
        this.currentState = State.ALIVE;
        this.controllingCharacter = c;
    }

    public void setHealth(int amount)
    {
        stats.put("health", amount);
    }

    public void setBehavior(Behavior b)
    {
        this.currentBehavior = b;
    }

    public Behavior getCurrentBehavior()
    {
        return this.currentBehavior;
    }


    public void modifyHealth(int amount)
    {
        stats.put("health", stats.get("health") + amount);
    }

	@Override
	public void update(float delta) {

        if(stats.get("health") <= 0){
            stats.put("health",0);
            this.currentState = State.DEAD;
            this.deathAnimation.incrementTime(delta);
            this.currentTexture = deathAnimation.getCurrentRegion();
        }
      
        else{
            this.currentTexture = defaultAppearence;
        }
		
	}

}