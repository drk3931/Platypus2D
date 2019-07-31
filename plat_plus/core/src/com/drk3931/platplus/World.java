package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

class World implements DrawableComponent {

    public CharacterEntity[] characters;
    public Entity[] gameEntities;
    private Player player;


    final public static float gravityAcceleration = -19f;
    public Player getPlayer() {
        return player;
    }

    public World() {

        player = new Player(0, 72, 64, 64, Color.BLUE);
        characters = new CharacterEntity[0];

    }

    public void update(float delta) {
        player.characterEntity.update(delta);

        for (int i = 0; i < characters.length; i++) {

            characters[i].update(delta);

        }

    }

    public void draw(Renderer r) {

        ShapeRenderer shapeRenderer = r.shapeRenderer;

        shapeRenderer.set(ShapeType.Filled);

        shapeRenderer.setColor(player.characterEntity.color);
        shapeRenderer.rect(player.characterEntity.rectangleRepresentation.x,
                player.characterEntity.rectangleRepresentation.y, player.characterEntity.rectangleRepresentation.width,
                player.characterEntity.rectangleRepresentation.height);

        for (int i = 0; i < characters.length; i++) {

            CharacterEntity entity = characters[i];
            Rectangle entityRect = entity.rectangleRepresentation;

            shapeRenderer.setColor(entity.color);
            shapeRenderer.rect(entityRect.x, entityRect.y, entityRect.width, entityRect.height);

        }

    }
}
