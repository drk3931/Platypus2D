package com.drk3931.platplus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;

class Player{

    public CharacterEntity characterEntity;


    final int X_SPEED = 34,Y_SPEED=34;


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
                    this.translate(X_SPEED * -1, 0, delta);

                }
                if(Gdx.input.isKeyPressed(Keys.RIGHT))
                {
                    this.translate(X_SPEED , 0, delta);

                }
                if(Gdx.input.isKeyPressed(Keys.UP))
                {
                    this.translate(0 , Y_SPEED, delta);

                }
                if(Gdx.input.isKeyPressed(Keys.DOWN))
                {
                    this.translate(0 , Y_SPEED * -1, delta);

                }

            }

        };

    }

   
}