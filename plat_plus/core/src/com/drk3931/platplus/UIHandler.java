package com.drk3931.platplus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.drk3931.platplus.PlatPlus.GameState;

public class UIHandler implements Updateable {

    private Skin skin;
    private Stage stage;

    private Table table;
    private TextButton mainButton;

    private Label gameLabel;

    public UIHandler(final PlatPlus gameRef) {
        skin = new Skin();
        stage = new Stage(new ScreenViewport());

        Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));

        gameLabel = new Label("Demo Level", new Label.LabelStyle(font, Color.ORANGE));

        skin.add("default", font);
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());
        mainButton = new TextButton("New Game", skin);

        mainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                try {

                    PlatPlus.setGameState(GameState.GAME_RUNNING);
                    mainButton.setVisible(false);
                    gameLabel.setVisible(false);
                    gameRef.loadWorld();
                    event.stop();

                } catch (Exception e) {
                        
                        
                
                }

            }
        });

        table.padTop(100);

        table.row();
        table.add(gameLabel).padBottom(35);
        table.row();
        table.add(mainButton);
        table.row();

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float delta) {

        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        stage.act(Gdx.graphics.getDeltaTime());

        if (PlatPlus.getGameState() == GameState.GAME_OVER) {
            mainButton.setText("Restart Game");
            gameLabel.setVisible(true);
            mainButton.setVisible(true);
            
        }

    }

    public void draw() {
        stage.draw();
    }

}