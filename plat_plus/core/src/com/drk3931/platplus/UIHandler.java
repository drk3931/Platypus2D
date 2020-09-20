package com.drk3931.platplus;

import java.text.NumberFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.drk3931.platplus.PlatPlus.GameState;

public class UIHandler implements Updateable {

    private Skin skin, runningSkin;
    public Stage uiStage, runningStage;

    private Table table;
    private TextButton mainButton;

    private Label gameLabel, healthLabel, healthNumber;

    private PlatPlus gameRef;

    public void onGameOver() {
        gameLabel.setText("Game Over");
        mainButton.setText("Restart Game");
        healthNumber.setText("100");
    }

    public void onGameWon() {
        gameLabel.setText("You Won!");
        mainButton.setText("Restart Game");
        healthNumber.setText("100");
    }

    enum UIEvent {
        PLAYER_DAMAGED
    }

    public void onEvent(UIEvent e, Object data) {
        if (e == UIEvent.PLAYER_DAMAGED) {

            Integer asNum = (Integer) data;
            this.healthNumber.setText(asNum);
        }
    }

    private void loadMessageWindow() {

        float sw = Gdx.graphics.getWidth();
        float sh = Gdx.graphics.getHeight();


        final Window w = new Window("ALERT", skin){
        

            @Override
            public float getPrefHeight() {
                return 100f;
            }
            
            @Override
            public float getPrefWidth() {
                return 400f;
            }

        };

        w.getTitleLabel().setFontScale(1.2f);


        Label message = new Label("Use the WASD keys to move. Press SHIFT to sprint. \n Click on an enemy to attack.",
                runningSkin,"title-plain");
        message.setFontScale(0.80f);
        w.add(message).fill();

      
        

        Container<Window> windowContainer = new Container<Window>();
        windowContainer.setSize(sw, sh);
        windowContainer.setActor(w);
        windowContainer.align(Align.top).padTop(100);

        TextButton okButton = new TextButton("Ok!", skin);
        okButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                w.remove();
                
            }
        });
        w.row();
        w.add(okButton);

        runningStage.addActor(windowContainer);

    }

    private void setupMenuUI() {

        Container<Table> tableContainer = new Container<Table>();
        float sw = Gdx.graphics.getWidth();
        float sh = Gdx.graphics.getHeight();
        float cw = sw * 0.7f;
        float ch = sh * 0.5f;

        table = new Table(skin);

        tableContainer.setSize(cw, ch);
        tableContainer.setPosition((sw - cw) / 2.0f, (sh - ch) / 2.0f);
        tableContainer.fillX();
        tableContainer.padBottom(100);

        gameLabel = new Label("Demo Level", skin, "title");
        gameLabel.setAlignment(Align.center);
        gameLabel.setFontScale(1.25f);

        mainButton = new TextButton("New Game", skin);

        // The fill method causes a widget to be sized to the cell.
        // expand makes the logical table take up

        table.row();
        table.add(gameLabel);
        table.row();
        table.add(mainButton);

        tableContainer.setActor(table);
        uiStage.addActor(tableContainer);

    }

    private void setupRunningUI() {

        float sw = Gdx.graphics.getWidth();
        float sh = Gdx.graphics.getHeight();

        Table table = new Table();
        table.setSize(sw, sh);

        table.top().left();

        // table.background(skin.getDrawable("textfield"));

        healthLabel = new Label("Health: ", runningSkin, "title-plain");
        healthLabel.setFontScale(1.25f);
        healthLabel.setColor(Color.GREEN);

        healthNumber = new Label("100", runningSkin, "title-plain");
        healthNumber.setFontScale(1.25f);
        healthNumber.setColor(Color.WHITE);

        table.add(healthLabel);
        table.add(healthNumber);

        runningStage.addActor(table);

        loadMessageWindow();



    }

    public UIHandler(final PlatPlus gameRef) {

        runningSkin = new Skin(Gdx.files.internal("shade/skin/uiskin.json"),
                GameLoader.genAtlas("shade/skin/uiskin.atlas/"));

        skin = new Skin(Gdx.files.internal("freezing/skin/freezing-ui.json"),
                GameLoader.genAtlas("freezing/skin/freezing-ui.atlas"));

        uiStage = new Stage(new ScreenViewport());
        runningStage = new Stage(new ScreenViewport());

        this.gameRef = gameRef;

        setupMenuUI();
        setupRunningUI();

        mainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                gameRef.setGameState(GameState.GAME_RUNNING);
                Gdx.input.setInputProcessor(runningStage);
                loadMessageWindow();

                event.stop();

            }

        });

        Gdx.input.setInputProcessor(uiStage);

    }

    @Override
    public void update(float delta) {

        if (PlatPlus.getGameState() == GameState.GAME_WON || PlatPlus.getGameState() == GameState.GAME_OVER
                || PlatPlus.getGameState() == GameState.INITIAL) {
            uiStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
            uiStage.act(Gdx.graphics.getDeltaTime());
        }
        if (PlatPlus.getGameState() == GameState.GAME_RUNNING) {
            runningStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
            runningStage.act(Gdx.graphics.getDeltaTime());

        }

    }

    public void draw() {
        if (PlatPlus.getGameState() == GameState.GAME_WON || PlatPlus.getGameState() == GameState.GAME_OVER
                || PlatPlus.getGameState() == GameState.INITIAL) {
            uiStage.draw();
        }
        if (PlatPlus.getGameState() == GameState.GAME_RUNNING) {

            runningStage.draw();

        }
    }

}