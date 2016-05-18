package com.mygdx.game.builder;

import com.mygdx.game.model.IPlayer;

/**
 * Created by Pontus on 2016-05-14.
 */
public interface AreaIO {
    public void savePlayer();
    public void saveAreaHandler();
    public IPlayer loadPlayer();
    public AreaHandler loadAreaHandler();
}
