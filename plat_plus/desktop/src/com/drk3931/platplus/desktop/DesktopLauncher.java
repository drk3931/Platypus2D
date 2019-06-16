package com.drk3931.platplus.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.drk3931.platplus.PlatPlus;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="Plat Plus";
		config.width = 640;
		config.height = 480;
		new LwjglApplication(new PlatPlus(), config);
	}
}
