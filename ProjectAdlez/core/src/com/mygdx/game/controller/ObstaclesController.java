package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.IObstacle;
import com.mygdx.game.view.ObstaclesView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michel on 5.5.2016.
 */
public class ObstaclesController implements IController{
	
	private ObstaclesView obstaclesView;
	private Adlez adlez;
	private List<IObstacle> obstacles;
	
	public ObstaclesController(List<IObstacle> obstacles, String obstacleImg) {
		adlez = Adlez.getInstance();
		this.obstacles = obstacles;
		obstaclesView = new ObstaclesView(obstacleImg);
	}
	
	@Override
	public void update(float deltaT){
		List<IObstacle> destroyedObstacles = new ArrayList<>();
		for(IObstacle obstacle : obstacles){
			if(obstacle.isDestroyed()){
				destroyedObstacles.add(obstacle);
			}
		}
		for(IObstacle obstacle : destroyedObstacles){
			adlez.removeObstacleFromWorld(obstacle);
		}
	}
	
	@Override
	public void render(SpriteBatch batch){
		obstaclesView.generateObstacles(obstacles, batch);
	}
}
