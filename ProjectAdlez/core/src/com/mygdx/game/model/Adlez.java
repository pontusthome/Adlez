package com.mygdx.game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by Pontus on 2016-04-19.
 *
 * Singleton of the model Adlez
 */
public class Adlez {
    private static Adlez adlez = new Adlez();

    public static Adlez getInstance() {
        return adlez;
    }

    private Player player;
    private List<NPC> enemies;

    private Adlez() {
        player = new Player();
        enemies = new ArrayList<NPC>();
        for (int i = 0; i < 20; i++) {
            NPC enemy = new NPC();
            enemy.setPosX(random.nextInt(1000)-500);
            enemy.setPosY(random.nextInt(1000)-500);
            enemy.setSpeed(random.nextFloat() * (2f - 1) + 1);
            enemies.add(enemy);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public List<NPC> getEnemies() { return enemies; }
}
