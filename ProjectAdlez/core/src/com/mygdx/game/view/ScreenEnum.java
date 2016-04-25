package com.mygdx.game.view;

/**
 * Created by Viktor on 2016-04-19.
 */

public enum ScreenEnum {

    INTRO {
        public AbstractScreen getScreen() {
            return new IntroScreen();
        }
    },
    MAIN_MENU {
        public AbstractScreen getScreen() {
            return new MainMenuScreen();
        }
    },
    GAME {
        public AbstractScreen getScreen() {
            return new GameScreen();
        }
    };

    public abstract AbstractScreen getScreen();
}