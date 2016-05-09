package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.event.AreaHandler;
import com.mygdx.game.model.*;
import com.mygdx.game.model.Character;
import com.mygdx.game.utils.AssetStrings;
import com.mygdx.game.view.ScreenManager;

/**
 * Created by martinso on 27/03/16.
 */
public class PlayerController implements ICharacterController{

    // Have a view not extend a view

    private IPlayer player;
    private CharacterView playerView;
    private Adlez adlez;
    
    /** To be able to paint where actions landed for debugging purposes */
    public static IAttack currentAttack = new MeleeAttack();
    public static IInteraction currentInteraction = new Interaction();

    public PlayerController(IPlayer player) {
        this.player = player;
        playerView = new CharacterView(AssetStrings.PLAYER_MOVE);
        adlez = Adlez.getInstance();
    }

    /**
     * Handles all the character actions.
     * Move with: W A S D
     * Attack with: K
     */
    @Override
    public void update() {
        Character playerCharacter = (Character) player;
        playerCharacter.clearMoveFlags();
        
        /** Movement only in 1 direction at a time */
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.moveNorth();
        }else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.moveSouth();
        }else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.moveWest();
        }else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveEast();
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            currentAttack = new MeleeAttack(playerCharacter);
            currentAttack.setSound(new LibGDXSoundAdapter(AssetStrings.MELEE_ATTACK_SOUND));
            currentAttack.playSound(0.1f);
            adlez.addAttack(currentAttack);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            currentAttack = new RangeMagicAttack(playerCharacter);
            currentAttack.setSound(new LibGDXSoundAdapter(AssetStrings.RANGE_MAGIC_ATTACK_SOUND));
            currentAttack.playSound(0.1f);
            adlez.addAttack(currentAttack);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            currentAttack = new AOEMagicAttack(playerCharacter);
            currentAttack.setSound(new LibGDXSoundAdapter(AssetStrings.AOE_MAGIC_ATTACK_SOUND));
            currentAttack.playSound(0.1f);
            adlez.addAttack(currentAttack);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            currentInteraction = new Interaction(playerCharacter);
            currentInteraction.setSound(new LibGDXSoundAdapter(AssetStrings.INTERACTION_SOUND));
            currentInteraction.playSound(0.5f);
            adlez.addInteraction(currentInteraction);
        }

        /**
         * ===============================
         * TEST for changing areas
         * ===============================
          */
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            ScreenManager.getInstance().switchArea(AreaHandler.getInstance().loadArea1());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            ScreenManager.getInstance().switchArea(AreaHandler.getInstance().loadArea2());
        }
        /**
         * ===============================
         * TEST for saving and loading areas
         * ===============================
         */
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
            AreaHandler.getInstance().SaveAreaHandler();
            Adlez.getInstance().savePlayer();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            AreaHandler.getInstance().LoadAreaHandler();
            Adlez.getInstance().loadPlayer();
            ScreenManager.getInstance().switchArea(AreaHandler.getInstance().getCurrentArea());
        }

        playerView.viewUpdate(player.getDirection());
    }
    
    @Override
    public void render(SpriteBatch batch){
        batch.draw(getCurrentFrame(), player.getPosX(), player.getPosY());
    }

    @Override
    public ICharacterView getView() {
        return playerView;
    }

    public TextureRegion getCurrentFrame() {
        return playerView.getCurrentFrame();
    }
}