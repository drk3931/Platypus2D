package com.drk3931.platplus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;


public class PlatPlus extends ApplicationAdapter {


	Renderer renderer;
	World world;
	Map map;
	CollisionHandler collisionHandler;
    GameLoader gameLoader;

	@Override
	public void create () {

		gameLoader = new GameLoader();

		world= gameLoader.getLoadedWorld();
		map = gameLoader.getLoadedMap();

		
		collisionHandler = new  CollisionHandler(map, world);
		renderer = new Renderer(map,world,collisionHandler);

	}

	@Override
	public void render() {
		float delta = Gdx.graphics.getDeltaTime();
		world.update(delta);
		collisionHandler.update(delta);
		renderer.draw();
	}
	
	@Override
	public void dispose () {
		
	}
}
