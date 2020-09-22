package com.drk3931.platplus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

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
	StretchViewport svp;

	final static int VIRTUAL_HEIGHT = 600;
	final static int VIRTUAL_WIDTH = 1024; 


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

		svp = new StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT,renderer.camera);


		uiHandler = new UIHandler(this);
		collisionHandler = new CollisionHandler(this);

		clearColor = new Color(Color.SKY);

	}


	float time = 0;
	final float tick = 1 / 60f,max=1/300f;
	int maxUpdatesPerFrame = 5;
	
	@Override
	public void pause(){
		
	}


	@Override
	public void render() {

		float delta = Gdx.graphics.getDeltaTime();

		if(delta > 1.0f){
			return;
		}
	

		Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, 1.0f);

		renderer.draw();
		uiHandler.draw();

		time += delta;
		int updatesThisFrame = 0;

		while (time >= tick && updatesThisFrame < maxUpdatesPerFrame) {

			if (currentState == GameState.GAME_RUNNING) {
				world.update(tick);
				collisionHandler.update();
				world.getPlayer().applyToCam(renderer.camera);
			}
			uiHandler.update(tick);
			
	
			updatesThisFrame++;
			time -= tick;


		}

		


		




	}

	@Override
	public void resize(int width, int height) {
		svp.update(width, height);
		uiHandler.runningStage.getViewport().update(width, height);
		uiHandler.runningStage.getViewport().update(width, height);

	}

	@Override
	public void dispose() {

	}

}
