package com.mygdx.game.model.characters.actions;


import com.mygdx.game.model.characters.ICharacter;

/**
 * Created by Michel on 4.5.2016.
 */
public interface IAttack extends IAction{
	void setDamage(ICharacter character);
	void setDamage(int damage);
	int getManaUsage();
	void setManaUsage(int manaUsage);
	int getDamage();
}
