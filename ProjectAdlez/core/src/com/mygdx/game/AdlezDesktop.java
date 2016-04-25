package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.view.PlayScreen;
import com.mygdx.game.view.ScreenEnum;
import com.mygdx.game.view.ScreenManager;

public class AdlezDesktop extends Game {
    Game game;

    @Override
    public void create() {
        game = this;
        // Temporary start screen. Needs to be a proper main menu screen.
        ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().showScreen(ScreenEnum.INTRO);


    }

    @Override
    public void render() {
        super.render();
    }
}
