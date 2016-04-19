package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.view.CharacterView;

/**
 * Created by martinso on 27/03/16.
 */
public class PlayerController implements CharacterActions {

    private CharacterView characterView;

    private Vector2 playerPosition;

    public PlayerController(CharacterView character) {
        characterView = character;
    }

    // Updating the character's animation.
    public void updatePlayer() {
        moveDirection();

        if (characterView.getStateTime() < 2) {
            characterView.setStateTime(characterView.getStateTime() + Gdx.graphics.getDeltaTime() * 6);
            if (characterView.getStateTime() > 2) {
                characterView.setStateTime(0);
            }
        } else {
            characterView.setStateTime(0);
        }
        characterView.setStateTime(characterView.getStateTime() + Gdx.graphics.getDeltaTime());
    }

    // Moving player with keys: W A S D
    @Override
    public void moveDirection() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            move(0, 2f);
            updateAnimation(4);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            move(0, -2f);
            updateAnimation(0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            move(-2f, 0);
            updateAnimation(2);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            move(2f, 0);
            updateAnimation(6);
        }
    }

    public void move(float x, float y) {
        characterView.setCharacterPositionX(characterView.getCharacterPosition().x + x);
        characterView.setCharacterPositionY(characterView.getCharacterPosition().y + y);
    }

    public void updateAnimation(int frame) {
        characterView.setCurrentFrame(characterView.getAnimation().getKeyFrame(frame + characterView.getStateTime()));
    }


    @Override
    public void attackDirection() {
        if (Gdx.input.isKeyPressed((Input.Keys.K))) {
        }
    }
}