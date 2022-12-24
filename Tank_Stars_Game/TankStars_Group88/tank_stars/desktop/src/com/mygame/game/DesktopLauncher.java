package com.mygame.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.mygame.game.LoadScreen;

import java.util.ArrayList;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(512, 250);
		config.useVsync(true);
		config.setForegroundFPS(60);
		config.setTitle("TANK STARS");
		new Lwjgl3Application(new MainGame(), config);
	}
}
