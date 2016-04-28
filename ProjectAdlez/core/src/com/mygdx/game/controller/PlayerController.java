package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Player;

/**
 * Created by martinso on 27/03/16.
 */
public class PlayerController extends CharacterView implements CharacterActions {

    private Player player;

    public PlayerController(Player player, String characterImg) {
        this.player = player;

        characterTexture = new Texture((Gdx.files.internal((characterImg))));
        TextureRegion[][] tmp = TextureRegion.split(characterTexture,
                characterTexture.getWidth() / col,
                characterTexture.getHeight() / row);
        characterFrames = new TextureRegion[col * row];

        // Setting frames from player sheet.
        int index = 0;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                characterFrames[index++] = tmp[j][i];
            }
        }
        // Sheet index:
        // 0 2 4 6
        // 1 3 5 7

        animation = new Animation(1, characterFrames);
        stateTime = 0f;
        currentFrame = animation.getKeyFrame(0);
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            CombatHandler.handleMeleeAttack();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            CombatHandler.handleRangeMagicAttack();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            CombatHandler.handleAOEMagicAttack();
        }

        viewUpdate(player.getDirection());
    }
}