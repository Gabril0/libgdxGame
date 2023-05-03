package com.mygdxt.trabalho;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdxt.trabalho.TrabalhoPoo2;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(144);
		config.setTitle("TrabalhoPoo2");
		config.setWindowedMode(1366, 768);
		
		new Lwjgl3Application(new TrabalhoPoo2(), config);
	}
}
