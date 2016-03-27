package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.view.PlayScreen;

public class AdlezDesktop extends Game {
    Game game;

    @Override
    public void create() {
        game = this;
        // Temporary start screen. Needs to be a proper main menu screen.
        setScreen(new PlayScreen(game));
    }

    @Override
    public void render() {
        super.render();
    }
}
