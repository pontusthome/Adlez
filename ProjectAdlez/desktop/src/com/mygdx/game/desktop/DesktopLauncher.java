package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.AdlezDesktop;
import org.lwjgl.opengl.Display;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Adlez";
		config.width = 800;
		config.height = 500;
		config.x = 1000;
		config.y = 100;
		new LwjglApplication(new AdlezDesktop(), config);
	}
}
