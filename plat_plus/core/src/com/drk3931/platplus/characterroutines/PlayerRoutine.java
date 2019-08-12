package com.drk3931.platplus.characterroutines;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.drk3931.platplus.CharacterEntity;
import com.drk3931.platplus.CharacterRoutine;
import com.drk3931.platplus.Player;

public class PlayerRoutine implements CharacterRoutine{


    private CharacterEntity cEntity;

    
    final int jumpAcceleration = 685;

    final int initialXVelocity = 375;

    
    public PlayerRoutine(Player player)
    {

            this.cEntity = player.characterEntity;
    }

    public void routine(float delta )
    {

        cEntity.xVelocity  = 0;
        if (Gdx.input.isKeyPressed(Keys.A)) {
            cEntity.xVelocity  = initialXVelocity * -1;
        }

        if (Gdx.input.isKeyPressed(Keys.D)) {
            cEntity.xVelocity  = initialXVelocity;
        }

        if (Gdx.input.isKeyPressed(Keys.W) && cEntity.canJump()) {
            cEntity.yVelocity = jumpAcceleration;

        }

        
    }

}