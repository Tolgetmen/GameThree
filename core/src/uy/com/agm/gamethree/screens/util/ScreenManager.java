package uy.com.agm.gamethree.screens.util;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import uy.com.agm.gamethree.screens.AbstractScreen;

/**
 * Created by AGM on 1/18/2018.
 */

public class ScreenManager {
    private static final String TAG = ScreenManager.class.getName();

    // Singleton: unique instance
    private static ScreenManager instance;

    // Reference to game
    private Game game;

    // Singleton: prevent instantiation from other classes
    private ScreenManager() {
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
    }

    // Reference to game
    public Game getGame() {
        return game;
    }

    // Show in the game the screen which enum type is received
    public void showScreen(ScreenEnum screenEnum, Object... params) {

        // Get current screen to dispose it
        Screen currentScreen = game.getScreen();

        // Show new screen
        AbstractScreen newScreen = screenEnum.getScreen(params);
        newScreen.buildStage();
        game.setScreen(newScreen);

        // Dispose previous screen
        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }
}