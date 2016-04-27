package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.event.AreaHandler;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.view.ScreenManager;

public class AdlezDesktop extends Game {
    Game game;
    private Adlez adlez = Adlez.getInstance();

    @Override
    public void create() {
        game = this;


        // Initiate the area
        adlez.initiateArea(AreaHandler.testLevel());

        // Loads up the startscreen
        ScreenManager.getInstance().initialize(this);
    }

    @Override
    public void render() {
        super.render();
    }
}
