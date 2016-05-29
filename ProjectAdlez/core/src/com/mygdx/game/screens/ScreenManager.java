package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.Area;


/**
 * Created by Viktor on 2016-04-19.
 */
public class ScreenManager {

    // Singleton: unique instance
    private static ScreenManager instance;

    // Reference to game
    private Game game;

    // Singleton: private constructor
    private ScreenManager() {
        super();
    }

    // Singleton: retrieve instance
    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    // Initialization with the game class
    public void initialize(Game game) {
        this.game = game;
        showScreen(ScreenEnum.INTRO);
    }

    // Initialization with the game class
    public void switchArea(Area area) {
        // Initiate the area
        Adlez adlez = Adlez.getInstance();
        adlez.initiateArea(area);
        showScreen(ScreenEnum.GAME);
    }

    // Show in the game the screen which enum type is received
    public void showScreen(ScreenEnum screenEnum) {

        // Get current screen to dispose it
        Screen currentScreen = game.getScreen();

        // Show new screen
        AbstractScreen newScreen = screenEnum.getScreen();
        newScreen.buildStage();
        game.setScreen(newScreen);

        // Dispose previous screen
        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }
}
