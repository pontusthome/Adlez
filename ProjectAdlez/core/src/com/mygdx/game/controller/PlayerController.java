package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.model.*;
import com.mygdx.game.view.CharacterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by martinso on 27/03/16.
 */
public class PlayerController implements CharacterActions {

    private Player player;
    private CharacterView view;
    private CollisionHandler collisionHandler = new CollisionHandler();
    private List<WorldObject> worldObjectList = Adlez.getInstance().getWorldObjects();
    private List<WorldObject> enemiesToRemove = new ArrayList<>();
    private List<WorldObject> enemiesToKeep = new ArrayList<>();
    private Sound sound = Gdx.audio.newSound(Gdx.files.internal("Gun_Shot.mp3"));
    
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
            player.setDirection(Direction.NORTH);
            if (collisionHandler.checkCollision(player)) {
                player.moveSouth();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.moveSouth();
            player.setDirection(Direction.SOUTH);
            if (collisionHandler.checkCollision(player)) {
                player.moveNorth();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.moveWest();
            player.setDirection(Direction.WEST);
            if (collisionHandler.checkCollision(player)) {
                player.moveEast();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveEast();
            player.setDirection(Direction.EAST);
            if (collisionHandler.checkCollision(player)) {
                player.moveWest();
            }
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
    
    public List<WorldObject> getEnemiesToRemove(){
        return enemiesToRemove;
    }
    
    public List<WorldObject> getEnemiesToKeep(){
        return enemiesToKeep;
    }
    
    public void clearEnemiesToKeep(){
        enemiesToKeep.clear();
    }
}