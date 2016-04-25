package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.event.CollisionHandler;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.NPC;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.view.CharacterView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinso on 27/03/16.
 */
public class PlayerController implements CharacterActions {

    private Player player;
    private CharacterView view;
    private CollisionHandler collisionHandler = new CollisionHandler();
    
    // Data for first implementation of attack
    private Sound sound = Gdx.audio.newSound(Gdx.files.internal("Gun_Shot.mp3"));
    private List<WorldObject> enemiesToKeep = new ArrayList<>();
    public List<WorldObject> getEnemiesToKeep(){return enemiesToKeep;}
    public void clearEnemiesToKeep(){enemiesToKeep.clear();}
    private List<WorldObject> worldObjectList = Adlez.getInstance().getWorldObjects();
    
    
    private Rectangle playerHitbox = new Rectangle();
    private Rectangle enemyHitbox = new Rectangle();
    

    public PlayerController(Player player, CharacterView view) {
        this.player = player;
        this.view = view;
    }

    /**
     * Handles all the character actions.
     * Move with: W A S D
     * Attack with: K
     */
    @Override
    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.moveNorth();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.moveSouth();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.moveWest();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveEast();
        }
        if (Gdx.input.isKeyPressed((Input.Keys.K))) {
            // Attack
        }
        if (Gdx.input.isKeyPressed((Input.Keys.SPACE))) {
            // Trying to implement an AOE attack around the player to begin with, 
            // which should be easier to implement than a normal attack
        
            sound.play();
        
            enemiesToKeep.clear();
        
            for(WorldObject object : worldObjectList){
                if(object instanceof NPC){
                
                    playerHitbox.set(player.getPosX() - player.getWidth()/2, player.getPosY() - player.getHeight()/2,
                            player.getWidth()*4, player.getHeight()*4);
                
                    enemyHitbox.set(object.getPosX(), object.getPosY(),
                            object.getWidth(), object.getHeight());
                
                    if (playerHitbox.overlaps(enemyHitbox)) {
                        System.out.println("Test");
                    
                        NPC enemy = (NPC) object;
                        enemy.setAliveStatus(false);
                    }else{
                        enemiesToKeep.add(object);
                    }
                }
            }
        
            System.out.println(enemiesToKeep.size());
        }

        view.update(player.getDirection());
    }
}