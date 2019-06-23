package com.drk3931.platplus;

import static com.badlogic.gdx.Gdx.*;

import java.util.ArrayList;

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
    private ShapeRenderer shapeRenderer;
    private TiledMapRenderer tiledMapRenderer;



   


    public Renderer(TiledMap map)
    {

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);


        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.setToOrtho(false, 800, 480);

        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);



    }


    public void draw(ArrayList<Shape2D> mapPolies,CharacterEntity[] entities)
    {
        gl.glClearColor(0, 0, 0, 1.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin();
        shapeRenderer.set(ShapeType.Line);


        drawMap(mapPolies);
        drawCharacters(entities);

        shapeRenderer.end();



    }

    private void drawMap(ArrayList<Shape2D> mapTiles)
    {

        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.set(ShapeType.Line);
        for(int i =0; i < mapTiles.size(); i++)
        {

            Shape2D s = mapTiles.get(i);
            if(s == null)
            {
                return;
            }
            if(s.getClass() == Rectangle.class)
            {
                Rectangle r = (Rectangle)s;
                shapeRenderer.rect(r.x, r.y, r.width, r.height);

            }
            if(s.getClass() == Circle.class)
            {
                Circle c = (Circle)s;
                shapeRenderer.circle(c.x , c.y, c.radius);

            }
        
        }
        

    }

    private void drawCharacters(CharacterEntity[] entities)
    {
        
        shapeRenderer.set(ShapeType.Filled);

        for(int i = 0; i < entities.length; i++)
        {
            CharacterEntity entity = entities[i];
            Rectangle entityRect = entity.rectangleRepresentation;

            shapeRenderer.setColor(entity.color);
            shapeRenderer.rect(entityRect.x, entityRect.y, entityRect.width, entityRect.height);

        }
        


    }



}