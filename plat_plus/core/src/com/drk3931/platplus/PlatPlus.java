package com.drk3931.platplus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;


public class PlatPlus extends ApplicationAdapter {


	Map map;
	GameLoader gameLoader;
	Renderer renderer;


	/*
    GameLoader gameLoader;
	*/

	World world;
	CollisionHandler collisionHandler;




	@Override
	public void create () {


		gameLoader = new GameLoader();
		map = gameLoader.loadMap();
		renderer = new Renderer(map);
		world= gameLoader.loadWorld();
		collisionHandler = new CollisionHandler(map, world);

		renderer.addDrawableComponent(world);

		/*


		collisionHandler = new CollisionHandler(map, world);
		renderer = new Renderer(map,world,collisionHandler);
		*/

	}

	@Override
	public void render() {
		/*
		world.update(delta);
		collisionHandler.update(delta);
		*/
		float delta = Gdx.graphics.getDeltaTime();
		world.update(delta);
		collisionHandler.update(delta);
		world.getPlayer().applyToCam(renderer.camera);
		renderer.draw();


	}
	
	@Override
	public void dispose () {
		
	}
}
