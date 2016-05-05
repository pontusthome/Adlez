package com.mygdx.game.model;

/**
 * Created by martinso on 27/04/16.
 */
public class Obstacle extends WorldObject implements IObstacle{
    
    private int health;

    // Destructable obstacles, requires a number of hits to destroy. (health)
    public Obstacle(float posX, float posY, int width, int height, int health) {
        super(posX, posY, width, height);
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

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
