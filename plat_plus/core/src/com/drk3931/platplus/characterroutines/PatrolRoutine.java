package com.drk3931.platplus.characterroutines;


import com.drk3931.platplus.CharacterEntity;
import com.drk3931.platplus.CharacterRoutine;


public class PatrolRoutine implements CharacterRoutine
{


    int patrolXVelocity = 100,patrolStartDirection; 

    CharacterEntity controllingCharacter;

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

        this.controllingCharacter.xVelocity = patrolXVelocity * patrolStartDirection;


        System.out.println(controllingCharacter.dx());
        if(Math.abs(controllingCharacter.dx()) < 10)
        {
            patrolStartDirection *= -1;
        }
        
        

    }

}