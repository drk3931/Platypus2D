package com.drk3931.platplus;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public interface DrawableComponent{
    public void drawShapeRenderer(ShapeRenderer r);
    public void drawSpriteBatch(SpriteBatch b);
}