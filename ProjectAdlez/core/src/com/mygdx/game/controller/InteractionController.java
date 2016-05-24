package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.characters.actions.IInteraction;
import com.mygdx.game.model.core.GameSound;
import com.mygdx.game.model.core.LibGDXSoundAdapter;
import com.mygdx.game.utils.AssetStrings;

/**
 * Created by Michel on 8.5.2016.
 */
public class InteractionController implements IController{
	
	private IInteraction interaction;
	private Adlez adlez;
	
	public InteractionController(IInteraction interaction){
		this.interaction = interaction;
		adlez = Adlez.getInstance();
		GameSound interactionSound = new LibGDXSoundAdapter(AssetStrings.INTERACTION_SOUND);
		interactionSound.play(0.5f);
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
}
