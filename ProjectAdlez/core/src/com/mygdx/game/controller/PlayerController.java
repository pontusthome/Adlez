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
            characterView.setCharacterPositionY(characterView.getCharacterPosition().y + 2f);
            // Animation from View
            characterView.setCurrentFrame(characterView.getAnimation().getKeyFrame(4 + characterView.getStateTime()));
            //playerPosition.y +=2f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            characterView.setCharacterPositionY(characterView.getCharacterPosition().y - 2f);
            // Animation from View
            characterView.setCurrentFrame(characterView.getAnimation().getKeyFrame(0 + characterView.getStateTime()));
            //playerPosition.y -=2f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            characterView.setCharacterPositionX(characterView.getCharacterPosition().x - 2f);
            // Animation from View
            characterView.setCurrentFrame(characterView.getAnimation().getKeyFrame(2 + characterView.getStateTime()));
            //playerPosition.x -=2f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            characterView.setCharacterPositionX(characterView.getCharacterPosition().x + 2f);
            // Animation from View
            characterView.setCurrentFrame(characterView.getAnimation().getKeyFrame(6 + characterView.getStateTime()));
            //playerPosition.x +=2f;
        }
    }

    @Override
    public void attackDirection() {
        if (Gdx.input.isKeyPressed((Input.Keys.K))) {
        }
    }
}