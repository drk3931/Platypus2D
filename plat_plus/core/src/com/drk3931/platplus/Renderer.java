package com.drk3931.platplus;

import static com.badlogic.gdx.Gdx.*;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public Renderer(Map map) {

        //map needs to be provided in the constructor to setup the tiledMaprenderer
        this.map = map;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        spriteBatch = new SpriteBatch();

        camera = new OrthographicCamera();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());



    }

    public void setWorld(World world){
        this.world = world;
    }

    



    public void draw() {
  

        camera.update();

        // set render views
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        tiledMapRenderer.setView(camera);

        tiledMapRenderer.render();

        shapeRenderer.begin();

        
        this.map.drawShapeRenderer(shapeRenderer);

        if(this.world != null){
            this.world.drawShapeRenderer(shapeRenderer);

        }
        
        
        shapeRenderer.end();

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