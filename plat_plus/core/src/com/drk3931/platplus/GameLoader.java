package com.drk3931.platplus;
import com.drk3931.platplus.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class GameLoader {

    private Map gameMap;

    public GameLoader(){

      
    }

    public static TextureRegion getTexRegion(String name)
    {
        return new TextureRegion(new Texture(Gdx.files.internal(name)));
    }

    public Map loadMap()
    {

        gameMap = new Map("plat_plus_l1.tmx");
        return gameMap;
        
        /*
            parse characters from map and put them in the world
        */

      
        
    }

    public World loadWorld(Map map)
    {
        World world = new World();
        map.parseCharactersLayer(world);
        return world;
    }



    public static Animation<TextureRegion> genAnimation(String fileName, int FRAME_ROWS,int FRAME_COLS){


        Texture animationSheet = new Texture(Gdx.files.internal(fileName));

        TextureRegion[][] tmp = TextureRegion.split(animationSheet, 
        animationSheet.getWidth() / FRAME_COLS,
        animationSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				frames[index++] = tmp[i][j];
			}
        }
        
        return new Animation<TextureRegion>(0.025f, frames);

    }


}