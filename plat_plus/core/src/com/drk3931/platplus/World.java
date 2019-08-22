package com.drk3931.platplus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

class World implements DrawableComponent,Updateable {

    public ArrayList<Enemy> characters;
    public Entity[] gameEntities;
    private Player player;
    public static Stack<GameEvent> gameEvents;


    final public static float gravityAcceleration = -19f;

    public Player getPlayer() {
        return player;
    }

    public World() {

        player = new Player(0, 72, 64, 64, Color.BLUE);
        characters = new ArrayList<Enemy>();
        gameEvents = new Stack<GameEvent>();
    }

    public void update(float delta) {

        Iterator i = gameEvents.iterator();
        while(i.hasNext())
        {
            GameEvent e = (GameEvent)i.next();
            e.action();
        }


        for (Enemy enemy : characters) {
            enemy.characterEntity.update(delta);
        }

        player.characterEntity.update(delta);


    }

    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {


        for (Enemy enemy : characters) {
            enemy.characterEntity.drawShapeRenderer(shapeRenderer);
        }

        player.characterEntity.drawShapeRenderer(shapeRenderer);


    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {

        for (Enemy enemy : characters) {
            enemy.characterEntity.drawSpriteBatch(b);
        }

        player.characterEntity.drawSpriteBatch(b);


    }


    public void addEnemy(int x, int y)
    {

        Enemy e = new Enemy(x, y, 64, 64, Color.RED, true);
        

        
        this.characters.add( e );
    }

  
}
