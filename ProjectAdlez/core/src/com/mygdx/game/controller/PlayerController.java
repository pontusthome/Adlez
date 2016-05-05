package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.*;
import com.mygdx.game.model.Character;
import com.mygdx.game.utils.AssetStrings;

/**
 * Created by martinso on 27/03/16.
 */
public class PlayerController implements IController {

    // Have a view not extend a view

    private IPlayer player;
    private CharacterView playerView;
    private Adlez adlez;
    
    /** To be able to paint where melee attack landed for debugging purposes */
    public static IAttack meleeAttack = new MeleeAttack(0,0,0,0,0);

    public PlayerController(IPlayer player, String characterImg) {
        this.player = player;
        playerView = new CharacterView(characterImg);
        adlez = Adlez.getInstance();
    }

    /**
     * Handles all the character actions.
     * Move with: W A S D
     * IAttack with: K
     */
    @Override
    public void update() {
        Character playerCharacter = (Character) player;
        playerCharacter.clearMoveFlags();
        
        /** Movement only in 1 direction at a time */
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.moveNorth();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.moveSouth();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.moveWest();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveEast();
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            meleeAttack = new MeleeAttack(playerCharacter);
            meleeAttack.setAttackSound(new LibGDXSoundAdapter(AssetStrings.MELEE_ATTACK_SOUND));
            meleeAttack.playAttackSound(0.1f);
            adlez.addAttack(meleeAttack);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            
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