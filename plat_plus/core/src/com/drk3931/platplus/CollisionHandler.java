package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;

class CollisionHandler implements DrawableComponent {

    private World world;
    private Map map;

    
    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {

        /*
        shapeRenderer.set(ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        for (int i = 0; i < broadPhase.length; i++) {
            Rectangle re = (Rectangle) broadPhase[i];

            shapeRenderer.rect(re.getX(), re.getY(), re.getWidth(), re.getHeight());
        }
        */

    }

    public void drawSpriteBatch(SpriteBatch r) {

    }

    public CollisionHandler(Map map, World world) {
        this.world = world;
        this.map = map;
        this.tmpRectangle = new Rectangle();

    }

    Shape2D[] broadPhase = new Shape2D[9];
    {
        for (int i = 0; i < broadPhase.length; i++) {
            broadPhase[i] = new Rectangle();
        }
    }

    Rectangle tmpRectangle;

    public void update(float delta) {



      

        for (Enemy e : world.characters) {

            broadPhase(e.characterEntity);
            narrowPhase(e.characterEntity, delta);
        }


        Player player = world.getPlayer();

        broadPhase(player.characterEntity);
        narrowPhase(player.characterEntity, delta);
     

    }

    private void broadPhase(CharacterEntity cEntity) {

        Rectangle rect = cEntity.rectangleRepresentation;

        for(int i = 0; i < broadPhase.length; i++)
        {
            ((Rectangle) broadPhase[i]).set(0, 0, 0, 0);
        }

        int xStart = (int) (rect.getX() - (rect.getX() % map.CELL_W));
        xStart -= map.CELL_W;
        int xEnd = xStart + (map.CELL_W * 3);

        int yStart = (int) (rect.getY() - (rect.getY() % map.CELL_H));
        yStart -= map.CELL_H;
        int yEnd = yStart + (map.CELL_H * 3);

        int rectCounter = 0;

        for (int x = xStart; x < xEnd; x += map.CELL_W) {

            for (int y = yStart; y < yEnd; y += map.CELL_H) {

                Cell c = map.getCell("Terrain", (int) x / map.CELL_W, (int) y / map.CELL_H);

                if (c != null) {
                    ((Rectangle) broadPhase[rectCounter]).set(x, y, map.CELL_W, map.CELL_H);

                }

                rectCounter++;

            }

        }

    }

    private void narrowPhase(CharacterEntity characterEntity, float delta) {



        for (int i = 0; i < broadPhase.length; i++) {
            Rectangle r = (Rectangle) broadPhase[i];

            Rectangle characterRect = characterEntity.rectangleRepresentation;

            if (Intersector.intersectRectangles(characterRect, r, tmpRectangle)) {

                int oppositeXVelocity = characterEntity.xVelocity * -1;
                int oppositeYVelocity = characterEntity.yVelocity * -1;


                //characterEntity.setCoordinatesBeforeCollisionResolution();

                // translate back a step
                characterEntity.translate(delta * oppositeXVelocity, delta * oppositeYVelocity);

                 // try to move Y;
                 characterEntity.translate(0, delta * oppositeYVelocity * -1);
                 if (Intersector.intersectRectangles(characterRect, r, tmpRectangle)) {

                    if(tmpRectangle.height < tmpRectangle.width)
                    {
                        characterEntity.translate(0, ( tmpRectangle.getHeight()) * Math.signum(oppositeYVelocity));
                        characterEntity.yVelocity = 0;
                    }
                 }
 

                 // try to move X;
                 characterEntity.translate(delta * oppositeXVelocity * -1, 0);
                 if (Intersector.intersectRectangles(characterRect, r, tmpRectangle)) {

                    if(tmpRectangle.width < tmpRectangle.height)
                    {
                     characterEntity.translate((tmpRectangle.getWidth()) * Math.signum(oppositeXVelocity), 0);
                     characterEntity.xVelocity = 0;
                    }
                 }

               
            
               

            }

        }
    }

}