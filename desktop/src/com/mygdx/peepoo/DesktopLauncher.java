package com.mygdx.peepoo;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.peepoo.MyGdxGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("peepoo");
		config.setWindowedMode(640, 640);
//		config.setWindowedMode(1080, 720);
		config.setResizable(false);
		new Lwjgl3Application(new MyGdxGame(), config);
	}
}
