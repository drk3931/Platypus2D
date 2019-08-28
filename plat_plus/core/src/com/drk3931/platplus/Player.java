package com.drk3931.platplus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.drk3931.platplus.Entity.Identity;
import com.drk3931.platplus.characterroutines.PlayerRoutine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player implements Updateable,DrawableComponent{

    public CharacterEntity characterEntity;

    public ProjectileWeapon weapon;


    public Player(int x, int y, int w, int h) {

        TextureRegion pTex = new TextureRegion(new Texture(Gdx.files.internal("gPast.jpeg")));

        characterEntity = new CharacterEntity(x, y, w, h, Color.BLUE, true, pTex) {
         
        
            @Override
            public void onCollision(Entity e){

    
            }

            @Override
            public void onDestroyRoutine()
            {
                
            }


        };

        weapon = new ProjectileWeapon(this.characterEntity);

        this.characterEntity.setIdentity(Identity.PLAYER);


        this.characterEntity.setCharacterRoutine(new PlayerRoutine(this));
        this.characterEntity.getCharacterStats().setHealth(100);
        this.characterEntity.getCharacterStats().setMaxHealth(100);

    }

    @Override
    public void drawShapeRenderer(ShapeRenderer r) {
        r.set(ShapeType.Line);
        characterEntity.drawShapeRenderer(r);
        weapon.drawShapeRenderer(r);
    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {
       characterEntity.drawSpriteBatch(b);
    }

    @Override
    public void update(float delta) {
        characterEntity.update(delta);
        weapon.update(delta);

	}

}