package com.drk3931.platplus;
import com.drk3931.platplus.Map;

import java.util.Arrays;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;



public class GameLoader {

    private Map gameMap;

    public GameLoader(){

      
    }

    public static TextureRegion getTexRegion(String fname)
    {
        return new TextureRegion(new Texture(Gdx.files.internal(fname)));
    }


    public static TextureAtlas genAtlas(String path){
        return new TextureAtlas(Gdx.files.internal(path));
    }


    public Map loadMap()
    {

        gameMap = new Map("plat_plus_l1.tmx");
        return gameMap;
        
        /*
            parse characters from map and put them in the world
        */

      
        
    }

    public World loadWorld(PlatPlus gameRef) 
    {
        World world = new World(gameRef);
        return world;
    }


    public static Animation<TextureRegion> genAnimation(String fileName, int FRAME_COLS,int FRAME_ROWS,float timing){


        Texture animationSheet = new Texture(Gdx.files.internal(fileName));

        TextureRegion[][] tmp = TextureRegion.split(animationSheet, 
        animationSheet.getWidth() / FRAME_COLS,
        animationSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {

                if(FLIP_TEXTURE_ON_GENERATE){
                    tmp[i][j].flip(true, false);
                }

				frames[index++] = tmp[i][j];
			}
        }
        
        return new Animation<TextureRegion>(timing, frames);

    }



    public static Animation<TextureRegion> genAnimationRange(String fileName, int FRAME_COLS,int FRAME_ROWS,float timing, int row, int numFrames){


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

        int start = row *FRAME_COLS;
        frames = Arrays.copyOfRange(frames, start, start + numFrames );
        
        return new Animation<TextureRegion>(timing, frames);

    }



    public static boolean FLIP_TEXTURE_ON_GENERATE = false;


    public static Animation<TextureRegion> genAnimationAtlas(String fileName, PlayMode playMode, float timing){


        TextureAtlas atlas = new TextureAtlas(fileName);
      


    
        return new Animation<TextureRegion>(timing, atlas.getRegions(), PlayMode.LOOP);

    }


}