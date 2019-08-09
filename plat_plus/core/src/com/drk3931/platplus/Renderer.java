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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Renderer{


    public OrthographicCamera camera;
    public ShapeRenderer shapeRenderer;
    public SpriteBatch spriteBatch; 
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
        spriteBatch = new SpriteBatch();

        camera = new OrthographicCamera();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap());
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        cameraUpdate();


    }


    private void cameraUpdate()
    {


        Player p = this.world.getPlayer();


        

        camera.position.x = p.characterEntity.rectangleRepresentation.x;
        camera.position.y = p.characterEntity.rectangleRepresentation.y;

        camera.update();

    

    }


    public void draw()
    {
        gl.glClearColor(0, 0, 0, 1.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        cameraUpdate();


        tiledMapRenderer.render();


        //set render views
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        tiledMapRenderer.setView(camera);

        spriteBatch.begin();

        spriteBatch.end();

        shapeRenderer.begin();
            map.drawShapeRenderer(this.shapeRenderer);
            world.drawShapeRenderer(this.shapeRenderer);
            collisionHandler.draw(this.shapeRenderer);
        shapeRenderer.end();



    }



  



}