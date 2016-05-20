package com.mygdx.game.model.obstacles;

import com.mygdx.game.model.core.GateOpenListener;
import com.mygdx.game.model.core.IWorldObject;

/**
 * Created by martinso on 06/05/16.
 */
public interface IAreaConnection extends IWorldObject {
    public void add(GateOpenListener listener);
    public void remove(GateOpenListener listener);
}
