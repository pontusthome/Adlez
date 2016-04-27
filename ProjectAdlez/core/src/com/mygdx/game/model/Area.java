package com.mygdx.game.model;

import java.util.List;

/**
 * @author Pontus
 * If we want to save the areas, this should not be final? /PT 24/4
 */
public final class Area {

    private float playerXposition;
    private float playerYposition;

    private List<NPC> enemies;
    private List<NPC> friendlyNPCs;
    private List<WorldObject> stationaryObjects;
    private List<Wall> walls;

    public Area(float playerXposition, float playerYposition,
                List<NPC> enemies, List<NPC> friendlyNPCs, List<WorldObject> stationaryObjects, List<Wall> walls) {
        this.playerXposition = playerXposition;
        this.playerYposition = playerYposition;

        this.enemies = enemies;
        this.friendlyNPCs = friendlyNPCs;
        this.stationaryObjects = stationaryObjects;
        this.walls = walls;
    }

    public float getPlayerXposition() {
        return playerXposition;
    }

    public float getPlayerYposition() {
        return playerYposition;
    }

    public List<NPC> getEnemies() {
        return enemies;
    }

    public List<NPC> getFriendlyNPCs() {
        return friendlyNPCs;
    }

    public List<WorldObject> getStationaryObjects() {
        return stationaryObjects;
    }

    public List<Wall> getWalls() {
        return walls;
    }
}
