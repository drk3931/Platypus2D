package com.drk3931.platplus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;

public class PlatPlus extends ApplicationAdapter {

	Map map;
	GameLoader gameLoader;
	Renderer renderer;

	/*
	 * GameLoader gameLoader;
	 */

	World world;
	CollisionHandler collisionHandler;
	UIHandler uiHandler;
	Color clearColor;

	final float timeStep = 1 / 120f;

	enum GameState {
		INITIAL, GAME_RUNNING, GAME_OVER, GAME_WON
	}

	private static GameState currentState = GameState.INITIAL;

	public void setGameState(GameState state) {
		currentState = state;
		if (state == GameState.GAME_RUNNING) {
			loadWorld();
		}
		if (state == GameState.GAME_OVER) {

			uiHandler.onGameOver();
		}
		if (state == GameState.GAME_WON) {
			uiHandler.onGameWon();
		}
	}

	public static GameState getGameState() {
		return currentState;
	}

	public void loadWorld() {
		this.world = gameLoader.loadWorld(this);

		map.parseLevelsLayer(this);
		map.parseCharactersLayer(this);
		collisionHandler.setWorld(world);
		renderer.setWorld(world);

	}

	@Override
	public void create() {

		gameLoader = new GameLoader();
		map = gameLoader.loadMap();

		renderer = new Renderer(map);

		uiHandler = new UIHandler(this);
		collisionHandler = new CollisionHandler(this);

		clearColor = new Color(Color.SKY);

	}


	float buildup = 0;

	@Override
	public void render() {

	

		Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, 1.0f);

		renderer.draw();
		uiHandler.draw();


		if(buildup < timeStep){
			
			buildup+=Gdx.graphics.getDeltaTime();
			return;

		}
		else{

			if (currentState == GameState.GAME_RUNNING) {
				world.update(timeStep);
	
				collisionHandler.update(timeStep);
				world.getPlayer().applyToCam(renderer.camera);
			}
			uiHandler.update(timeStep);
			buildup = 0; 
			
		}




	}

	@Override
	public void dispose() {

	}

}
