package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

class Enemy {

    private CharacterEntity characterEntity;

    public Enemy(int x, int y, int w, int h, Color c, boolean gravityEnabled)
    {

        this.characterEntity = new CharacterEntity(x,y,w,h,c,gravityEnabled,null){

            @Override
            public void update(float delta)
            {
                super.update(delta);
            }
        
            @Override
            public void drawSpriteBatch(SpriteBatch b) {
                super.drawSpriteBatch(b);
            }
        
            @Override
            public void drawShapeRenderer(ShapeRenderer r) {
                super.drawShapeRenderer(r);
            }
        };


        this.characterEntity.setCharacterRoutine(new CharacterRoutine(){
        
            @Override
            public void routine(float delta, CharacterEntity cEntity) {

                    System.out.println("calling routine on ENEMY");

                
            }
        });

    }

}