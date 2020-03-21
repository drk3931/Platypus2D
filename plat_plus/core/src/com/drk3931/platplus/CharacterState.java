package com.drk3931.platplus;

import java.util.HashMap;

import com.drk3931.platplus.behaviors.Behavior;

public class CharacterState implements Updateable
{
    HashMap<String,Integer> stats;

    

    public enum State{
        ALIVE,
        DEAD,
        ATTACK,
        DEFENSE,
        SPAWN,
        ALERT,
        DEATH,
        MOVING     
    }

    private State currentState;
    private Behavior currentBehavior;


    public CharacterState(){

        this.stats = new HashMap<String,Integer>();

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
        stats.put("health", stats.get("health") - amount);
    }

	@Override
	public void update(float delta) {

        if(stats.get("health") < 0){
            this.currentState = State.DEAD;
        }
		
	}

}