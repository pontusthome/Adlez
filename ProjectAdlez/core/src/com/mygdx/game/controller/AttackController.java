package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.*;
import com.mygdx.game.model.characters.actions.*;
import com.mygdx.game.sound.GameSound;
import com.mygdx.game.sound.LibGDXSoundAdapter;
import com.mygdx.game.utils.AssetStrings;

/**
 * Created by Michel on 4.5.2016.
 */

public class AttackController implements IController{
	
	private IAttack attack;
	private Adlez adlez;
	
	public AttackController(IAttack attack){
		this.attack = attack;
		adlez = Adlez.getInstance();
		
		GameSound attackSound;
		
		if(attack instanceof MeleeAttack || attack instanceof AOEMeleeAttack){
			attackSound = new LibGDXSoundAdapter(AssetStrings.MELEE_ATTACK_SOUND);
			attackSound.play(0.1f);
		}else if(attack instanceof AOEMagicAttack){
			attackSound = new LibGDXSoundAdapter(AssetStrings.AOE_MAGIC_ATTACK_SOUND);
			attackSound.play(0.1f);
		}else if(attack instanceof RangeMagicAttack){
			attackSound = new LibGDXSoundAdapter(AssetStrings.RANGE_MAGIC_ATTACK_SOUND);
			attackSound.play(0.1f);
		}
	}
	
	@Override
	public void update(float deltaT){
		if (attack.isFinished()){
			adlez.removeAttackFromWorld(attack);
		}
		
		/** 
		 * Attacks that should be in only one game loop are set as finished immediately.
		 * Other attacks though, such as projectiles, could survive several game loops.
		 */
		if(attack instanceof MeleeAttack || attack instanceof RangeMagicAttack
				|| attack instanceof AOEMagicAttack || attack instanceof AOEMeleeAttack){
			attack.setFinished();
		}
	}
	
	@Override
	public void render(SpriteBatch batch){
		
	}
}
