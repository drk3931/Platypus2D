package com.drk3931.platplus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.drk3931.platplus.characterroutines.PlayerRoutine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {

    public CharacterEntity characterEntity;


    public Player(int x, int y, int w, int h, Color c) {

        TextureRegion pTex = new TextureRegion(new Texture(Gdx.files.internal("gPast.jpeg")));

        characterEntity = new CharacterEntity(x, y, w, h, c, true, pTex,100) {
            @Override
            public void update(float delta) {

                super.update(delta);


            }

        };


        this.characterEntity.setCharacterRoutine(new PlayerRoutine(this));

    }

}