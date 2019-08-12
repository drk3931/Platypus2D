package com.drk3931.platplus.characterroutines;


import com.badlogic.gdx.math.MathUtils;
import com.drk3931.platplus.CharacterEntity;
import com.drk3931.platplus.CharacterRoutine;


public class PatrolRoutine implements CharacterRoutine
{


    int patrolXVelocity = 100,patrolStartDirection; 

    CharacterEntity controllingCharacter;

    float patrolTime = 0; 
    float MAX_PATROL_TIME = 3.5f;

    public PatrolRoutine(CharacterEntity characterEntity)
    {
        if( Math.random() < 0.5)
        {
            this.patrolStartDirection = -1;
        }
        else{
            this.patrolStartDirection = 1; 
        }

        this.controllingCharacter = characterEntity;
    }

    public void routine(float delta)
    {      
        patrolTime += delta;
        this.controllingCharacter.xVelocity = patrolXVelocity * patrolStartDirection;

        if(patrolTime > MAX_PATROL_TIME)
        {
            patrolStartDirection *= -1;
            patrolTime = 0;
            MAX_PATROL_TIME = MathUtils.random(2,5);
        }


        
        

    }

}