package com.drk3931.platplus;

import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;

class CollisionHandler implements DrawableComponent {

    private World world;
    private Map map;

    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {

        /*
         * shapeRenderer.set(ShapeType.Line); shapeRenderer.setColor(Color.RED); for
         * (int i = 0; i < broadPhase.length; i++) { Rectangle re = (Rectangle)
         * broadPhase[i];
         * 
         * shapeRenderer.rect(re.getX(), re.getY(), re.getWidth(), re.getHeight()); }
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

        /*
        for (Character c : world.characters) {

            broadPhase(c.getCharacterEntity());
            narrowPhase(c.getCharacterEntity(), delta);
        }

        Player player = world.getPlayer();

        broadPhase(player.characterEntity);
        narrowPhase(player.characterEntity, delta);

        Iterator<Projectile> i = player.weapon.poppedProjectiles.iterator();

        while (i.hasNext()) {
            Projectile p = (Projectile) i.next();
            simpleBroadPhase(p.geometricRepresentation.shapeRepresentation);
            boolean terrainCollision = simpleNarrowPhase(p.geometricRepresentation.shapeRepresentation);
            projectileCharacterIntersect(p);
            if (terrainCollision || p.isMarkedForRemoval()) {
                i.remove();
            }
          
        }
        */

    }

    private void projectileCharacterIntersect(Projectile p)
    {

        /*
        Circle c = p.circleRep;
        for(Character character: world.characters)
        {
            Rectangle enemyShape = (Rectangle)character.getCharacterEntity().geometricRepresentation.getShape();
            if(Intersector.overlaps(c,enemyShape)){
                p.onCollision(character.getCharacterEntity());
            }
        }
        */
    }

    private void broadPhase(CharacterEntity cEntity) {

        Rectangle rect = (Rectangle)cEntity.geometricRepresentation.shapeRepresentation;

        for (int i = 0; i < broadPhase.length; i++) {
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

    private void simpleBroadPhase(Shape2D shape)  {


        for (int i = 0; i < broadPhase.length; i++) {
            ((Rectangle) broadPhase[i]).set(0, 0, 0, 0);
        }

        int shapeX = 0, shapeY = 0;
        if (shape instanceof Circle) {
            int radius = (int) ((Circle) shape).radius;
            shapeX = (int) ((Circle) shape).x - radius;
            shapeY = (int) ((Circle) shape).y - radius;

        } else if (shape instanceof Rectangle) {

            shapeX = (int) ((Rectangle) shape).x;
            shapeY = (int) ((Rectangle) shape).y;

        } else {
        
        }

        int xStart = (int) (shapeX - (shapeX % map.CELL_W));
        xStart -= map.CELL_W;
        int xEnd = xStart + (map.CELL_W * 3);

        int yStart = (int) (shapeY - (shapeY % map.CELL_H));
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

    private boolean simpleNarrowPhase(Shape2D shape) {

        for (int i = 0; i < broadPhase.length; i++) {
            Rectangle r = (Rectangle) broadPhase[i];
            if (shape instanceof Circle) {
                Circle circleShape = (Circle) shape;
                if (Intersector.overlaps(circleShape, r)) {
                    return true;
                }

            }

            else if (shape instanceof Rectangle) {
                Rectangle rectangleShape = (Rectangle) shape;
                if (Intersector.overlaps(rectangleShape, r)) {
                    return true;
                }
            }

        }

        return false;

    }

    private void narrowPhase(CharacterEntity characterEntity, float delta) {

        for (int i = 0; i < broadPhase.length; i++) {
            Rectangle r = (Rectangle) broadPhase[i];

            Rectangle characterRect = (Rectangle)characterEntity.geometricRepresentation.getShape();

            if (Intersector.intersectRectangles(characterRect, r, tmpRectangle)) {

                float oppositeXVelocity = characterEntity.xVelocity * -1;
                float oppositeYVelocity = characterEntity.yVelocity * -1;

                // characterEntity.setCoordinatesBeforeCollisionResolution();

                // translate back a step
                characterEntity.translate(delta * oppositeXVelocity, delta * oppositeYVelocity);

                // try to move Y;
                characterEntity.translate(0, delta * oppositeYVelocity * -1);

                if (Intersector.intersectRectangles(characterRect, r, tmpRectangle)) {

                    if (tmpRectangle.height < tmpRectangle.width) {
                        characterEntity.translate(0, (tmpRectangle.getHeight()) * Math.signum(oppositeYVelocity));
                        characterEntity.yVelocity = 0;
                    }
                }

                // try to move X;
                characterEntity.translate(delta * oppositeXVelocity * -1, 0);
                if (Intersector.intersectRectangles(characterRect, r, tmpRectangle)) {

                    if (tmpRectangle.width < tmpRectangle.height) {
                        characterEntity.translate((tmpRectangle.getWidth()) * Math.signum(oppositeXVelocity), 0);
                        characterEntity.xVelocity = 0;
                    }
                }

            }

        }
    }

}