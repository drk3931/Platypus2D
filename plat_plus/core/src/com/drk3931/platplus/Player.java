package com.drk3931.platplus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


class Player {

    public CharacterEntity characterEntity;

    final int jumpAcceleration = 500;

    final int initialXVelocity = 333;

    public Player(int x, int y, int w, int h, Color c) {



        characterEntity = new CharacterEntity(x, y, w, h, c, true,new TextureRegion(new Texture(Gdx.files.internal("gNow.jpeg")))) {
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
                
                if (Gdx.input.isKeyPressed(Keys.W) && characterEntity.canJump()) {
                    this.yVelocity = jumpAcceleration;

                }

                this.translate(xVelocity * delta, yVelocity * delta);

            }

            @Override
            public void drawSpriteBatch(Renderer r)
            {

                b.draw(characterEntity.characterTexture,characterEntity.rectangleRepresentation.x,characterEntity.rectangleRepresentation.y,characterEntity.rectangleRepresentation.width,characterEntity.rectangleRepresentation.height);



              
        
           

                        
            }

            @Override
            public void drawShapeRenderer(ShapeRenderer shapeRenderer)
            {
                shapeRenderer.set(ShapeType.Filled);
        
                shapeRenderer.setColor(characterEntity.color);
                shapeRenderer.rect(characterEntity.rectangleRepresentation.x,
                        characterEntity.rectangleRepresentation.y, characterEntity.rectangleRepresentation.width,
                        characterEntity.rectangleRepresentation.height);
            }

        };

    }

   

}