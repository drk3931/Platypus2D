package com.drk3931.platplus;

import static com.badlogic.gdx.Gdx.*;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

class Renderer {

    public OrthographicCamera camera;
    public ShapeRenderer shapeRenderer;
    public SpriteBatch spriteBatch;
    private TiledMapRenderer tiledMapRenderer;

    private ArrayList<DrawableComponent> drawableComponents;
    private Map map;

    public Renderer(Map map) {

        //map needs to be provided in the constructor to setup the tiledMaprenderer
        this.map = map;
        drawableComponents = new ArrayList<DrawableComponent>();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        spriteBatch = new SpriteBatch();

        camera = new OrthographicCamera();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        cameraUpdate();


    }

    private void cameraUpdate() {


        /*
        Player p = this.world.getPlayer();
        GeometricRepresentation pGeoRep = p.characterEntity.geometricRepresentation;
        
        camera.position.x = pGeoRep.getX();
        camera.position.y = pGeoRep.getY();

        */

        camera.update();

    }

    public void addDrawableComponent(DrawableComponent c)
    {
        this.drawableComponents.add(c);
    }

    public void draw() {
        gl.glClearColor(0, 0, 0, 1.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cameraUpdate();

        // set render views
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        tiledMapRenderer.setView(camera);

        tiledMapRenderer.render();

        shapeRenderer.begin();

        
        this.map.drawShapeRenderer(shapeRenderer);
        for(DrawableComponent d: drawableComponents)
        {
            d.drawShapeRenderer(shapeRenderer);
        }
        
        shapeRenderer.end();

        spriteBatch.begin();
        this.map.drawSpriteBatch(spriteBatch);

        for(DrawableComponent d: drawableComponents)
        {
            d.drawSpriteBatch(spriteBatch);
        }

        spriteBatch.end();

    }

}