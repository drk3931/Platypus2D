package com.drk3931.platplus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class Player {

    public CharacterEntity characterEntity;

    final int jumpAcceleration = 500;

    final int initialXVelocity = 333;

    public Player(int x, int y, int w, int h, Color c) {

        TextureRegion pTex = new TextureRegion(new Texture(Gdx.files.internal("gPast.jpeg")));

        characterEntity = new CharacterEntity(x, y, w, h, c, true, null) {
            @Override
            public void update(float delta) {

                super.update(delta);


            }

            @Override
            public void drawSpriteBatch(SpriteBatch batch) {

                super.drawSpriteBatch(batch);

            }

            @Override
            public void drawShapeRenderer(ShapeRenderer shapeRenderer) {

                super.drawShapeRenderer(shapeRenderer);
            }
        };


        this.characterEntity.setCharacterRoutine(new CharacterRoutine(){
        
            @Override
            public void routine(float delta, CharacterEntity cEntity) {
                
                cEntity.xVelocity  = 0;
                if (Gdx.input.isKeyPressed(Keys.A)) {
                    cEntity.xVelocity  = initialXVelocity * -1;
                }

                if (Gdx.input.isKeyPressed(Keys.D)) {
                    cEntity.xVelocity  = initialXVelocity;
                }

                if (Gdx.input.isKeyPressed(Keys.W) && characterEntity.canJump()) {
                    cEntity.yVelocity = jumpAcceleration;

                }

                cEntity.translate(cEntity.xVelocity * delta, cEntity.yVelocity * delta);

            }
        });

    }

}