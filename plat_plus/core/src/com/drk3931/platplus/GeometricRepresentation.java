package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;

public class GeometricRepresentation  {

    public Shape2D shapeRepresentation;
    private Color color;

    public GeometricRepresentation(Color c, Shape2D shape) {
        this.shapeRepresentation = shape;
        this.color = c;
    }

    public void setShape(Shape2D shape) {
        shapeRepresentation = shape;
    }

    public Shape2D getShape() {
        return this.shapeRepresentation;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public Color getColor() {
        return this.color;
    }

    public void setPosition(int x, int y) {
        if (shapeRepresentation instanceof Rectangle) {
            Rectangle asRect = (Rectangle) shapeRepresentation;
            asRect.x = x;
            asRect.y = y;
            return;
        }

        if (shapeRepresentation instanceof Circle) {
            Circle asCircle = (Circle) shapeRepresentation;
            asCircle.x = x;
            asCircle.y = y;
            return;
        }
    }

    public void setRadius(int rad) throws Exception {
        if (shapeRepresentation instanceof Circle) {
            ((Circle) shapeRepresentation).radius = rad;
        } else {
            throw new Exception("Not a circle!");
        }
    }

    public void setWH(int w, int h) throws Exception {
        if (shapeRepresentation instanceof Circle) {
            Rectangle asRect = (Rectangle) shapeRepresentation;
            asRect.setSize(w, h);
        } else {
            throw new Exception("Not a rectangle!");
        }

    }

    public void translate(float dx, float dy) {

        if (shapeRepresentation instanceof Rectangle) {
            Rectangle asRect = (Rectangle) shapeRepresentation;
            asRect.x += dx;
            asRect.y += dy;

        }

        if (shapeRepresentation instanceof Circle) {
            Circle asCircle = (Circle) shapeRepresentation;
            asCircle.x += dx;
            asCircle.y += dy;
        }

    }

    public int getCenterX(){
        return (int) (((Rectangle) shapeRepresentation).getX() + ((Rectangle) shapeRepresentation).getWidth());

    }

    public int getCenterY(){
        return (int) (((Rectangle) shapeRepresentation).getY() + ((Rectangle) shapeRepresentation).getHeight());

    }


    public int getX() {

        if (shapeRepresentation instanceof Rectangle) {
            return (int) ((Rectangle) shapeRepresentation).getX();
        } else if (shapeRepresentation instanceof Circle) {
            return (int) ((Circle) shapeRepresentation).x;
        } else {
            return -1;
        }
    }

    public int getY() {

        if (shapeRepresentation instanceof Rectangle) {
            return (int) ((Rectangle) shapeRepresentation).getY();
        } else if (shapeRepresentation instanceof Circle) {
            return (int) ((Circle) shapeRepresentation).y;
        } else {
            return -1;
        }

    }

    public int getWidth() {
        return (int) ((Rectangle) this.shapeRepresentation).width;
    }

    public int getHeight() {
        return (int) ((Rectangle) this.shapeRepresentation).height;

    }

    public int getRadius() {
        return (int) ((Circle) this.shapeRepresentation).radius;

    }

    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {
        
        GeometricRepresentation geoRep = this;


        if(geoRep.shapeRepresentation instanceof Rectangle)
        {

            Rectangle r = (Rectangle) geoRep.shapeRepresentation;
            shapeRenderer.setColor(geoRep.getColor());
            shapeRenderer.set(ShapeType.Filled);
            shapeRenderer.rect(r.x, r.y, r.width, r.height);

        }

        if(geoRep.shapeRepresentation instanceof Circle)
        {

            Circle r = (Circle) geoRep.shapeRepresentation;
            shapeRenderer.setColor(geoRep.getColor());
            shapeRenderer.set(ShapeType.Filled);
            shapeRenderer.circle(r.x, r.y, r.radius);

        }



    }

    
    

}
