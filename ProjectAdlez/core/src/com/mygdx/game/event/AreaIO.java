package com.mygdx.game.event;

import com.mygdx.game.model.Player;

/**
 * Created by Pontus on 2016-05-14.
 */
public interface AreaIO {
    public void savePlayer();
    public void saveAreaHandler();
    public Player loadPlayer();
    public AreaHandler loadAreaHandler();
}
