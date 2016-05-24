package com.mygdx.game.model;

import com.mygdx.game.model.core.IWorldObject;
import com.mygdx.game.model.characters.IEnemy;
import com.mygdx.game.model.characters.IFriendlyNPC;
import com.mygdx.game.model.obstacles.*;
import com.mygdx.game.model.obstacles.IChest;

import java.util.List;

/**
 * @author Pontus
 * If we want to save the areas, this should not be final? /PT 24/4
 */
public final class Area {

    private int name;
    private float playerXposition;
    private float playerYposition;

    private List<IEnemy> enemies;
    private List<IFriendlyNPC> friendlyNPCs;
    private List<IWall> walls;
    private List<IObstacle> obstacles;
    private List<IChest> chests;
    private List<IAreaConnection> areaConnections;
    private List<IManaFountain> manaFountains;

    public Area(float playerXposition, float playerYposition,
                List<IEnemy> enemies, List<IFriendlyNPC> friendlyNPCs,
                List<IWall> walls, List<IObstacle> obstacles, List<IChest> chests, List<IAreaConnection> areaConnections, int name, List<IManaFountain> manaFountains) {
        this.playerXposition = playerXposition;
        this.playerYposition = playerYposition;

        this.enemies = enemies;
        this.friendlyNPCs = friendlyNPCs;
        this.walls = walls;
        this.obstacles = obstacles;
        this.chests = chests;
        this.areaConnections = areaConnections;
        this.manaFountains = manaFountains;

        this.name = name;
    }

    public float getPlayerXposition() {
        return playerXposition;
    }

    public float getPlayerYposition() {
        return playerYposition;
    }

    public List<IEnemy> getEnemies() {
        return enemies;
    }

    public List<IFriendlyNPC> getFriendlyNPCs() {
        return friendlyNPCs;
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

    public List<IAreaConnection> getAreaConnections() {
        return areaConnections;
    }

    public List<IManaFountain> getManaFountains() {
        return manaFountains;
    }

    public int getAreaName() {
        return this.name;
    }
}
