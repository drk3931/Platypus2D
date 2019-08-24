package com.drk3931.platplus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.drk3931.platplus.Entity.Identity;
import com.drk3931.platplus.characterroutines.PatrolRoutine;

public class Enemy implements Updateable {

    public CharacterEntity characterEntity;

    public Enemy(int x, int y, int w, int h, Color c, boolean gravityEnabled) {

        this.characterEntity = new CharacterEntity(x, y, w, h, c, gravityEnabled, null, 100) {

            @Override
            public void drawShapeRenderer(ShapeRenderer r) {
                r.set(ShapeType.Filled);
                super.drawShapeRenderer(r);

            }

            @Override
            public void onCollision(Entity e) {

            }

        };

        this.characterEntity.setIdentity(Identity.ENEMY);

        this.characterEntity.setCharacterRoutine(new PatrolRoutine(this.characterEntity));

    }

    float destructionTimer = 0;
    float deathTime = 2f;
    boolean readyToBeDestroyed = false;

    @Override
    public void update(float delta) {

        if(!readyToBeDestroyed)
        {
            characterEntity.update(delta);
        }
        if (!characterEntity.getStats().markedForRemoval()) {
            readyToBeDestroyed = false;
        } else {
            destructionTimer += delta;
            characterEntity.geometricRepresentation.setColor(Color.WHITE);
            if (destructionTimer > deathTime) {
                readyToBeDestroyed = true;
            }

        }

    }

    public boolean readyToBeDestroyed() {
        return this.readyToBeDestroyed;
    }

}