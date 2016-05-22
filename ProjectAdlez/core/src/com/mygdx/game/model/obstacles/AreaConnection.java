package com.mygdx.game.model.obstacles;

import com.mygdx.game.model.core.Collidable;
import com.mygdx.game.model.core.GateOpenListener;
import com.mygdx.game.model.characters.actions.IInteraction;
import com.mygdx.game.model.core.WorldObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pontus on 2016-04-29.
 */
public class AreaConnection extends WorldObject implements IAreaConnection {

    private List<GateOpenListener> listeners = new ArrayList<GateOpenListener>();

    public AreaConnection(float posX, float posY, int width, int height) {
        super(posX, posY, width, height);
    }

    @Override
    public void onCollide(Collidable other) {
        if (other instanceof IInteraction) {
            System.out.println("sadasd");
            for (GateOpenListener listener : listeners) {
                listener.gateOpen();
            }
        }
    }

    @Override
    public void add(GateOpenListener listener) {
        listeners.add(listener);
    }

    @Override
    public void remove(GateOpenListener listener) {
        listeners.remove(listener);
    }
}
