package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.Direction;
import com.mygdx.game.model.Player;
import com.mygdx.game.view.CharacterView;

/**
 * Created by martinso on 27/03/16.
 */
public class PlayerController {

    private Player player;

    public PlayerController(Player player) {
        this.player = player;
    }

    // Updating the character's animation.
    public void updatePlayer() {
        moveDirection();
    }

    // Moving player with keys: W A S D
    public void moveDirection() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.move(0, 2f);
            player.setDirection(Direction.NORTH);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.move(0, -2f);
            player.setDirection(Direction.SOUTH);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.move(-2f, 0);
            player.setDirection(Direction.WEST);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.move(2f, 0);
            player.setDirection(Direction.EAST);
        }
    }

    public void attackDirection() {
        if (Gdx.input.isKeyPressed((Input.Keys.K))) {
        }
    }
}