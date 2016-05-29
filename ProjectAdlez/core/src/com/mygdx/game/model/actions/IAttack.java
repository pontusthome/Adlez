package com.mygdx.game.model.actions;

/**
 * Created by Michel on 4.5.2016.
 */
public interface IAttack extends IAction{
	void setDamage(int damage);
	int getManaUsage();
	void setManaUsage(int manaUsage);
	int getDamage();
}
