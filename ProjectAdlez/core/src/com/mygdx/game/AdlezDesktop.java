package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.view.ScreenManager;

public class AdlezDesktop extends Game {

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
