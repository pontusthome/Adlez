package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.*;

/**
 * Created by Michel on 4.5.2016.
 */

public class AttackController implements IController{
	
	/** No implementation at the moment but this class should be used to handle attacks.
	 * Will be responsible for rendering them as well as removing them from the world when their work is done
	 */
	
	private IAttack attack;
	private Adlez adlez;
	
	public AttackController(IAttack attack){
		this.attack = attack;
		adlez = Adlez.getInstance();
	}
	
	@Override
	public void update(){
		if (attack.isFinished()){
			adlez.removeAttackFromWorld(attack);
		}
		
		/** 
		 * Melee attack should be in only one game loop, so set as finished immediately.
		 * 
		 * Other attacks though, such as projectiles, could survive several game loops.
		 * */
		if(attack instanceof MeleeAttack || attack instanceof RangeMagicAttack || attack instanceof AOEMagicAttack){
			attack.setFinished();
		}
	}
	
	@Override
	public void render(SpriteBatch batch){
		
	}
	
	@Override
	public ICharacterView getView(){
		return null;
	}
	
	public IAttack getAttack(){
		return attack;
	}
}
