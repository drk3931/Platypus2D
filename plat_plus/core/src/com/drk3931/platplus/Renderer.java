package com.drk3931.platplus;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

class Renderer {

    public OrthographicCamera camera;
    public ShapeRenderer shapeRenderer;
    public SpriteBatch spriteBatch;
    private TiledMapRenderer tiledMapRenderer;

    private Map map;
    private World world;
    private TextureRegion backdropRef;

    public Renderer(Map map) {

        //map needs to be provided in the constructor to setup the tiledMaprenderer
        this.map = map;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        spriteBatch = new SpriteBatch();

        camera = new OrthographicCamera(PlatPlus.VIRTUAL_WIDTH,PlatPlus.VIRTUAL_HEIGHT);
        camera.setToOrtho(false);
        
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap());

        

        backdropRef = GameLoader.getTexRegion("landscape.png");


    

       



    }

    public void setWorld(World world){
        this.world = world;
    }




    



    public void draw() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if(world != null){
            Entity e = world.getPlayer().e;
            if (e.getGeoRep().getX() > PlatPlus.VIRTUAL_WIDTH / 2) {
                camera.position.x = e.getGeoRep().getX();
            } else {
                camera.position.x =PlatPlus.VIRTUAL_WIDTH / 2;
            }
    
            if (e.getGeoRep().getY() > PlatPlus.VIRTUAL_HEIGHT / 2) {
                camera.position.y = e.getGeoRep().getY();
            } else {
                camera.position.y = PlatPlus.VIRTUAL_HEIGHT/ 2;
            }
        }
     
  

        camera.update();
        

        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);


        Color lastTint = spriteBatch.getColor();


        spriteBatch.setColor(Color.WHITE);
        spriteBatch.begin();

        for(int i = 0; i < 10; i++){

            
            spriteBatch.draw(backdropRef,i * PlatPlus.VIRTUAL_WIDTH, 0, PlatPlus.VIRTUAL_WIDTH,PlatPlus.VIRTUAL_HEIGHT*2); 

        }
        
    


        spriteBatch.end();

        spriteBatch.setColor(lastTint);

        shapeRenderer.begin();

        
        //this.map.drawShapeRenderer(shapeRenderer);

        if(this.world != null){
            this.world.drawShapeRenderer(shapeRenderer);

        }
        
        
        shapeRenderer.end();


        tiledMapRenderer.setView(camera);

        tiledMapRenderer.render();


        spriteBatch.begin();


        this.map.drawSpriteBatch(spriteBatch);
        
        if(this.world != null){   
            
           
            this.world.drawSpriteBatch(spriteBatch);

        
            


        }

    

        spriteBatch.end();

    }

 
    public void resize(int w, int h){

        camera.setToOrtho(false, PlatPlus.VIRTUAL_HEIGHT * w/(float)(PlatPlus.VIRTUAL_WIDTH), h);

    }

}