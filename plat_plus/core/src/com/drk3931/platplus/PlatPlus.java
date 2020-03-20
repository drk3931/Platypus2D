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
		world= gameLoader.loadWorld(map);
		collisionHandler = new CollisionHandler(map, world);

		renderer.addDrawableComponent(world);



	}

	@Override
	public void render() {

		
		float delta = Gdx.graphics.getDeltaTime();
		float timeLimitMin = (float)1/30;

		if(delta > timeLimitMin)
		{
			return;
		}
		world.update(delta);
		collisionHandler.update(delta);
		world.getPlayer().applyToCam(renderer.camera);
		renderer.draw();


	}
	
	@Override
	public void dispose () {
		
	}
}
