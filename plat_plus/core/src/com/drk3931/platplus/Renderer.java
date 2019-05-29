package com.drk3931.platplus;

import static com.badlogic.gdx.Gdx.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.utils.compression.lzma.Base;

class Renderer{


    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;


    public Renderer()
    {

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);


        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.setToOrtho(false, 800, 480);



    }


    public void draw(Shape2D[] mapTiles,CharacterEntity[] entities)
    {
        gl.glClearColor(0, 0, 0, 1.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin();
        shapeRenderer.set(ShapeType.Line);


        drawMap(mapTiles);
        drawCharacters(entities);

        shapeRenderer.end();



    }

    private void drawMap(Shape2D[] mapTiles)
    {

        shapeRenderer.setColor(Color.WHITE);
        for(int i =0; i < mapTiles.length; i++)
        {

            Shape2D s = mapTiles[i];
            if(s.getClass() == Rectangle.class)
            {
                Rectangle r = (Rectangle)s;
                shapeRenderer.rect(r.x, r.y, r.width, r.height);

            }
        
        }
        

    }

    private void drawCharacters(CharacterEntity[] entities)
    {

    
        if(entities == null)
        {
            return;
        }

        for(int i = 0; i < entities.length; i++)
        {
            CharacterEntity entity = entities[i];
            Rectangle entityRect = entity.rectangleRepresentation;

            shapeRenderer.setColor(entity.color);
            shapeRenderer.rect(entityRect.x, entityRect.y, entityRect.width, entityRect.height);

        }
        


    }



}