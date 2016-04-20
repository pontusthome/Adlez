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
            player.move(0, 2f);
            player.setDirection(Direction.NORTH);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.move(0, -2f);
            player.setDirection(Direction.SOUTH);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.move(-2f, 0);
            player.setDirection(Direction.WEST);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.move(2f, 0);
            player.setDirection(Direction.EAST);
        }
        if (Gdx.input.isKeyPressed((Input.Keys.K))) {
            // Attack
        }
    }
}