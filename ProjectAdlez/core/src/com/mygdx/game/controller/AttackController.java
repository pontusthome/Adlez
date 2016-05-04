package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.ICharacter;

/**
 * Created by Michel on 4.5.2016.
 */
public class AttackController implements IController{
	
	/** No implementation at the moment but this class should be used to handle attacks.
	 * Will be responsible for rendering them as well as removing them from the world when their work is done
	 */
	
	@Override
	public void update(){
		
	}
	
	@Override
	public void render(SpriteBatch batch){
		
	}
	
	@Override
	public ICharacterView getView(){
		return null;
	}
}
