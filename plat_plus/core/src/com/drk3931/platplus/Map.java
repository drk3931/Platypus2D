package com.drk3931.platplus;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
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
import com.drk3931.platplus.behaviors.Behavior;
import com.drk3931.platplus.behaviors.GroundPatrolBehavior;
import com.drk3931.platplus.projectiles.PlayerProjectile;

class Map implements DrawableComponent {

    private ArrayList<Shape2D> mapPolies;
    private TiledMap tiledMap;
    public final int CELL_W, CELL_H, MAP_H, MAP_W;

    public Cell getCell(String layer, int x, int y) {
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

        Gdx.app.log("Terrain Cell Width", "" + CELL_W);
        Gdx.app.log("Terrain Cell Height", "" + CELL_H);

        Gdx.app.log("Terrain Layer Width", "" + terrainTileLayer.getWidth());
        Gdx.app.log("Terrain Layer Height", "" + terrainTileLayer.getHeight());

        for (int i = 0; i < terrainTileLayer.getWidth(); i++) {
            for (int j = 0; j < terrainTileLayer.getHeight(); j++) {

                Cell cell = terrainTileLayer.getCell(i, j);

                if (cell != null) {

                    MapProperties tileProps = cell.getTile().getProperties();
                    if (tileProps.containsKey("SLOPE")) {
                        String slopeType = (String) tileProps.get("SLOPE");
                        if (slopeType.equals("UP")) {
                            mapPolies.add(genLineSeg(i * CELL_W, j * CELL_H, i * CELL_W + CELL_W, j * CELL_H + CELL_H));

                        } else {
                            mapPolies.add(genLineSeg(i * CELL_W, j * CELL_H + CELL_H, i * CELL_W + CELL_W, j * CELL_H));
                        }

                    } else {
                        mapPolies.add(genRect(i * CELL_W, j * CELL_H, CELL_W, CELL_H));
                    }
                }
            }
        }

    }

    public void parseCharactersLayer(World world) {

        MapLayer characterLayer = tiledMap.getLayers().get("Characters");
        JsonReader jsonReader = new JsonReader();
        JsonValue characters = jsonReader.parse(Gdx.files.internal("characters.json"));
        JsonValue itemsData = jsonReader.parse(Gdx.files.internal("items.json"));
        JsonValue weapons = itemsData.get("weapons");

        // create player from map, not json
        MapProperties playerProps = characterLayer.getObjects().get("player").getProperties();
        float playerX = Float.parseFloat(playerProps.get("x").toString());
        float playerY = Float.parseFloat(playerProps.get("y").toString());
        world.spawnPlayer(playerX, playerY);
        PlayerProjectile.setDamage(weapons.get("projectilePlayer").getInt("damage"));

        // standard death animation
        Animation<TextureRegion> deathAnimation = GameLoader.genAnimation("poof.png", 6, 5, 0.10f);
        deathAnimation.setPlayMode(PlayMode.NORMAL);


        // parsing all enemies
        MapObjects characterObjects = characterLayer.getObjects();
        Iterator<MapObject> characterObjectsIterator = characterObjects.iterator();

        while (characterObjectsIterator.hasNext()) {

            RectangleMapObject currentObj = (RectangleMapObject) characterObjectsIterator.next();

            MapProperties characterProperties = currentObj.getProperties();
            Rectangle charRect = currentObj.getRectangle();
            float characterX = charRect.x, characterY = charRect.y, characterW = charRect.width,characterH = charRect.height;
            String characterType = characterProperties.get("character_type", "NO_TYPE_FOUND", String.class);
            Player playerRef = world.getPlayer();

            if (!characterType.equals("NO_TYPE_FOUND")) {

                


                JsonValue jsonCharacterData = characters.get("enemies").get(characterType);

                if(jsonCharacterData == null){
                    Gdx.app.log("Error", "Unknown Character Data " + characterType);
                }
                else{
                    Gdx.app.log("Info", "Adding character of type " + characterType + " Coordinates: " + characterX + ' ' + characterY + ' ' + characterW + ' ' + characterH);
                }

                Character newCharacter = new Enemy(playerRef);
                newCharacter.setEntityRep(characterX, characterY, characterW, characterH);

                newCharacter.characterState.setHealth(jsonCharacterData.getInt("health"));
                newCharacter.characterState.setDeathAnimation(deathAnimation);

                JsonValue defaultAnimation = jsonCharacterData.get("defaultAnimation");

                newCharacter.characterState.setDefaultAnimation(GameLoader.genAnimationAtlas(defaultAnimation.getString("file"), Animation.PlayMode.LOOP, defaultAnimation.getFloat("timing")));

                String behavior = jsonCharacterData.getString("behavior");
                Behavior characterBehavior = null;

                if (behavior.equals("GroundPatrolBehavior")) {

                    characterBehavior = new GroundPatrolBehavior(newCharacter);
                } else if (behavior.equals("AirPatrolBehavior")) {
                    characterBehavior = new AirPatrolBehavior(newCharacter);
                }

                newCharacter.characterState.setBehavior(characterBehavior);

                world.addCharacter(newCharacter);

            }

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
    public void drawShapeRenderer(ShapeRenderer shapeRenderer) {

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
                Polyline p = (Polyline) s;
                shapeRenderer.polyline(p.getVertices());

            }

        }

    }

    @Override
    public void drawSpriteBatch(SpriteBatch b) {

    }

}