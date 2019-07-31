package com.drk3931.platplus;

import static com.badlogic.gdx.Gdx.*;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.utils.compression.lzma.Base;

class Renderer{


    private OrthographicCamera camera;
    public ShapeRenderer shapeRenderer;
    private TiledMapRenderer tiledMapRenderer;

    private World world;
    private Map map; 
    private CollisionHandler collisionHandler;

    public Renderer(Map map, World world, CollisionHandler collisionHandler)
    {   

        this.collisionHandler = collisionHandler;


        this.world = world;
        this.map = map;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap());




    }


    private void cameraUpdate()
    {

        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        tiledMapRenderer.setView(camera);
        camera.update();

    

    }


    public void draw()
    {
        gl.glClearColor(0, 0, 0, 1.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cameraUpdate();

        tiledMapRenderer.render();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin();
        shapeRenderer.set(ShapeType.Line);

        map.draw(this);
        world.draw(this);
        collisionHandler.draw(this);


       

        shapeRenderer.end();



    }



  



}