package com.mygdx.game.model;

/**
 * Created by Michel on 4.5.2016.
 */
public class MeleeAttack extends WorldObject implements IAttack{
	
	private int damage;
	private HitBox hitBox = new HitBox();
	private GameSound attackSound;
	private boolean isFinished;
	
	public MeleeAttack(){
		
	}
	
	/** Set position of attack in front of character. For now also set size the same as character's size */
	public MeleeAttack(ICharacter character){
		this(character.getWidth(), character.getHeight(), 
				character.getAttackDamage());
		
		setPos(character);
		hitBox = new HitBox(getPosX(), getPosY(), getWidth(), getHeight());
		setFinished();
	}
	
	public MeleeAttack(int width, int height, int damage){
		setWidth(width);
		setHeight(height);
		this.damage = damage;
	}
	
	public MeleeAttack(float posX, float posY, int width, int height, int damage){
		super(posX, posY, width, height);
		this.damage = damage;
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
	public void onCollide(Collidable other){
		
	}
	
	private void setPos(ICharacter character){
		switch(character.getDirection()){
			case Direction.NORTH:
				setPos(character.getPosX(), character.getPosY() + character.getHeight());
				break;
			case Direction.SOUTH:
				setPos(character.getPosX(), character.getPosY() - character.getHeight());
				break;
			case Direction.EAST:
				setPos(character.getPosX() + character.getWidth(), character.getPosY());
				break;
			case Direction.WEST:
				setPos(character.getPosX() - character.getWidth(), character.getPosY());
				break;
		}
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
