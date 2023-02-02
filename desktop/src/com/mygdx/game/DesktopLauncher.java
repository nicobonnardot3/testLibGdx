package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
    public static void main (String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        config.setTitle(Application.TITLE + "v" + Application.VERSION);
        config.setWindowedMode(Application.V_WIDTH, Application.V_HEIGHT);
        config.setForegroundFPS(144);
        config.setResizable(false);

        new Lwjgl3Application(new Application(), config);
    }
}
