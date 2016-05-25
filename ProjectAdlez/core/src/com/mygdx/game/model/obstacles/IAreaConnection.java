package com.mygdx.game.model.obstacles;

import com.mygdx.game.model.core.GateOpenListener;
import com.mygdx.game.model.core.IWorldObject;

/**
 * Created by martinso on 06/05/16.
 */
public interface IAreaConnection extends IWorldObject {
    void add(GateOpenListener listener);
    void remove(GateOpenListener listener);
}
