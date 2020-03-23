package com.drk3931.platplus;


import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.drk3931.platplus.projectiles.Projectile;

class World implements DrawableComponent {
    
    final public static float accelGravity = -19f;



    
    private Player player;

    ArrayList<Character> characters;
    public static ArrayList<Projectile> projectileStore;


    public Player getPlayer(){
        return this.player;
    }

    public void spawnPlayer(float x, float y){
        player = new Player( x,  y);
    }
    

    public World() {

        characters = new ArrayList<Character>();
        projectileStore = new ArrayList<Projectile>();
        
      
       
    }

    LinkedList<Character> markedForRemoval = new LinkedList<Character>();
    
    public void update(float delta) {

        markedForRemoval.clear();

        player.update(delta);
        for(Character c: characters)
        {
            c.update(delta);
            if(c.characterState.isMarkedForRemoval())
            {
                this.markedForRemoval.add(c);
            }
        }

        characters.removeAll(markedForRemoval);


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


        
       for (Character c: characters) {
            c.drawSpriteBatch(b);
        }

        

        player.drawSpriteBatch(b);



    }

    public void addCharacter(Character c){


        this.characters.add(c);

    }



    public static void addProjectile(Projectile p){

        
        

        projectileStore.add(p);

    }

  
  
}
