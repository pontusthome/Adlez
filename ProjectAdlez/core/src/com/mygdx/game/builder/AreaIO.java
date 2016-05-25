package com.mygdx.game.builder;

import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.model.exceptions.ItemNotFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Pontus on 2016-05-14.
 */
public interface AreaIO {
    void savePlayer();
    void saveAreaHandler() throws IOException;
    IPlayer loadPlayer() throws IOException;
    AreaHandler loadAreaHandler() throws IOException;
}
