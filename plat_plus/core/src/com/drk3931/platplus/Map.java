package com.drk3931.platplus;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.drk3931.platplus.behaviors.AirPatrolBehavior;
import com.drk3931.platplus.behaviors.GroundPatrolBehavior;

class Map implements DrawableComponent{

    private ArrayList<Shape2D> mapPolies;
    private TiledMap tiledMap;
    public final int CELL_W, CELL_H,MAP_H,MAP_W;


    public Cell getCell(String layer, int x, int y)
    {
        return ((TiledMapTileLayer) tiledMap.getLayers().get(layer)).getCell(x, y);
    }

    public TiledMap getTiledMap() {
        return this.tiledMap;
    }

    public ArrayList<Shape2D> getMapPolies() {
        return this.mapPolies;
    }

    World world;

    public Map(String mapFileName) {

        mapPolies = new ArrayList<Shape2D>();
        tiledMap = new TmxMapLoader().load(mapFileName);
        TiledMapTileLayer terrainTileLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Terrain");

        CELL_W = (int) terrainTileLayer.getTileWidth();
        CELL_H = (int) terrainTileLayer.getTileHeight();

        MAP_H = (int) terrainTileLayer.getHeight();
        MAP_W = (int) terrainTileLayer.getWidth();


        Gdx.app.log("Terrain Cell Width", ""+CELL_W);
        Gdx.app.log("Terrain Cell Height", ""+CELL_H);

        Gdx.app.log("Terrain Layer Width", ""+terrainTileLayer.getWidth());
        Gdx.app.log("Terrain Layer Height", ""+terrainTileLayer.getHeight());

        this.world = world;



        for (int i = 0; i < terrainTileLayer.getWidth(); i++) {
            for (int j = 0; j < terrainTileLayer.getHeight(); j++) {

                Cell cell = terrainTileLayer.getCell(i, j);

                if (cell != null) {
                    
                    MapProperties tileProps = cell.getTile().getProperties();
                    if(tileProps.containsKey("SLOPE"))
                    {
                        String slopeType = (String)tileProps.get("SLOPE");
                        if(slopeType.equals("UP"))
                        {
                            mapPolies.add(genLineSeg(i * CELL_W, j * CELL_H, i * CELL_W + CELL_W, j * CELL_H + CELL_H));
                            
                        }else{
                            mapPolies.add(genLineSeg(i * CELL_W, j * CELL_H + CELL_H, i * CELL_W + CELL_W, j * CELL_H));
                        }

                    }
                    else{
                        mapPolies.add(genRect(i * CELL_W, j * CELL_H, CELL_W, CELL_H));
                    }
                }
            }
        }




      

    }


    public void parseCharactersLayer(World world)
    {
        MapLayer characterLayer = tiledMap.getLayers().get("Characters");
        MapObjects objects = characterLayer.getObjects();

        
        JsonReader json = new JsonReader();
        JsonValue characters = json.parse(Gdx.files.internal("characters.json"));


        

        MapProperties playerProps = objects.get("player").getProperties();
    
        float playerX = Float.parseFloat(playerProps.get("x").toString());
        float playerY = Float.parseFloat(playerProps.get("y").toString());
        world.spawnPlayer(playerX,playerY);
        
        Iterator<MapObject> objectsIter = objects.iterator();


        while(objectsIter.hasNext())
        {


            MapObject obj = (MapObject)objectsIter.next();
            MapProperties objProps = obj.getProperties();


            float characterX = Float.parseFloat(objProps.get("x").toString());
            float characterY = Float.parseFloat(objProps.get("y").toString());


            String characterType = objProps.get("character_name", "none", String.class);
            System.out.println(characterType);

            Character newCharacter;

            if(characterType.equals("enemyGround"))
            {
                
                JsonValue enemyGround = characters.get("enemies").get("enemyGround");

                CharacterState eState = new CharacterState();

                newCharacter = new Enemy(world.getPlayer(),eState);

                eState.setHealth(enemyGround.getInt("health"));
                eState.setBehavior(new GroundPatrolBehavior(newCharacter));
                newCharacter.setEntityRep(characterX,characterY,(float)Integer.parseInt(enemyGround.get("width").asString()),(float)Integer.parseInt(enemyGround.get("height").asString()));
                world.addCharacter(newCharacter);

            }

            if(characterType.equals("enemyAir"))
            {
                JsonValue enemyAir = characters.get("enemies").get("enemyAir");
                CharacterState eState = new CharacterState();
                newCharacter = new Enemy(world.getPlayer(),eState);
                newCharacter.setEntityRep(characterX,characterY,(float)Integer.parseInt(enemyAir.get("width").asString()),(float)Integer.parseInt(enemyAir.get("height").asString()));

                eState.setHealth(enemyAir.getInt("health"));
                eState.setBehavior(new AirPatrolBehavior(newCharacter));
                world.addCharacter(newCharacter);

            }

            
            //Character c = new Character(world.getPlayer());
            //c.setupCharacter(enemyX,enemyY,64.0f,64.0f);

        
        }
    }

    public Shape2D genRect(int x, int y, int w, int h) {

        return new Rectangle(x, y, w, h);

        /*
         * return new Polygon(new float[]{ x,y,x,y+h,x+w,y+h,x+w,y });
         */
    }

    public Shape2D genCircle(int x, int y, int radius) {

        return new Circle(x, y, radius);

    }

    public Shape2D genLineSeg(int x1, int y1, int x2, int y2) {
        return new Polyline(new float[] { x1, y1, x2, y2 });
    }


    @Override
    public void drawShapeRenderer(ShapeRenderer shapeRenderer){

        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.set(ShapeType.Line);
        for (int i = 0; i < mapPolies.size(); i++) {

            Shape2D s = mapPolies.get(i);
            if (s == null) {
                return;
            }
            if (s.getClass() == Rectangle.class) {
                Rectangle r = (Rectangle) s;
                shapeRenderer.rect(r.x, r.y, r.width, r.height);

            }
            if (s.getClass() == Circle.class) {
                Circle c = (Circle) s;
                shapeRenderer.circle(c.x, c.y, c.radius);

            }

            if (s.getClass() == Polyline.class) {
                Polyline p = (Polyline)s;
                shapeRenderer.polyline(p.getVertices());

            }

        }

    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {

	}

}