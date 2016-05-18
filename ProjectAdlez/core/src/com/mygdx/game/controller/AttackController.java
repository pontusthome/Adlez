package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.*;

/**
 * Created by Michel on 4.5.2016.
 */

public class AttackController implements IController{
	
	private IAttack attack;
	private Adlez adlez;
	
	public AttackController(IAttack attack){
		this.attack = attack;
		adlez = Adlez.getInstance();
	}
	
	@Override
	public void update(float deltaT){
		if (attack.isFinished()){
			adlez.removeAttackFromWorld(attack);
		}
		
		/** 
		 * Melee attack should be in only one game loop, so set as finished immediately.
		 * 
		 * Other attacks though, such as projectiles, could survive several game loops.
		 * */
		if(attack instanceof MeleeAttack || attack instanceof RangeMagicAttack 
				|| attack instanceof AOEMagicAttack || attack instanceof EnemyAOEAttack){
			attack.setFinished();
		}
	}
	
	@Override
	public void render(SpriteBatch batch){
		
	}
	
	public IAttack getAttack(){
		return attack;
	}
}
