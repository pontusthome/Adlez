package com.mygdx.game.main;

import com.badlogic.gdx.Game;
import com.mygdx.game.screens.ScreenManager;

public class AdlezGame extends Game {

    @Override
    public void create() {
        // Loads up the startscreen
        ScreenManager.getInstance().initialize(this);
    }

    @Override
    public void render() {
        super.render();
    }
}
