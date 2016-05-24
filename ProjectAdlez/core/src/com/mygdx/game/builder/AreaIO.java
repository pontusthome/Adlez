package com.mygdx.game.builder;

import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.model.exceptions.ItemNotFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Pontus on 2016-05-14.
 */
public interface AreaIO {
    public void savePlayer();
    public void saveAreaHandler() throws IOException;
    public IPlayer loadPlayer() throws IOException;
    public AreaHandler loadAreaHandler() throws IOException;
}
