package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import java.awt.Dimension;
import java.awt.Toolkit;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(144);
		config.setTitle("TrabalhoPOO2");
		config.setWindowIcon("PlayerBaseSprite.png");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth() - (int)(screenSize.getWidth() * 0.1f);
        int screenHeight = (int) screenSize.getHeight() - (int)(screenSize.getHeight() * 0.1f);

		if(screenWidth >= 1980){screenWidth = 1980;} //just for it to not become unplayable if too large
		if(screenHeight >= 1080){screenHeight = 1080;}

        config.setWindowedMode(screenWidth, screenHeight);
        config.setResizable(false);

		new Lwjgl3Application(new MyGdxGame(), config);
	}
}
