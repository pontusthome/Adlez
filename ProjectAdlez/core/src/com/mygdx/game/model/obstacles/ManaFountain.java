package com.mygdx.game.model.obstacles;

import com.mygdx.game.model.core.*;
import com.mygdx.game.model.actions.IInteraction;
import com.mygdx.game.model.characters.IPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 15/05/16.
 */
public class ManaFountain extends WorldObject implements IManaFountain, ObservableWorldObject{
    
    private List<WorldObjectObserver> observers;
    
    public ManaFountain(float posX, float posY, int width, int height) {
        super(posX, posY, width, height);
        observers = new ArrayList<>();
    }

    @Override
    public void onCollide(Collidable other) {
        if (other instanceof IInteraction) {
            IInteraction interaction = (IInteraction) other;
            if (interaction.byPlayer()) {
                
                notifyObservers("mana_fountain_interacted", null);
            }
        }
    }
    
    @Override
    public void addObserver(WorldObjectObserver observer){
        if(!observers.contains(observer)){
            observers.add(observer);
        }
    }
    
    @Override
    public void removeObserver(WorldObjectObserver observer){
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers(String action, IWorldObject other){
        for(WorldObjectObserver observer : observers){
            observer.update(this, action, other);
        }
    }
}
