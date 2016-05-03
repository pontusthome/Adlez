package com.mygdx.game.model;

import com.mygdx.game.model.handler.CollisionHandler2;

import java.util.ArrayList;
import java.util.List;

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

    private List<IWorldObject> worldObjects;

    private IPlayer player;
    private List<INPC> enemies;
    private List<INPC> friendlyNPCs;
    private List<IWorldObject> stationaryObjects;
    private List<IWall> walls;
    private List<IObstacle> obstacles;
    private List<IChest> chests;
    private CollisionHandler2 collisionHandler;

    private Adlez() {
        /** Set players's width & height to size of texture for debugging purposes */
        player = new Player(Direction.NORTH, 2f,
                            17, 17,
                            0, 0,
                            100, 20, 0, 100);
    }

    public void initiateArea(Area area) {
        // Reset the world and then set it up for the new area
        worldObjects = new ArrayList<>();

        collisionHandler = new CollisionHandler2();

        // add the player and set him to the new position
        worldObjects.add(player);
        collisionHandler.add((Collidable) player);
        player.setPosX(area.getPlayerXposition());
        player.setPosY(area.getPlayerYposition());

        enemies = area.getEnemies();
        worldObjects.addAll(enemies);
        collisionHandler.addAll(enemies);

        friendlyNPCs = area.getFriendlyNPCs();
        worldObjects.addAll(friendlyNPCs);
        collisionHandler.addAll(friendlyNPCs);

        stationaryObjects = area.getStationaryObjects();
        worldObjects.addAll(stationaryObjects);
        collisionHandler.addAll(stationaryObjects);

        walls = area.getWalls();
        worldObjects.addAll(walls);
        collisionHandler.addAll(walls);

        obstacles = area.getObstacles();
        worldObjects.addAll(obstacles);
        collisionHandler.addAll(obstacles);

        chests = area.getChests();
        worldObjects.addAll(chests);
        collisionHandler.addAll(chests);
    }

    public IPlayer getPlayer() {
        return player;
    }

    public List<INPC> getEnemies() { return enemies; }

    public List<INPC> getFriendlyNPCs() {
        return friendlyNPCs;
    }

    public List<IWorldObject> getStationaryObjects() {
        return stationaryObjects;
    }

    public List<IWorldObject> getWorldObjects() {
        return worldObjects;
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
    
    public void removeEnemyFromWorld(INPC enemy){
        enemies.remove(enemy);
        worldObjects.remove(enemy);
        collisionHandler.remove((Collidable) enemy);
    }

    public void removeChestFromWorld(IChest chest) {
        chests.remove(chest);
        worldObjects.remove(chest);
        collisionHandler.remove((Collidable) chest);
    }

    public CollisionHandler2 getCollisionHandler(){
        return collisionHandler;
    }
}
