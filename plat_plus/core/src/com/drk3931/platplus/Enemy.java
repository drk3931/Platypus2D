package com.drk3931.platplus;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.drk3931.platplus.characterroutines.PatrolRoutine;

class Enemy {

    
    public CharacterEntity characterEntity;

    

    public Enemy(int x, int y, int w, int h, Color c, boolean gravityEnabled)
    {

        this.characterEntity = new CharacterEntity(x,y,w,h,c,gravityEnabled,null,100){
            @Override
            public void drawShapeRenderer(ShapeRenderer r) {
                r.set(ShapeType.Filled);
                super.drawShapeRenderer(r);
            }
        };


        this.characterEntity.setCharacterRoutine(new PatrolRoutine(this.characterEntity));

    }

}