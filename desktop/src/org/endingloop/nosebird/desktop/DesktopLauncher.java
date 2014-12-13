package org.endingloop.nosebird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.endingloop.nosebird.NBGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Noise Bird";
        config.width = 272;
        config.height = 408;
        new LwjglApplication(new NBGame(), config);
	}
}
