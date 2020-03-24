package com.drk3931.platplus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
		world = gameLoader.loadWorld(mapRef);
		collisionHandler.setWorld(world);
		renderer.addDrawableComponent(world);

	}

	private Map mapRef;

	@Override
	public void create() {

		gameLoader = new GameLoader();
		map = gameLoader.loadMap();
		this.mapRef = map;

		renderer = new Renderer(map);

		collisionHandler = new CollisionHandler(map);

		uiHandler = new UIHandler(this);

	}

	@Override
	public void render() {

		float delta = Gdx.graphics.getDeltaTime();
		float timeLimitMin = (float) 1 / 30;

		if (delta > timeLimitMin) {
			return;
		}
		uiHandler.update(delta);

		if (world != null) {
			world.update(delta);
			if (world.gameOver()) {
				currentState = GameState.GAME_OVER;
			}
			collisionHandler.update(delta);
			world.getPlayer().applyToCam(renderer.camera);


		}

		


		Gdx.gl.glClearColor(0, 0, 0, 1.0f);
		// gl.glClearColor(Math.random(), Math.random(), Math.random(), Math.random());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		uiHandler.draw();
		renderer.draw();

	}

	@Override
	public void dispose() {

	}
}
