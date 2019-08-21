package com.drk3931.platplus;


public class EntityStats {

    enum STATE{
        ALIVE,
        DEAD,
        SPECIAL
    };

    private int health; 
    private final int maxHealth = 100;
    private STATE entityState;


    public int getHealth()
    {
        return health;
    }
    
    public STATE getState()
    {
        return entityState;
    }

    public EntityStats(int health)
    {
        this.health = health;
        this.entityState = STATE.ALIVE;
    }

    public EntityStats()
    {
        this.entityState = STATE.SPECIAL;
    }

    public void subHealth(int amount)
    {
        if(entityState == STATE.SPECIAL)
        {
            return;
        }

        this.health -= amount;
        if(health < 0)
        {
            health = 0; 
        }
        
    }

    public void incHealth(int amount)
    {   


        if(entityState == STATE.SPECIAL)
        {
            return;
        }

        this.health += amount;
        if(health > maxHealth)
        {
            health = maxHealth; 
        }
     
    }



}