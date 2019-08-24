package com.drk3931.platplus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Entity implements DrawableComponent {

    Color bulletColor = Color.WHITE;
    public Circle circleRep;
    private ProjectileStats projectileStats;
    private ProjectileWeapon firedFrom; 
  

    public Projectile(ProjectileWeapon firedFrom) {
        
        geometricRepresentation.setColor(bulletColor);
        setIdentity(Identity.PROJECTILE);
        this.firedFrom = firedFrom;
        this.projectileStats = new ProjectileStats();
        loadStats(this.projectileStats);

    
    }
    
    public void setTrajectory(int xStart, int yStart, int xDir, int yDir)
    {

    
        Vector2 vel = new Vector2(xDir,yDir).nor().setLength(projectileStats.velScale);
        circleRep = new Circle(xStart,yStart,projectileStats.radius);
        geometricRepresentation.setShape(circleRep);
        geometricRepresentation.setColor(bulletColor);
        setVelocity(vel.x, vel.y);
        setIdentity(Identity.PROJECTILE);

        

    }

	@Override
    public void onCollision(Entity e) {
        
        if(e.getIdentity() == Identity.ENEMY && this.firedFrom.boundEntity.getIdentity() == Identity.PLAYER)
        {
            CharacterStats eStat = (CharacterStats)e.entityStats;
            eStat.subHealth(this.projectileStats.damage);
        }

        if(e.getIdentity() == Identity.PLAYER && this.firedFrom.boundEntity.getIdentity() == Identity.ENEMY)
        {
            System.out.println("nou");
            CharacterStats pStat = (CharacterStats)e.entityStats;
            pStat.subHealth(this.projectileStats.damage);
        }
    }


    @Override
    public void update(float delta)
    {

  
        move(delta);


    }

    @Override
    public void drawShapeRenderer(ShapeRenderer r) {
        Circle cRep = (Circle)geometricRepresentation.shapeRepresentation;
        r.set(ShapeType.Filled);
        r.circle(cRep.x, cRep.y, cRep.radius);


   
    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {
		
	}

   
}

























