package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.event.CollisionHandler;
import com.mygdx.game.model.*;
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
    
    
    public static Rectangle playerHitbox = new Rectangle(10,10,10,10);
    public static Rectangle enemyHitbox = new Rectangle(10,10,10,10);
    

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
        if (Gdx.input.isKeyPressed((Input.Keys.SPACE))) {
            CombatHandler.handleMeleeAttack();
        }

        view.update(player.getDirection());
    }
}