package com.drk3931.platplus;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.physics.box2d.Shape;

class CollisionHandler implements DrawableComponent {

    private World world;
    private Map map;

    public void draw(Renderer r) {
        r.shapeRenderer.set(ShapeType.Line);
        r.shapeRenderer.setColor(Color.RED);
        for (int i = 0; i < broadPhase.length; i++) {
            Rectangle re = (Rectangle) broadPhase[i];

            r.shapeRenderer.rect(re.getX(), re.getY(), re.getWidth(), re.getHeight());
        }

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

        Player player = world.getPlayer();

        Rectangle playerRect = player.characterEntity.rectangleRepresentation;

        int xStart = (int) (playerRect.getX() - (playerRect.getX() % map.CELL_W));
        xStart -= map.CELL_W;
        int xEnd = xStart + (map.CELL_W * 3);

        int yStart = (int) (playerRect.getY() - (playerRect.getY() % map.CELL_H));
        yStart -= map.CELL_H;
        int yEnd = yStart + (map.CELL_H * 3);

        int rectCounter = 0;

        for (int x = xStart; x < xEnd; x += map.CELL_W) {

            for (int y = yStart; y < yEnd; y += map.CELL_H) {

                Cell c = map.getCell("Terrain", (int) x / map.CELL_W, (int) y / map.CELL_H);

                if (c != null) {
                    ((Rectangle) broadPhase[rectCounter]).set(x, y, map.CELL_W, map.CELL_H);

                } else {
                    ((Rectangle) broadPhase[rectCounter]).set(0, 0, 0, 0);
                }

                rectCounter++;

            }

        }

        narrowPhase(player, delta);

    }

    private void narrowPhase(Player player, float delta) {

        for (int i = 0; i < broadPhase.length; i++) {
            Rectangle r = (Rectangle) broadPhase[i];
            Rectangle playerRect = player.characterEntity.rectangleRepresentation;

            if (playerRect.overlaps(r)) {

                int oppositeXVelocity = player.characterEntity.xVelocity * -1;
                int oppositeYVelocity = player.characterEntity.yVelocity * -1;

                // translate back a step
                player.characterEntity.translate(delta * oppositeXVelocity, delta * oppositeYVelocity);

                // try to move Y;
                player.characterEntity.translate(0, delta * oppositeYVelocity * -1);
                if (Intersector.intersectRectangles(playerRect, r, tmpRectangle)) {
                    player.characterEntity.translate(0, tmpRectangle.getHeight() * Math.signum(oppositeYVelocity));
                    player.characterEntity.yVelocity = 0;

                }
                // try to move X;
                player.characterEntity.translate(delta * oppositeXVelocity * -1, 0);
                if (Intersector.intersectRectangles(playerRect, r, tmpRectangle)) {
                    player.characterEntity.translate(tmpRectangle.getWidth() * Math.signum(oppositeXVelocity), 0);
                    player.characterEntity.xVelocity = 0;
                }

            }

        }
    }

}