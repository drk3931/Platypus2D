package com.drk3931.platplus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.drk3931.platplus.GameEvents.GameEvent;

class World implements DrawableComponent,Updateable {

    public ArrayList<Enemy> enemies;
    public Entity[] gameEntities;
    private Player player;
    private static Stack<GameEvent> gameEvents = new Stack<GameEvent>();



    final public static float gravityAcceleration = -19f;

    public Player getPlayer() {
        return player;
    }

    public World() {

        player = new Player(0, 72, 64, 64);
        enemies = new ArrayList<Enemy>();
    }

    public static void pushEvent(GameEvent e)
    {
        gameEvents.push(e);
    }

    public void update(float delta) {


        
        Iterator<GameEvent> i = gameEvents.iterator();
        while(i.hasNext())
        {
            GameEvent e = (GameEvent)i.next();
            e.action(delta);
            i.remove();
        }

        Iterator<Enemy> charIter = enemies.iterator();
        while(charIter.hasNext())
        {
            Enemy e = charIter.next();
            e.update(delta);
            if(e.characterEntity.isReadyToBeDestroyed())
            {
                charIter.remove();
            }

        }

        player.update(delta);


    }

    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {


        for (Enemy enemy : enemies) {
            enemy.characterEntity.drawShapeRenderer(shapeRenderer);
        }

        player.drawShapeRenderer(shapeRenderer);


    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {

        for (Enemy enemy : enemies) {
            enemy.characterEntity.drawSpriteBatch(b);
        }

        player.drawSpriteBatch(b);


    }


    public void addEnemy(int x, int y)
    {

        Enemy e = new Enemy(x, y, 64, 64, Color.RED, true);
        
        this.enemies.add( e );
    }

  
}
