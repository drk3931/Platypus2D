package com.drk3931.platplus.characterroutines;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.drk3931.platplus.CharacterEntity;
import com.drk3931.platplus.CharacterRoutine;
import com.drk3931.platplus.Player;

public class PlayerRoutine implements CharacterRoutine {

    private CharacterEntity playerEntity;
    private Player player;

    final int jumpAcceleration = 685;
    final int initialXVelocity = 375;

    public PlayerRoutine(Player player) {

        this.playerEntity = player.characterEntity;
        this.player = player;
        
    }

    public void routine(float delta) {

        playerEntity.xVelocity = 0;
        if (Gdx.input.isKeyPressed(Keys.A)) {
            playerEntity.xVelocity = initialXVelocity * -1;
        }

        if (Gdx.input.isKeyPressed(Keys.D)) {
            playerEntity.xVelocity = initialXVelocity;
        }

        if (Gdx.input.isKeyPressed(Keys.W) && playerEntity.canJump()){
            playerEntity.yVelocity = jumpAcceleration;

        }

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            int yPos = Gdx.input.getY();
            int xPos = Gdx.input.getX();
            
            this.player.weapon.fire(xPos, Gdx.graphics.getHeight() - yPos);
        }

    }

}