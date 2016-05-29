package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.obstacles.IChest;
import com.mygdx.game.view.ChestView;

import java.util.List;

/**
 * Created by Michel on 5.5.2016.
 */
public class ChestsController implements IController{
	
	private ChestView chestView;
	private List<IChest> chests;
	
	public ChestsController(List<IChest> chests, String closedChestImg, String openChestImg) {
		this.chests = chests;
		chestView = new ChestView(closedChestImg, openChestImg);
	}
	
	@Override
	public void update(float deltaT){
		// Nothing for now
	}
	
	@Override
	public void render(SpriteBatch batch){
		chestView.generateChests(chests, batch);
	}
}
