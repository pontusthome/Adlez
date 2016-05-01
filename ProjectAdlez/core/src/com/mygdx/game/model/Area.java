package com.mygdx.game.model;

import java.util.List;

/**
 * @author Pontus
 * If we want to save the areas, this should not be final? /PT 24/4
 */
public final class Area {

    private float playerXposition;
    private float playerYposition;

    private List<INPC> enemies;
    private List<INPC> friendlyNPCs;
    private List<IWorldObject> stationaryObjects;
    private List<IWall> walls;
    private List<IObstacle> obstacles;
    private List<IChest> chests;

    public Area(float playerXposition, float playerYposition,
                List<INPC> enemies, List<INPC> friendlyNPCs, List<IWorldObject> stationaryObjects,
                List<IWall> walls, List<IObstacle> obstacles, List<IChest> chests) {
        this.playerXposition = playerXposition;
        this.playerYposition = playerYposition;

        this.enemies = enemies;
        this.friendlyNPCs = friendlyNPCs;
        this.stationaryObjects = stationaryObjects;
        this.walls = walls;
        this.obstacles = obstacles;
        this.chests = chests;
    }

    public float getPlayerXposition() {
        return playerXposition;
    }

    public float getPlayerYposition() {
        return playerYposition;
    }

    public List<INPC> getEnemies() {
        return enemies;
    }

    public List<INPC> getFriendlyNPCs() {
        return friendlyNPCs;
    }

    public List<IWorldObject> getStationaryObjects() {
        return stationaryObjects;
    }

    public List<IWall> getWalls() {
        return walls;
    }

    public List<IObstacle> getObstacles() {
        return obstacles;
    }

    public List<IChest> getChests() {
        return chests;
    }
}
