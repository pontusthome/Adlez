package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.model.Direction;
import com.mygdx.game.model.Player;

/**
 * Created by martinso on 27/03/16.
 */
public class PlayerController implements CharacterActions {

    private Player player;
    private CollisionHandler collisionHandler = new CollisionHandler();

    public PlayerController(Player player) {
        this.player = player;
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
    }
}