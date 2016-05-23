package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.builder.AreaBuilder;
import com.mygdx.game.builder.AreaHandler;
import com.mygdx.game.builder.AreaIO;
import com.mygdx.game.model.*;
import com.mygdx.game.model.characters.IFriendlyNPC;
import com.mygdx.game.model.characters.NPCShop;
import com.mygdx.game.model.characters.actions.*;
import com.mygdx.game.model.characters.Character;
import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.model.core.GameSound;
import com.mygdx.game.model.core.GateOpenListener;
import com.mygdx.game.model.core.LibGDXSoundAdapter;
import com.mygdx.game.model.characters.ShopOpenListener;
import com.mygdx.game.model.obstacles.IAreaConnection;
import com.mygdx.game.utils.AssetStrings;
import com.mygdx.game.screens.ScreenManager;
import com.mygdx.game.view.CharacterView;
import com.mygdx.game.view.ICharacterView;

import java.util.List;

/**
 * Created by martinso on 27/03/16.
 */
public class PlayerController implements ICharacterController, GateOpenListener , ShopOpenListener {

// Have a screens not extend a screens

    private IPlayer player;
    private CharacterView playerView;
    private Adlez adlez;
    private List<IAreaConnection> areaConnections;
    private List<IFriendlyNPC> friendlyNPCs;
    private AreaHandler areaHandler;
    private GameSound outOfManaSound;
    
    //TODO: Remove when debugging is over
    public static IAttack currentAttack;
    public static IInteraction currentInteraction;

    public PlayerController(IPlayer player) {
        this.player = player;
        playerView = new CharacterView(AssetStrings.PLAYER_MOVE);
        adlez = Adlez.getInstance();
        areaHandler = AreaHandler.getInstance();

        areaConnections = adlez.getAreaConnections();
        for (IAreaConnection ac : areaConnections) {
            ac.add(this);
        }

        friendlyNPCs = adlez.getFriendlyNPCs();
        for (IFriendlyNPC fNPC : friendlyNPCs) {
            fNPC.add(this);
        }
    
        outOfManaSound = new LibGDXSoundAdapter(AssetStrings.OUT_OF_MANA_SOUND);
    
        //TODO: Remove when debugging is over
        currentAttack = player.getLatestAttack();
        currentInteraction = player.getLatestInteraction();
    }

    /**
     * Handles all the character actions.
     * Move with: W A S D
     * Attack with: K
     */
    @Override
    public void update(float deltaT) {
        //TODO: Remove when debugging is over
        currentAttack = player.getLatestAttack();
        currentInteraction = player.getLatestInteraction();
        
        // Temporary sound to notify when player is dead
        if (player.getHealth() == 0) {
            GameSound dyingSound = new LibGDXSoundAdapter(AssetStrings.TEMP_DYING_SOUND);
            dyingSound.play(0.5f);
            player.setHealth(100);
        }

        Character playerCharacter = (Character) player;
        playerCharacter.clearMoveFlags();
        
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player.MeleeAttack();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            if(player.getMana() >= 15) {
                player.RangeMagicAttack();
            }else{
                outOfManaSound.play(0.5f);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            if(player.getMana() >= 20) {
                player.AOEMagicAttack();
            }else{
                outOfManaSound.play(0.5f);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            player.interact();
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
            areaBuilder.loadPlayer();

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

    private TextureRegion getCurrentFrame() {
        return playerView.getCurrentFrame();
    }

    @Override
    public void gateOpen() {
        if (areaHandler.getCurrentAreaInt() == AreaHandler.AREA_1) {
            ScreenManager.getInstance().switchArea(AreaHandler.getInstance().loadArea2());
        } else if (areaHandler.getCurrentAreaInt() == AreaHandler.AREA_2) {
            ScreenManager.getInstance().switchArea(AreaHandler.getInstance().loadArea1());
        }
    }

    @Override
    public void shopOpen(NPCShop shop) {
        // Should open the shop view...
        // Waiting for the inventory view to be implemented...
    }

}