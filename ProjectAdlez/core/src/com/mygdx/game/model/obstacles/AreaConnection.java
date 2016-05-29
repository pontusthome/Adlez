package com.mygdx.game.model.obstacles;

import com.mygdx.game.model.characters.ICharacter;
import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.model.core.Collidable;
import com.mygdx.game.model.core.GateOpenListener;
import com.mygdx.game.model.actions.IInteraction;
import com.mygdx.game.model.core.WorldObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pontus on 2016-04-29.
 */
public class AreaConnection extends WorldObject implements IAreaConnection {

    private List<GateOpenListener> listeners = new ArrayList<>();

    public AreaConnection(float posX, float posY, int width, int height) {
        super(posX, posY, width, height);
    }

    @Override
    public void onCollide(Collidable other) {
        if (other instanceof IInteraction) {
            IInteraction interaction = (IInteraction) other; 
            ICharacter interactor = interaction.getCharacter();
            if(interactor instanceof IPlayer){
                notifyListeners();
            }
        }
    }

    @Override
    public void add(GateOpenListener listener) {
        if(!listeners.contains(listener)){
            if(!listeners.isEmpty()){
                listeners.clear();
            }
            listeners.add(listener);
        }
    }

    @Override
    public void remove(GateOpenListener listener) {
        listeners.remove(listener);
    }
    
    private void notifyListeners(){
        for (GateOpenListener listener : listeners) {
            listener.gateOpen();
        }
    }
}
