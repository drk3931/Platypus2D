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
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.drk3931.platplus.PlatPlus.GameState;

public class UIHandler implements Updateable {

    private Skin skin, runningSkin;
    public Stage uiStage, runningStage;

    private Table table;
    private TextButton mainButton;

    private Label gameLabel, healthLabel, healthNumber;

    private PlatPlus gameRef;

    private ProgressBar pb;

    public void onGameOver() {
        gameLabel.setText("Game Over");
        mainButton.setText("Restart Game");
        healthNumber.setText("100");
        Gdx.input.setInputProcessor(uiStage);

    }

    public void onGameWon() {
        gameLabel.setText("You Won!");
        mainButton.setText("Restart Game");
        healthNumber.setText("100");
        Gdx.input.setInputProcessor(uiStage);

    }

    enum UIEvent {
        PLAYER_DAMAGED
    }

    public void onEvent(UIEvent e, Object data) {
        if (e == UIEvent.PLAYER_DAMAGED) {

            Integer asNum = (Integer) data;
            this.healthNumber.setText(asNum);
            pb.setValue(asNum);
        }
    }


    private void setupMenuUI() {

        Container<Table> tableContainer = new Container<Table>();
        float sw = gameRef.svp.getWorldWidth();
        float sh = gameRef.svp.getWorldHeight();
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

    private TextureRegionDrawable getSimpleDrawable() {
        Pixmap bgPixmap = new Pixmap(1, 1, Pixmap.Format.RGB565);
        bgPixmap.setColor(Color.CYAN);
        bgPixmap.fill();
        TextureRegionDrawable t = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        return t; 
    }

    
    private Container<Window> getMessageWindow() {
        float sw = gameRef.svp.getWorldWidth();
        float sh = gameRef.svp.getWorldHeight();

        final Window w = new Window("ALERT", skin);

        w.getTitleLabel().setFontScale(1.2f);

        Label message = new Label(
                "• Use the WASD keys to move. \n• Press SHIFT and W at the same time to running jump. \n• Click on an enemy to attack.",
                runningSkin);
        w.add(message).fill();

        Container<Window> windowContainer = new Container<Window>();
        windowContainer.setSize(sw, sh);
        windowContainer.setActor(w);
        windowContainer.align(Align.top).padTop(50);

        TextButton okButton = new TextButton("Ok!", skin);
        okButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                w.remove();

            }
        });
        w.row();
        w.add(okButton);

        return windowContainer;

    }

    private void setupRunningUI() {


        this.runningStage.clear();

        float sw = gameRef.svp.getWorldWidth();
        float sh = gameRef.svp.getWorldHeight();

  

        Table table = new Table();
        Container<Table> ct = new Container<Table>();
        ct.setSize(sw, sh);
        ct.setActor(table);
        ct.top().left();




        healthLabel = new Label("Health: ", runningSkin,"subtitle");
        healthLabel.setColor(Color.BLUE);


        healthNumber = new Label("100", runningSkin,"subtitle");
        healthNumber.setColor(Color.GREEN);

        healthLabel.setFontScale(0.80f);
        healthNumber.setFontScale(0.80f);

        ProgressBarStyle pbs = runningSkin.get("default-horizontal",ProgressBarStyle.class);
        ProgressBar pb = new ProgressBar(0, 100, 1f, false,pbs);
        pb.setValue(100);

        this.pb = pb;
        


        table.add(healthLabel);
        table.add(pb);
        //Drawable d = getSimpleDrawable();
        //table.background(d);

        runningStage.addActor(ct);

        runningStage.addActor(getMessageWindow());

    }

    public UIHandler(final PlatPlus gameRef) {

        StretchViewport svp = new StretchViewport(PlatPlus.VIRTUAL_WIDTH, PlatPlus.VIRTUAL_HEIGHT);

        runningSkin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"),
                GameLoader.genAtlas("pixthulhu/skin/pixthulhu-ui.atlas"));

        skin = new Skin(Gdx.files.internal("freezing/skin/freezing-ui.json"),
                GameLoader.genAtlas("freezing/skin/freezing-ui.atlas"));

        uiStage = new Stage(svp);
        runningStage = new Stage(svp);

        this.gameRef = gameRef;

        setupMenuUI();

        mainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                gameRef.setGameState(GameState.GAME_RUNNING);
                Gdx.input.setInputProcessor(runningStage);
                setupRunningUI();

                event.stop();

            }

        });

        Gdx.input.setInputProcessor(uiStage);

    }

    @Override
    public void update(float delta) {

        if (PlatPlus.getGameState() == GameState.GAME_WON || PlatPlus.getGameState() == GameState.GAME_OVER
                || PlatPlus.getGameState() == GameState.INITIAL) {
            uiStage.act(delta);
        }
        if (PlatPlus.getGameState() == GameState.GAME_RUNNING) {
            runningStage.act(delta);

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