package com.drk3931.platplus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

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

	public static void setGameState(GameState state) {
		currentState = state;
	}

	public static GameState getGameState() {
		return currentState;
	}

	public void loadWorld() {
		world = gameLoader.loadWorld(map);
		collisionHandler.setWorld(world);
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

	@Override
	public void render() {





		float delta = Gdx.graphics.getDeltaTime();
		float timeLimitMin = (float) 1 / 30;

		if (delta > timeLimitMin) {
			return;
		}

		if (world != null) {
			world.update(delta);
			if (world.gameOver()) {
				currentState = GameState.GAME_OVER;
			}
			collisionHandler.update(delta);
			world.getPlayer().applyToCam(renderer.camera);


		}
		uiHandler.update(delta);


		Gdx.gl.glClearColor(clearColor.r,clearColor.g,clearColor.b, 1.0f);
		// gl.glClearColor(Math.random(), Math.random(), Math.random(), Math.random());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		
		renderer.draw();
		uiHandler.draw();


	}

	@Override
	public void dispose() {

	}
}
