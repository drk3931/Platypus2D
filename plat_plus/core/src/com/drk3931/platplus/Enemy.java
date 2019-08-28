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

        this.characterEntity = new CharacterEntity(x, y, w, h, c, gravityEnabled, null) {

            @Override
            public void drawShapeRenderer(ShapeRenderer r) {
                r.set(ShapeType.Filled);
                super.drawShapeRenderer(r);

            }

            @Override
            public void onCollision(Entity e) {

            }

            @Override
            public void onDestroyRoutine() {
                geometricRepresentation.setColor(Color.WHITE);

            }

        }.setDeathTime(2.0f);

        this.characterEntity.setIdentity(Identity.ENEMY);

        this.characterEntity.setCharacterRoutine(new PatrolRoutine(this.characterEntity));

        this.characterEntity.getCharacterStats().setHealth(100);
        this.characterEntity.getCharacterStats().setMaxHealth(100);


    }


    @Override
    public void update(float delta) {

       characterEntity.update(delta);

    }

}