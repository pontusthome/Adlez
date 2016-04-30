package com.mygdx.game.model;

/**
 * Created by martinso on 27/04/16.
 */
public class Obstacle extends WorldObject implements IObstacle{

    private Adlez adlez = Adlez.getInstance();
    private IPlayer player = adlez.getPlayer();

    private int health;

    // Destructable obstacles, requires a number of hits to destroy. (health)
    public Obstacle(float posX, float posY, int width, int height, int health) {
        super(posX, posY, width, height);
        this.health = player.getAttackDamage()*health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = player.getAttackDamage()*health;
    }
    
    @Override
    public void onCollide(Collidable other){
        
    }
}
