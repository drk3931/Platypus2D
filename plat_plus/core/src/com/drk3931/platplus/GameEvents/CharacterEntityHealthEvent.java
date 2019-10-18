package com.drk3931.platplus.GameEvents;

import com.drk3931.platplus.CharacterEntity;
import com.drk3931.platplus.Entity;

public class CharacterEntityHealthEvent extends EntityOnEntityEvent {

    int healthModification;

    public CharacterEntityHealthEvent(int healthModification,Entity source, CharacterEntity target)
    {
        this.healthModification = healthModification;
        this.source = source; 
        this.target = target; 
    }

    private void modifyCharacterHealth()
    {
        if(Math.signum(healthModification) < 0 )
        {
            target.getCharacterStats().subHealth(healthModification);
        }
        else{
            target.getCharacterStats().incHealth(healthModification);
        }
    }

    public void action(float delta)
    {
        modifyCharacterHealth();
    }


}