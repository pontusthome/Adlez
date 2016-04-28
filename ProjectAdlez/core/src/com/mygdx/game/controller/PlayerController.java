package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Player;

/**
 * Created by martinso on 27/03/16.
 */
public class PlayerController implements CharacterActions {

    // Have a view not extend a view

    private Player player;
    private CharacterView playerView;

    public PlayerController(Player player, String characterImg) {
        this.player = player;
        playerView = new CharacterView(characterImg);
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

        playerView.viewUpdate(player.getDirection());
    }
    
    @Override
    public void render(SpriteBatch batch){
        batch.draw(getCurrentFrame(), player.getPosX(), player.getPosY());
    }
    
    public TextureRegion getCurrentFrame() {
        return playerView.getCurrentFrame();
    }
}