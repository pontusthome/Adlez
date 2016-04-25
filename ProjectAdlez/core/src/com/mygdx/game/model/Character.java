package com.mygdx.game.model;

import com.mygdx.game.event.CollisionHandler;

/**
 * Created by Michel on 2016-04-19.
 */
public abstract class Character extends WorldObject implements ICharacter {

	private int attackDamage;
	private int health;
	private int maxHealth;
	private int mana;
	private int maxMana;
	private int level;
	private int gold;
	private String name;
	private String characterType;
	private int direction;
	private float speed;

	// Character ska ta in värden så som health och attack. Nu har character inga stats...
	public Character(){
		direction = Direction.NORTH;
		speed = 2f;
		setWidth(2);
		setHeight(2);
	}

	public Character(int direction, float speed,
				  float width, float height,
				  float posX, float posY,
				  int maxHealth, int attackDamage, int gold) {
		setDirection(direction);
		setSpeed(speed);
		setWidth(width);
		setHeight(height);
		setPosX(posX);
		setPosY(posY);
		setMaxHealth(maxHealth);
		setHealth(maxHealth);
		setAttackDamage(attackDamage);
		setGold(gold);
	}

	@Override
	public void moveNorth() {
		float oldYpos = this.getPosY();
		this.setPosY(this.getPosY() + this.getSpeed());
		setDirection(Direction.NORTH);
		if (CollisionHandler.checkCollision(this)) {
			this.setPosY(oldYpos);
		}
	}
	@Override
	public void moveSouth() {
		float oldYpos = this.getPosY();
		this.setPosY(this.getPosY() - this.getSpeed());
		setDirection(Direction.SOUTH);
		if (CollisionHandler.checkCollision(this)) {
			this.setPosY(oldYpos);
		}
	}
	@Override
	public void moveWest() {
		float oldXpos = this.getPosX();
		this.setPosX(this.getPosX() - this.getSpeed());
		setDirection(Direction.WEST);
		if (CollisionHandler.checkCollision(this)) {
			this.setPosX(oldXpos);
		}
	}
	@Override
	public void moveEast() {
		float oldXpos = this.getPosX();
		this.setPosX(this.getPosX() + this.getSpeed());
		setDirection(Direction.EAST);
		if (CollisionHandler.checkCollision(this)) {
			this.setPosX(oldXpos);
		}
	}



	@Override
	public int getAttackDamage() {
		return attackDamage;
	}

	@Override
	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	@Override
	public int getHealth(){
		return health;
	}
	
	@Override
	public void setHealth(int health){
		this.health = health;
	}
	
	@Override
	public int getLevel(){
		return level;
	}
	
	@Override
	public void setLevel(int level){
		this.level = level;
	}
	
	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public int getMana(){
		return mana;
	}
	
	@Override
	public void setMana(int mana){
		this.mana = mana;
	}
	
	@Override
	public int getGold(){
		return gold;
	}
	
	@Override
	public void setGold(int gold){
		this.gold = gold;
	}
	
	@Override
	public String getCharacterType(){
		return characterType;
	}
	
	@Override
	public void setCharacterType(String characterType){
		this.characterType = characterType;
	}
	
	@Override
	public int getMaxHealth(){
		return maxHealth;
	}
	
	@Override
	public void setMaxHealth(int maxHealth){
		this.maxHealth = maxHealth;
	}
	
	@Override
	public int getMaxMana(){
		return maxMana;
	}
	
	@Override
	public void setMaxMana(int maxMana){
		this.maxMana = maxMana;
	}

	@Override
	public int getDirection() {
		return direction;
	}

	@Override
	public void setDirection(int direction) {
		this.direction = direction;
	}

	@Override
	public float getSpeed() {
		return speed;
	}

	@Override
	public void setSpeed(float speed) {
		this.speed = speed;
	}
}