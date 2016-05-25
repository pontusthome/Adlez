package com.mygdx.game.model;

/**
 * Created by Michel on 2016-05-24.
 */
public interface ObservableWorldObject{
	void addObserver(WorldObjectObserver observer);
	void removeObserver(WorldObjectObserver observer);
	void notifyObservers(Object arg);
}
