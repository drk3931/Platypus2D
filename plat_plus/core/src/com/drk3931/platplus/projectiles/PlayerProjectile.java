package com.drk3931.platplus.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.drk3931.platplus.Character;
import com.drk3931.platplus.DrawableComponent;
import com.drk3931.platplus.Enemy;
import com.drk3931.platplus.Entity;
import com.drk3931.platplus.GeometricRepresentation;
import com.drk3931.platplus.Player;
import com.drk3931.platplus.Updateable;

public class PlayerProjectile extends Projectile {

    private static int damage;

    public PlayerProjectile(Player p, float x, float y) {
        super(p.e, Color.BLUE, x,y);
    }

    @Override
    public void onHit(Character c)
    {
      
            ((Enemy)c).characterState.modifyHealth(damage);

        
    }


    public static void setDamage(int d){
        damage = d; 
    }

}