package com.drk3931.platplus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.drk3931.platplus.PlatPlus.GameState;
import com.drk3931.platplus.Player.PlayerState;
import com.drk3931.platplus.projectiles.Projectile;

class World implements DrawableComponent {

    final public static float accelGravity = -19f;

    private Player player;

    private PlatPlus gameRef;

    private ArrayList<Character> characters;
    public static ArrayList<Projectile> projectileStore;

    public Player getPlayer() {
        return this.player;
    }

    public void spawnPlayer(float x, float y) {
        player = new Player(x, y);
    }

    public World(PlatPlus gameRef) {

        characters = new ArrayList<Character>();
        projectileStore = new ArrayList<Projectile>();
        this.gameRef = gameRef;

    }



    public void update(float delta) {

        delta *=2;

        if(gameOver()){
            gameRef.setGameState(GameState.GAME_OVER);
        }


        if (PlatPlus.getGameState() == GameState.INITIAL || PlatPlus.getGameState() == GameState.GAME_OVER) {
            return;
        }

        player.update(delta);
        

        Iterator<Character> iter = characters.iterator();
        while (iter.hasNext()) {
            Character c = iter.next();
            if (c.characterState.isMarkedForRemoval()) {
                iter.remove();
            } else {
                c.update(delta);
            }
        }



        for (Projectile p : projectileStore) {
            p.update(delta);
        }

        // geoRep.translate(lastChangeX, lastChangeY);

    }

    @Override
    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {

        player.drawShapeRenderer(shapeRenderer);
        for (Character c : characters) {
            c.drawShapeRenderer(shapeRenderer);
        }
        for (Projectile p : projectileStore) {
            p.drawShapeRenderer(shapeRenderer);
        }

    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {

        for (Character c : characters) {
            c.drawSpriteBatch(b);
        }

        player.drawSpriteBatch(b);

    }

    public void addCharacter(Character c) {

        this.characters.add(c);

    }

    public static void addProjectile(Projectile p) {

        projectileStore.add(p);

    }

    public boolean gameOver() {
         
        boolean gameOver1 = this.player.getCurrentState() == PlayerState.DEAD;

        boolean gameOver2 = player.e.getGeoRep().getY() < player.e.getGeoRep().getHeight() * -1;

        return gameOver1 || gameOver2;
    }

    public ArrayList<Character> getCharacters(){
        return characters;
    }

}
