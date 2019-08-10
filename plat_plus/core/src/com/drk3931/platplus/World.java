package com.drk3931.platplus;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

class World implements DrawableComponent {

    public ArrayList<CharacterEntity> characters;
    public Entity[] gameEntities;
    private Player player;


    final public static float gravityAcceleration = -19f;
    public Player getPlayer() {
        return player;
    }

    public World() {

        player = new Player(0, 72, 64, 64, Color.BLUE);
        characters = new ArrayList<CharacterEntity>();

    }

    public void update(float delta) {
        player.characterEntity.update(delta);


        for(CharacterEntity character: characters)
        {
                character.update(delta);
        }

     

    }


    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {

       player.characterEntity.drawShapeRenderer(shapeRenderer);

       
       for(CharacterEntity character: characters)
       {
               character.drawShapeRenderer(shapeRenderer);
       }

    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {

        player.characterEntity.drawSpriteBatch(b);
        for(CharacterEntity character: characters)
        {
                character.drawSpriteBatch(b);
        }

	}
}
