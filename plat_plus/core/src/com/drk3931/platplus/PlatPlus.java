package com.drk3931.platplus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

		renderer = new Renderer(map.getTiledMap());

		
		collisionHandler = new  CollisionHandler(map.getMapPolies(), world.worldCharacters);
		
	}

	@Override
	public void render() {
		float delta = Gdx.graphics.getDeltaTime();
		world.update(delta);
		collisionHandler.update(delta);
		renderer.draw(map.getMapPolies(), world.worldCharacters);
	}
	
	@Override
	public void dispose () {
		
	}
}
