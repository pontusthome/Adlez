package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.obstacles.IWall;

import java.util.List;

/**
 * Created by Michel on 6.5.2016.
 */
public class WallsController implements IController{
	
	private Adlez adlez;
	private List<IWall> walls;
	
	public WallsController(List<IWall> walls) {
		adlez = Adlez.getInstance();
		this.walls = walls;
	}
	
	@Override
	public void update(float deltaT){
		
	}
	
	//TODO: Render walls with this method?
	@Override
	public void render(SpriteBatch batch){
		
	}
}
