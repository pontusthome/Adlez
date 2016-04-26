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
    private List<NPC> friendlyNPCs;
    private List<WorldObject> stationaryObjects;

    private Adlez() {
        /** Set players's width & height to size of texture for debugging purposes */
        player = new Player(Direction.NORTH, 2f,
                            29, 29,
                            0, 0,
                            100, 20, 0);
    }

    public void initiateArea(Area area) {
        // Reset the world and then set it up for the new area
        worldObjects = new ArrayList<WorldObject>();

        // add the player and set him to the new position
        /** Set players's width & height to size of texture for debugging purposes */
        player = new Player(Direction.NORTH, 2f,
                            29, 29,
                            0, 0,
                            100, 20, 0);
        worldObjects.add(player);
        player.setPosX(area.getPlayerXposition());
        player.setPosY(area.getPlayerYposition());

        enemies = area.getEnemies();
        worldObjects.addAll(enemies);

        friendlyNPCs = area.getFriendlyNPCs();
        worldObjects.addAll(friendlyNPCs);

        stationaryObjects = area.getStationaryObjects();
        worldObjects.addAll(stationaryObjects);
    }

    public Player getPlayer() {
        return player;
    }

    public List<NPC> getEnemies() { return enemies; }

    public List<NPC> getFriendlyNPCs() {
        return friendlyNPCs;
    }

    public List<WorldObject> getStationaryObjects() {
        return stationaryObjects;
    }

    public List<WorldObject> getWorldObjects() {
        return worldObjects;
    }
    
    public void removeEnemyFromWorld(NPC enemy){
        enemies.remove(enemy);
        worldObjects.remove(enemy);
    }
}
