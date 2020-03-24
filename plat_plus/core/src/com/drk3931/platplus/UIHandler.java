package com.drk3931.platplus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.drk3931.platplus.PlatPlus.GameState;

public class UIHandler implements Updateable {

    private Skin skin;
    private Stage stage;

    private Table table;
    private TextButton startButton;



    public UIHandler() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);


        table.setPosition(0, Gdx.graphics.getHeight());
        startButton = new TextButton("New Game", skin);

        
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PlatPlus.setGameState(GameState.GAME_RUNNING);
                startButton.setVisible(false);
                event.stop();
            }
        });

        table.padTop(100);

        table.add(startButton).padBottom(30);

        table.row();

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float delta) {
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        stage.act(Gdx.graphics.getDeltaTime());

    }

    public void draw() {
        stage.draw();
    }

}