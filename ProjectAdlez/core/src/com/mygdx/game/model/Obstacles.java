package com.mygdx.game.model;

/**
 * Created by martinso on 27/04/16.
 */
public class Obstacles extends WorldObject{

    private Adlez adlez = Adlez.getInstance();
    private Player player = adlez.getPlayer();

    private int health;

    // Destructable obstacles, requires a number of hits to destroy. (health)
    public Obstacles(float posX, float posY, int width, int height, int health) {
        setPosX(posX);
        setPosY(posY);
        setWidth(width);
        setHeight(height);
        this.health = player.getAttackDamage()*health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = player.getAttackDamage()*health;
    }

}
