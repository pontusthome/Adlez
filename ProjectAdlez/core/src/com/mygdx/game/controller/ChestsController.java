package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.IChest;
import com.mygdx.game.model.IObstacle;
import com.mygdx.game.view.ChestView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michel on 5.5.2016.
 */
public class ChestsController implements IController{
	
	private ChestView chestView;
	private Adlez adlez;
	private List<IChest> chests;
	
	public ChestsController(List<IChest> chests, String chestImg) {
		adlez = Adlez.getInstance();
		this.chests = chests;
		chestView = new ChestView(chestImg);
	}
	
	@Override
	public void update(){
		List<IChest> destroyedChests = new ArrayList<>();
		for(IChest chest : chests){
			if(chest.isDestroyed()){
				destroyedChests.add(chest);
			}
		}
		for(IChest chest : destroyedChests){
			adlez.removeChestFromWorld(chest);
		}
	}
	
	@Override
	public void render(SpriteBatch batch){
		chestView.generateChests(chests, batch);
	}
	
	@Override
	public ICharacterView getView(){
		return null;
	}
}
