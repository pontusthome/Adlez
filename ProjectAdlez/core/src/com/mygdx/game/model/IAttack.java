package com.mygdx.game.model;


/**
 * Created by Michel on 4.5.2016.
 */
public interface IAttack extends IWorldObject{
	void setDamage(ICharacter character);
	void setDamage(int damage);
	int getDamage();
	HitBox getHitBox();
	boolean isFinished();
	void setFinished();
	void setAttackSound(GameSound attackSound);
	GameSound getAttackSound();
	void playAttackSound(float volume);
	void setInitLocation(ICharacter character);
}
