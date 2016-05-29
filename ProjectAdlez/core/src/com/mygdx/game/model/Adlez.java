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
    private Area area;
    private CollisionHandler collisionHandler;
    private List<IAttack> attacks;
    private List<IAttack> newAttacks;
    private List<IInteraction> interactions;
    private List<IInteraction> newInteractions;

    private Adlez() {
        player = new Player();
    }

    public void resetPlayer() {
        player.resetPlayer();
    }

    public void initiateArea(Area area) {
        // Reset the world and then set it up for the new area
        this.area = area;
        worldObjects = new ArrayList<>();
    
        interactions = new ArrayList<>();
        newInteractions = new ArrayList<>();
        attacks = new ArrayList<>();
        newAttacks = new ArrayList<>();

        // add the player and set him to the new position
        player.setPosX(area.getPlayerXposition());
        player.setPosY(area.getPlayerYposition());
        worldObjects.add(player);

        worldObjects.addAll(area.getEnemies());
        worldObjects.addAll(area.getFriendlyNPCs());
        worldObjects.addAll(area.getWalls());
        worldObjects.addAll(area.getObstacles());
        worldObjects.addAll(area.getChests());
        worldObjects.addAll(area.getAreaConnections());
        worldObjects.addAll(area.getManaFountains());

        collisionHandler = CollisionHandler.getInstance();
        collisionHandler.initiate(worldObjects, attacks, interactions, player);

        // Add this & collision handler as observers to all characters

        ObservableWorldObject playerObservable = (ObservableWorldObject) player;
        playerObservable.addObserver(this);
        playerObservable.addObserver(collisionHandler);

        for (IEnemy enemy : area.getEnemies()) {
            ObservableWorldObject enemyObservable = ((ObservableWorldObject) enemy);
            enemyObservable.addObserver(this);
            enemyObservable.addObserver(collisionHandler);
        }

        for (IFriendlyNPC fNPC : area.getFriendlyNPCs()) {
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
        return area.getEnemies();
    }

    public List<IFriendlyNPC> getFriendlyNPCs() {
        return area.getFriendlyNPCs();
    }

    public List<IWall> getWalls() {
        return area.getWalls();
    }

    public List<IObstacle> getObstacles() {
        return area.getObstacles();
    }

    public List<IChest> getChests() {
        return area.getChests();
    }

    public List<IAreaConnection> getAreaConnections() {
        return area.getAreaConnections();
    }

    public List<IManaFountain> getManaFountains() {
        return area.getManaFountains();
    }

    public void removeEnemyFromWorld(INPC enemy) {
        area.getEnemies().remove(enemy);
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
        area.getObstacles().remove(obstacle);
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
    
                    /** For debugging purposes
                     ((Character) character).latestAttack = attack;
                     */
                    break;
                }
                case "aoe_melee_attack":{
                    IAttack attack = new AOEMeleeAttack(character);
                    addAttack(attack);
    
                    /** For debugging purposes
                     ((Character) character).latestAttack = attack;
                     */
                    break;
                }
                case "range_magic_attack":{
                    IAttack attack = new RangeMagicAttack(character);
                    character.useMana(attack.getManaUsage());
                    addAttack(attack);
    
                    /** For debugging purposes
                     ((Character) character).latestAttack = attack;
                     */
                    break;
                }
                case "aoe_magic_attack":{
                    IAttack attack = new AOEMagicAttack(character);
                    character.useMana(attack.getManaUsage());
                    addAttack(attack);
    
                    /** For debugging purposes
                     ((Character) character).latestAttack = attack;
                     */
                    break;
                }
                case "interaction":
                    IInteraction interaction = new Interaction(character);
                    addInteraction(interaction);
    
                    /** For debugging purposes
                     ((Character) character).latestInteraction = interaction;
                     */
                    break;
            }
        }
    }
}
