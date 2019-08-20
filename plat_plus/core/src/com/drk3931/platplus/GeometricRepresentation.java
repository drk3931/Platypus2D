package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Shape2D;

class GeometricRepresentation {

    public Shape2D shapeRepresentation;
    public Color color;


    public GeometricRepresentation(Color c, Shape2D shape) {
        this.shapeRepresentation = shape;
        this.color = c;
    }

    public GeometricRepresentation() {
       
    }
    

}
