package com.mygdx.game.model;

import com.mygdx.game.model.characters.Character;
import com.mygdx.game.model.characters.actions.*;
import com.mygdx.game.model.characters.*;
import com.mygdx.game.model.core.IWorldObject;
import com.mygdx.game.model.collisionHandler.CollisionHandler;
import com.mygdx.game.model.core.ObservableWorldObject;
import com.mygdx.game.model.core.WorldObjectObserver;
import com.mygdx.game.model.obstacles.*;
import com.mygdx.game.model.obstacles.IChest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pontus
 *         <p>
 *         Singleton of the model Adlez
 */
public class Adlez implements WorldObjectObserver {
    private static Adlez adlez = new Adlez();

    public static Adlez getInstance() {
        return adlez;
    }

    private List<IWorldObject> worldObjects;

    private IPlayer player;
    private List<IEnemy> enemies;
    private List<IFriendlyNPC> friendlyNPCs;
    private List<IWall> walls;
    private List<IObstacle> obstacles;
    private List<IChest> chests;
    private List<IAreaConnection> areaConnections;
    private List<IManaFountain> manaFountains;
    private CollisionHandler collisionHandler;
    private List<IAttack> attacks = new ArrayList<>();
    private List<IAttack> newAttacks = new ArrayList<>();
    private List<IInteraction> interactions = new ArrayList<>();
    private List<IInteraction> newInteractions = new ArrayList<>();

    private Adlez() {
        player = new Player();
    }

    public void resetPlayer() {
        player.resetPlayer();
    }

    public void initiateArea(Area area) {
        // Reset the world and then set it up for the new area
        worldObjects = new ArrayList<>();

        // add the player and set him to the new position
        player.setPosX(area.getPlayerXposition());
        player.setPosY(area.getPlayerYposition());
        worldObjects.add(player);

        enemies = area.getEnemies();
        List<IWorldObject> tempList = new ArrayList<>();
        for (IEnemy enemy : enemies) {
            tempList.add(enemy);
        }
        worldObjects.addAll(tempList);

        tempList.clear();

        friendlyNPCs = area.getFriendlyNPCs();
        for (IFriendlyNPC friendlyNPC : friendlyNPCs) {
            tempList.add(friendlyNPC);
        }
        worldObjects.addAll(tempList);

        walls = area.getWalls();
        worldObjects.addAll(walls);

        obstacles = area.getObstacles();
        worldObjects.addAll(obstacles);

        chests = area.getChests();
        worldObjects.addAll(chests);

        attacks.clear();

        areaConnections = area.getAreaConnections();
        worldObjects.addAll(areaConnections);

        manaFountains = area.getManaFountains();
        worldObjects.addAll(manaFountains);

        collisionHandler = CollisionHandler.getInstance();
        collisionHandler.initiate(worldObjects, attacks, interactions, player);

        // Add this & collision handler as observers to all characters

        ObservableWorldObject playerObservable = (ObservableWorldObject) player;
        playerObservable.addObserver(this);
        playerObservable.addObserver(collisionHandler);

        for (IEnemy enemy : enemies) {
            ObservableWorldObject enemyObservable = ((ObservableWorldObject) enemy);
            enemyObservable.addObserver(this);
            enemyObservable.addObserver(collisionHandler);
        }

        for (IFriendlyNPC fNPC : friendlyNPCs) {
            ObservableWorldObject fNPCObservable = ((ObservableWorldObject) fNPC);
            fNPCObservable.addObserver(this);
            fNPCObservable.addObserver(collisionHandler);
        }
    }

    public IPlayer getPlayer() {
        return player;
    }

    public void setPlayer(IPlayer player) {
        this.player = player;
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

    public void removeEnemyFromWorld(INPC enemy) {
        enemies.remove(enemy);
        worldObjects.remove(enemy);
    }

    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }

    public List<IAttack> getAttacks() {
        return attacks;
    }

    public void removeAttackFromWorld(IAttack attack) {
        attacks.remove(attack);
    }

    public List<IAttack> getNewAttacks() {
        return newAttacks;
    }

    private void addAttack(IAttack attack) {
        newAttacks.add(attack);
        attacks.add(attack);
    }

    public void removeObstacleFromWorld(IObstacle obstacle) {
        obstacles.remove(obstacle);
        worldObjects.remove(obstacle);
    }

    private void addInteraction(IInteraction interaction) {
        newInteractions.add(interaction);
        interactions.add(interaction);
    }

    public void removeInteractionFromWorld(IInteraction interaction) {
        interactions.remove(interaction);
    }

    public List<IInteraction> getNewInteractions() {
        return newInteractions;
    }

    @Override
    public void update(IWorldObject worldObject, String action) {
        if (worldObject instanceof ICharacter) {
            ICharacter character = (ICharacter) worldObject;
            switch(action){
                case "melee_attack":{
                    IAttack attack = new MeleeAttack(character);
                    addAttack(attack);
            
                    //TODO: Remove when debugging is over
                    ((Character) character).latestAttack = attack;
                    break;
                }
                case "aoe_melee_attack":{
                    IAttack attack = new AOEMeleeAttack(character);
                    addAttack(attack);
            
                    //TODO: Remove when debugging is over
                    ((Character) character).latestAttack = attack;
                    break;
                }
                case "range_magic_attack":{
                    IAttack attack = new RangeMagicAttack(character);
                    character.useMana(attack.getManaUsage());
                    addAttack(attack);
            
                    //TODO: Remove when debugging is over
                    ((Character) character).latestAttack = attack;
                    break;
                }
                case "aoe_magic_attack":{
                    IAttack attack = new AOEMagicAttack(character);
                    character.useMana(attack.getManaUsage());
                    addAttack(attack);
            
                    //TODO: Remove when debugging is over
                    ((Character) character).latestAttack = attack;
                    break;
                }
                case "interaction":
                    IInteraction interaction = new Interaction(character);
                    addInteraction(interaction);
            
                    //TODO: Remove when debugging is over
                    ((Character) character).latestInteraction = interaction;
                    break;
            }
        }
    }
}
