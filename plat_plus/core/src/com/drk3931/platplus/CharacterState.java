package com.drk3931.platplus;

import java.util.HashMap;

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


    public CharacterState(){

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