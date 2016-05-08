package com.mygdx.game.model;


/**
 * Created by Michel on 4.5.2016.
 */
public interface IAttack extends IAction{
	void setDamage(ICharacter character);
	void setDamage(int damage);
	int getDamage();
}
