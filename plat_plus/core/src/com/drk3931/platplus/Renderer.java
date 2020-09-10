package com.drk3931.platplus;

import static com.badlogic.gdx.Gdx.*;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

class Renderer {

    public OrthographicCamera camera;
    public ShapeRenderer shapeRenderer;
    public SpriteBatch spriteBatch;
    private TiledMapRenderer tiledMapRenderer;

    private Map map;
    private World world;
    private TextureRegion backdropRef;

    public Renderer(Map map) {

        //map needs to be provided in the constructor to setup the tiledMaprenderer
        this.map = map;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        spriteBatch = new SpriteBatch();

        camera = new OrthographicCamera();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        backdropRef = GameLoader.getTexRegion("landscape.png");


    

       



    }

    public void setWorld(World world){
        this.world = world;
    }




    



    public void draw() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  

        camera.update();

        // set render views
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);


        Color lastTint = spriteBatch.getColor();


        spriteBatch.setColor(Color.WHITE);
        spriteBatch.begin();

        for(int i = 0; i < 10; i++){

            
            spriteBatch.draw(backdropRef,i * Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()*2); 

        }
        
    


        spriteBatch.end();

        spriteBatch.setColor(lastTint);

        shapeRenderer.begin();

        
        //this.map.drawShapeRenderer(shapeRenderer);

        if(this.world != null){
            this.world.drawShapeRenderer(shapeRenderer);

        }
        
        
        shapeRenderer.end();


        tiledMapRenderer.setView(camera);

        tiledMapRenderer.render();


        spriteBatch.begin();


        this.map.drawSpriteBatch(spriteBatch);
        
        if(this.world != null){
            this.world.drawSpriteBatch(spriteBatch);

        
            


        }

    

        spriteBatch.end();

    }

    static Vector3 getMousePosInGameWorld(Vector3 vec, Camera camera) {
        return camera.unproject(vec);
    }

}