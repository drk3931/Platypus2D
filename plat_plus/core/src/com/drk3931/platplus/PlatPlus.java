package com.drk3931.platplus;

import com.badlogic.gdx.ApplicationAdapter;
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

	enum GameState {
		INITIAL, GAME_RUNNING, GAME_OVER
	}

	private static GameState currentState = GameState.INITIAL;

	public void setGameState(GameState state) {
		currentState = state;
		if(state == GameState.GAME_RUNNING){
			loadWorld();
		}
		if(state == GameState.GAME_OVER){
			uiHandler.onGameOver();
		}
	}

	public static GameState getGameState() {
		return currentState;
	}

	public void loadWorld() {
		world = gameLoader.loadWorld(map);
		collisionHandler.setWorld(world,uiHandler);
		renderer.setWorld(world);

	}

	@Override
	public void create() {

		gameLoader = new GameLoader();
		map = gameLoader.loadMap();

		renderer = new Renderer(map);

		collisionHandler = new CollisionHandler(map);

		uiHandler = new UIHandler(this);

		clearColor = new Color(Color.SKY);

	}

	float max = 1/30f;

	@Override
	public void render() {

		float delta = MathUtils.clamp(Gdx.graphics.getDeltaTime(),0,max);

		if (world != null) {
			world.update(delta);
			if (world.gameOver()) {
				setGameState(GameState.GAME_OVER);
			}
			if (!world.gameOver()) {
				collisionHandler.update(delta);

			}
			world.getPlayer().applyToCam(renderer.camera);
		}
		uiHandler.update(delta);

		Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, 1.0f);
		// gl.glClearColor(Math.random(), Math.random(), Math.random(), Math.random());
		

		renderer.draw();
		uiHandler.draw();

	}

	@Override
	public void dispose() {

	}

}
