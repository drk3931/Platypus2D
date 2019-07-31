package com.drk3931.platplus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;

class Player{

    public CharacterEntity characterEntity;


    int xVelocity = 100,yVelocity=100;


    public Player(int x,int y, int w, int h,Color c)
    {
        characterEntity = new CharacterEntity(x, y, w, h, c)
        {
            @Override
            public void update(float delta)
            {

                //we can translate based on the input controls
                //this.translate(dx, dy, delta);
                if(Gdx.input.isKeyPressed(Keys.LEFT))
                {
                    xVelocity = Math.abs(xVelocity) * -1; 
                    this.translate(xVelocity * delta, 0);

                }
                if(Gdx.input.isKeyPressed(Keys.RIGHT))
                {
                    xVelocity = Math.abs(xVelocity);
                    this.translate(xVelocity * delta , 0);

                }
                if(Gdx.input.isKeyPressed(Keys.UP))
                {   yVelocity = Math.abs(yVelocity);
                    this.translate(0 , yVelocity * delta);

                }
                if(Gdx.input.isKeyPressed(Keys.DOWN))
                {
                    yVelocity = Math.abs(yVelocity) * -1;
                    this.translate(0 , yVelocity* delta);

                }

            }

        };

    }

   
}