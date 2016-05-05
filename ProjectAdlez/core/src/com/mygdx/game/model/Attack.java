package com.mygdx.game.model;

/**
 * Created by Michel on 5.5.2016.
 */
public abstract class Attack extends WorldObject implements IAttack{
	
	private int damage;
	private HitBox hitBox = new HitBox();
	private GameSound attackSound;
	private boolean isFinished;
	
	public Attack(){
		
	}
	
	/** Set position of attack in front of character. For now also set size the same as character's size */
	public Attack(ICharacter character){
		setDamage(character);
		setInitLocation(character);
		hitBox = new HitBox(getPosX(), getPosY(), getWidth(), getHeight());
	}
	
	@Override
	public void setDamage(int damage){
		this.damage = damage;
	}
	
	@Override
	public int getDamage(){
		return damage;
	}
	
	@Override
	public HitBox getHitBox(){
		return hitBox;
	}
	
	/**
	 * When an attack is done it should be removed from the world. Returns true for a melee attack because a melee
	 * attack only should be valid during a single game loop.
	 *
	 * @return True if attack should be removed from world, false otherwise
	 */
	@Override
	public boolean isFinished(){
		return isFinished;
	}
	
	@Override
	public void setFinished(){
		isFinished = true;
	}
	
	@Override
	public void setAttackSound(GameSound attackSound){
		this.attackSound = attackSound;
	}
	
	@Override
	public GameSound getAttackSound(){
		return null;
	}
	
	public void playAttackSound(float volume){
		attackSound.play(volume);
	}
}
