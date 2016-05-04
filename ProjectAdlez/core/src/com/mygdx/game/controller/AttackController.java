package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.IAttack;
import com.mygdx.game.model.ICharacter;
import com.mygdx.game.model.MeleeAttack;

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
		
		if(attack instanceof MeleeAttack){
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
