package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.builder.AreaBuilder;
import com.mygdx.game.builder.AreaHandler;
import com.mygdx.game.builder.AreaIO;
import com.mygdx.game.model.*;
import com.mygdx.game.model.Character;
import com.mygdx.game.model.exceptions.InventoryFullException;
import com.mygdx.game.model.exceptions.ItemNotFoundException;
import com.mygdx.game.model.handler.CollisionHandler;
import com.mygdx.game.utils.AssetStrings;
import com.mygdx.game.screens.ScreenManager;
import com.mygdx.game.view.CharacterView;
import com.mygdx.game.view.ICharacterView;

import java.util.List;

/**
 * Created by martinso on 27/03/16.
 */
public class PlayerController implements ICharacterController, GateOpenListener {

// Have a screens not extend a screens

    private IPlayer player;
    private CharacterView playerView;
    private Adlez adlez;
    private List<IAreaConnection> areaConnections;
    private AreaHandler areaHandler;

    /**
     * To be able to paint where actions landed for debugging purposes
     */
    public static IAttack currentAttack = new MeleeAttack();
    public static IInteraction currentInteraction = new Interaction();

    public PlayerController(IPlayer player) {
        this.player = player;
        playerView = new CharacterView(AssetStrings.PLAYER_MOVE);
        adlez = Adlez.getInstance();
        areaHandler = AreaHandler.getInstance();

        areaConnections = adlez.getAreaConnections();
        for (IAreaConnection ac : areaConnections) {
            ac.add(this);
        }
    }

    /**
     * Handles all the character actions.
     * Move with: W A S D
     * Attack with: K
     */
    @Override
    public void update(float deltaT) {
        // Temporary sound to notify when player is dead
        if (player.getHealth() < 0) {
            GameSound dyingSound = new LibGDXSoundAdapter(AssetStrings.TEMP_DYING_SOUND);
            dyingSound.play(0.5f);
            player.setHealth(100);
        }

        Character playerCharacter = (Character) player;
        playerCharacter.clearMoveFlags();
    
//        System.out.println("Vx: " + playerCharacter.getVx());
//        System.out.println("Vy: " + playerCharacter.getVy());
        
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            playerCharacter.setMovingNorth();
        }else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            playerCharacter.setMovingSouth();
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            playerCharacter.setMovingWest();
        }else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            playerCharacter.setMovingEast();
        }
        
        playerCharacter.update(deltaT);
        playerCharacter.move(deltaT);

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            currentAttack = new MeleeAttack(playerCharacter);
            currentAttack.setSound(new LibGDXSoundAdapter(AssetStrings.MELEE_ATTACK_SOUND));
            currentAttack.playSound(0.1f);
            adlez.addAttack(currentAttack);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            if(player.getMana() >= 15) {
                currentAttack = new RangeMagicAttack(playerCharacter);
                currentAttack.setSound(new LibGDXSoundAdapter(AssetStrings.RANGE_MAGIC_ATTACK_SOUND));
                currentAttack.playSound(0.1f);
                player.useMana(currentAttack);
                adlez.addAttack(currentAttack);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            if(player.getMana() >= 20) {
                currentAttack = new AOEMagicAttack(playerCharacter);
                currentAttack.setSound(new LibGDXSoundAdapter(AssetStrings.AOE_MAGIC_ATTACK_SOUND));
                currentAttack.playSound(0.1f);
                player.useMana(currentAttack);
                adlez.addAttack(currentAttack);
            }
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
            AreaIO areaBuilder = new AreaBuilder();
            areaBuilder.saveAreaHandler();
            areaBuilder.savePlayer();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            AreaIO areaBuilder = new AreaBuilder();
            areaBuilder.loadAreaHandler();
            try {
                areaBuilder.loadPlayer();
            } catch (ItemNotFoundException e) {
                e.getMessage();
            }
            ScreenManager.getInstance().switchArea(AreaHandler.getInstance().getCurrentArea());
        }

        playerView.viewUpdate(player.getDirection());
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(getCurrentFrame(), player.getPosX(), player.getPosY());
    }

    @Override
    public ICharacterView getView() {
        return playerView;
    }

    public TextureRegion getCurrentFrame() {
        return playerView.getCurrentFrame();
    }

    @Override
    public void gateOpen(Object source) {
        if (areaHandler.getCurrentAreaInt() == AreaHandler.AREA_1) {
            ScreenManager.getInstance().switchArea(AreaHandler.getInstance().loadArea2());
        } else if (areaHandler.getCurrentAreaInt() == AreaHandler.AREA_2) {
            ScreenManager.getInstance().switchArea(AreaHandler.getInstance().loadArea1());
        }
    }

}