package com.mygdx.game.screens;

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
    },
    GAME_OVER {
        public AbstractScreen getScreen() {
            return new GameOverScreen();
        }
    };

    public abstract AbstractScreen getScreen();
}