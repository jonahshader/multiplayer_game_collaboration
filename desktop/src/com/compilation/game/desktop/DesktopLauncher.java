package com.compilation.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.compilation.game.MainGame;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();

		for (GraphicsDevice g : gs) {
			DisplayMode dm = g.getDisplayMode();

			int refreshRate = dm.getRefreshRate();
			if (refreshRate == DisplayMode.REFRESH_RATE_UNKNOWN) {
				System.out.println("Unknown display refresh rate");
			} else {
				if (refreshRate > config.foregroundFPS)
					config.foregroundFPS = refreshRate;
			}

		}
		System.out.println("Running at " + config.foregroundFPS + " FPS");
		new LwjglApplication(new MainGame(), config);
	}
}
