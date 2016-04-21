package com.mygdx.game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * @author Pontus
 *
 * Singleton of the model Adlez
 */
public class Adlez {
    private static Adlez adlez = new Adlez();

    public static Adlez getInstance() {
        return adlez;
    }

    private List<WorldObject> worldObjects;

    private Player player;
    private List<NPC> enemies;

    private Adlez() {
        worldObjects = new ArrayList<WorldObject>();

        player = new Player();
        worldObjects.add(player);

        enemies = new ArrayList<NPC>();
        for (int i = 0; i < 20; i++) {
            NPC enemy = new NPC();
            enemy.setPosX(random.nextInt(1000)-500);
            enemy.setPosY(random.nextInt(1000)-500);
            enemy.setSpeed(random.nextFloat() * (2f - 1f) + 1);
            enemies.add(enemy);
            worldObjects.add(enemy);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public List<NPC> getEnemies() { return enemies; }

    public List<WorldObject> getWorldObjects() {
        return worldObjects;
    }
}
