package com.drk3931.platplus;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.drk3931.platplus.projectiles.Projectile;

class World implements DrawableComponent {
    
    final public static float accelGravity = -19f;


    
    Player player;

    ArrayList<Character> characters;
    public static ArrayList<Projectile> projectileStore;


    public Player getPlayer(){
        return this.player;
    }
    

    public World() {

        player = new Player();
        characters = new ArrayList<Character>();
        projectileStore = new ArrayList<Projectile>();
        
       
    }

    
    public void update(float delta) {



        player.update(delta);
        for(Character c: characters)
        {
            c.update(delta);
        }

        for(Projectile p: projectileStore)
        {
            p.update(delta);
        }


        //geoRep.translate(lastChangeX, lastChangeY);



    }

    @Override
    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {


        player.drawShapeRenderer(shapeRenderer);
        for(Character c: characters)
        {
            c.drawShapeRenderer(shapeRenderer);
        }
        for(Projectile p: projectileStore)
        {
            p.drawShapeRenderer(shapeRenderer);
        }
        
       
    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {


        /*
       for (Character c: characters) {
            c.getCharacterEntity().drawSpriteBatch(b);
        }

        player.drawSpriteBatch(b);
        */


    }

    public void addCharacter(Character c){


        this.characters.add(c);

    }


    public void addProjectile(Projectile p){


        this.projectileStore.add(p);

    }

  
  
}
