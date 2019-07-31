package com.drk3931.platplus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;

class Player {

    public CharacterEntity characterEntity;

    final int jumpAcceleration = 500;

    final int initialXVelocity = 333;

    public Player(int x, int y, int w, int h, Color c) {
        characterEntity = new CharacterEntity(x, y, w, h, c, true) {
            @Override
            public void update(float delta) {

                super.update(delta);

                // we can translate based on the input controls
                // this.translate(dx, dy, delta);

                xVelocity = 0;
                if (Gdx.input.isKeyPressed(Keys.A)) {
                    xVelocity = initialXVelocity * -1;
                } 

                if (Gdx.input.isKeyPressed(Keys.D)) {
                    xVelocity = initialXVelocity;
                }   
                
                if (Gdx.input.isKeyPressed(Keys.W) && characterEntity.yVelocity == World.gravityAcceleration) {
                    this.yVelocity = jumpAcceleration;

                }

                this.translate(xVelocity * delta, yVelocity * delta);

            }

        };

    }

}