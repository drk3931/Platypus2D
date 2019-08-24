package com.drk3931.platplus;

public class CharacterStats extends EntityStats
{
    private int health; 
    private int maxHealth;
    private boolean markedForRemoval = false;

    public boolean markedForRemoval()
    {
        return markedForRemoval;
    }

  


    public int getHealth()
    {
        return health;
    }


    public void setHealth(int h)
    {
        this.health = h;
    }

    public void setMaxHealth(int h)
    {
        this.maxHealth = h;
    }
    

    public void subHealth(int amount)
    {
        if(entityState == STATE.SPECIAL)
        {
            return;
        }

        System.out.println(this.health);
        this.health -= amount;
        if(health < 0)
        {
            health = 0; 
            markedForRemoval = true;
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