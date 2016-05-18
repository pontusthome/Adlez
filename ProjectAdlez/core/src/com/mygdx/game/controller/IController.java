package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Michel on 6.5.2016.
 */
public interface IController{
	void update(float deltaT);
	void render(SpriteBatch batch);
}
