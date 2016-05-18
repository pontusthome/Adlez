package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.IInteraction;

/**
 * Created by Michel on 8.5.2016.
 */
public class InteractionController implements IController{
	
	private IInteraction interaction;
	private Adlez adlez;
	
	public InteractionController(IInteraction interaction){
		this.interaction = interaction;
		adlez = Adlez.getInstance();
	}
	
	@Override
	public void update(float deltaT){
		if (interaction.isFinished()){
			adlez.removeInteractionFromWorld(interaction);
		}
		
		interaction.setFinished();
	}
	
	@Override
	public void render(SpriteBatch batch){
		
	}
	
	public IInteraction getInteraction(){
		return interaction;
	}
}
