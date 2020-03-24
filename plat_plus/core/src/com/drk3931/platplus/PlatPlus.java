package com.drk3931.platplus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;


public class PlatPlus extends ApplicationAdapter {


	Map map;
	GameLoader gameLoader;
	Renderer renderer;


	/*
    GameLoader gameLoader;
	*/

	World world;
	CollisionHandler collisionHandler;
	UIHandler uiHandler;


	enum GameState {
        INITIAL, GAME_RUNNING, GAME_OVER
    }

	private static GameState currentState = GameState.INITIAL;
	
	public static void setGameState(GameState state){
	currentState = state;
	}

	public static GameState getGameState(){
		return currentState;
	}



	@Override
	public void create () {


		gameLoader = new GameLoader();
		map = gameLoader.loadMap();
		renderer = new Renderer(map);
		world= gameLoader.loadWorld(map);
		collisionHandler = new CollisionHandler(map, world);

		renderer.addDrawableComponent(world);
		uiHandler = new UIHandler();



	}

	@Override
	public void render() {

		
		float delta = Gdx.graphics.getDeltaTime();
		float timeLimitMin = (float)1/30;

		if(delta > timeLimitMin)
		{
			return;
		}
		//uiHandler.update(delta);
		world.update(delta);
		collisionHandler.update(delta);
		world.getPlayer().applyToCam(renderer.camera);

		Gdx.gl.glClearColor(0, 0, 0, 1.0f);
        //gl.glClearColor(Math.random(), Math.random(), Math.random(), Math.random());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		uiHandler.draw();
		renderer.draw();
		


	}
	
	@Override
	public void dispose () {
		
	}
}
