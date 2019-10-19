package com.drk3931.platplus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.drk3931.platplus.GameEvents.GameEvent;

class World implements DrawableComponent,Updateable {

    
    private static Stack<GameEvent> gameEvents = new Stack<GameEvent>();
    final public static float gravityAcceleration = -19f;


    private Player player;
    public ArrayList<Character> characters;
    public Entity[] gameEntities;    



    public Player getPlayer() {
        return player;
    }

    public World() {

        player = new Player(0, 72, 64, 64);
        characters = new ArrayList<Character>();
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

        Iterator<Character> charIter = characters.iterator();
        while(charIter.hasNext())
        {
            Character c = charIter.next();
            c.update(delta);
            if(c.getCharacterEntity().isMarkedForRemoval())
            {
                charIter.remove();
            }

        }

        player.update(delta);


    }

    @Override
    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {


        for (Character c: characters) {
            c.getCharacterEntity().drawShapeRenderer(shapeRenderer);
        }

        player.drawShapeRenderer(shapeRenderer);


    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {

       for (Character c: characters) {
            c.getCharacterEntity().drawSpriteBatch(b);
        }

        player.drawSpriteBatch(b);


    }


    public void addEnemy(int x, int y)
    {

        Enemy e = new Enemy(x, y, 64, 64, Color.RED, true);
        
        this.characters.add(e);
    }

  
}
