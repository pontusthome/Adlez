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
    private List<IEnemy> enemies;
    private List<IFriendlyNPC> friendlyNPCs;
    private List<IWorldObject> stationaryObjects;
    private List<IWall> walls;
    private List<IObstacle> obstacles;
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
        worldObjects = new ArrayList<IWorldObject>();
    
        collisionHandler = new CollisionHandler2();

        // add the player and set him to the new position
        worldObjects.add(player);
        collisionHandler.add((Collidable) player);
        player.setPosX(area.getPlayerXposition());
        player.setPosY(area.getPlayerYposition());
        ((WorldObject)player).setOldPosX(area.getPlayerXposition());
        ((WorldObject)player).setOldPosY(area.getPlayerYposition());
        ((WorldObject)player).setHitBox(new HitBox(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight()));

        enemies = area.getEnemies();
        List<IWorldObject> tempList = new ArrayList<>();
        for(IEnemy enemy : enemies){
            tempList.add((IWorldObject) enemy);
        }
        worldObjects.addAll(tempList);
        collisionHandler.addAll(enemies);
        
        tempList.clear();
        
        friendlyNPCs = area.getFriendlyNPCs();
        for(IFriendlyNPC friendlyNPC : friendlyNPCs){
            tempList.add((IWorldObject) friendlyNPC);
        }
        worldObjects.addAll(tempList);
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
        
    }

    public IPlayer getPlayer() {
        return player;
    }

    public List<IEnemy> getEnemies() { return enemies; }

    public List<IFriendlyNPC> getFriendlyNPCs() {
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
    
    public void removeEnemyFromWorld(INPC enemy){
        enemies.remove(enemy);
        worldObjects.remove(enemy);
        collisionHandler.remove((Collidable) enemy);
    }
    
    public CollisionHandler2 getCollisionHandler(){
        return collisionHandler;
    }
}
