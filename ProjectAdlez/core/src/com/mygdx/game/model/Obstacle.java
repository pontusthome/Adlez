package com.mygdx.game.model;

import java.io.Serializable;

/**
 * Created by martinso on 27/04/16.
 */
public class Obstacle extends WorldObject implements IObstacle {
    
    private int health;

    // Destructable obstacles, requires a number of hits to destroy. (health)
    public Obstacle(float posX, float posY, int width, int height, int health) {
        super(posX, posY, width, height);
        this.health = health;
    }
    public Obstacle(int posX, int posY) {
        super(posX*32, posY*32, 32, 32);
        this.health = 100;
    }

    public Obstacle(float posX, float posY) {
        super(posX, posY, 32, 32);
        this.health = 100;
    }

    @Override
    public int getHealth() {
        return health;
    }
    
    @Override
    public void setHealth(int health) {
        this.health = health;
    }
    
    @Override
    public void onCollide(Collidable other){
        if(other instanceof IAttack){
            IAttack attack = (IAttack) other;
            setHealth(getHealth() - attack.getDamage());
        }
    }
    
    @Override
    public boolean isDestroyed(){
        return getHealth() <= 0;
    }
}
