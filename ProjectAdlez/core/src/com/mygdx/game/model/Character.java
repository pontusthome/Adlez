package com.mygdx.game.model;

import com.mygdx.game.model.handler.CollisionHandler;

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
	private boolean movingNorth;
	private boolean movingSouth;
	private boolean movingEast;
	private boolean movingWest;
	
	private boolean collidedNorth;
	private boolean collidedSouth;
	private boolean collidedEast;
	private boolean collidedWest;
	
	public Character() {
		this(Direction.NORTH, 2f,
				17, 17,
				0, 0,
				100, 5, 0, 100);
	}
	
	public Character(int direction, float speed,
			   int width, int height,
			   float posX, float posY,
			   int maxHealth, int attackDamage, int gold, int mana) {
		
		super(posX, posY, width, height);
		
		setDirection(direction);
		setSpeed(speed);
		setMaxHealth(maxHealth);
		setHealth(maxHealth);
		setAttackDamage(attackDamage);
		setGold(gold);
		setMana(mana);
		
		movingNorth = false;
		movingSouth = false;
		movingEast = false;
		movingWest = false;
		
		collidedNorth = false;
		collidedSouth = false;
		collidedEast = false;
		collidedWest = false;
	}

	@Override
	public void moveNorth() {
		setPosY(getPosY() + getSpeed());
		setDirection(Direction.NORTH);
		movingNorth = true;
	}
	
	@Override
	public void moveSouth() {
		setPosY(getPosY() - getSpeed());
		setDirection(Direction.SOUTH);
		movingSouth = true;
	}
	
	@Override
	public void moveEast() {
		setPosX(getPosX() + getSpeed());
		setDirection(Direction.EAST);
		movingEast = true;
	}
	
	@Override
	public void moveWest() {
		setPosX(getPosX() - getSpeed());
		setDirection(Direction.WEST);
		movingWest = true;
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
	
	@Override
	public void onCollide(Collidable other){
		if(other instanceof ICharacter && this != other){
			undoCharacterMove();
		}else if(other instanceof IWall){
			undoCharacterMove();
		}else if(other instanceof IObstacle){
			undoCharacterMove();
		}
	}
	
	public void undoCharacterMove(){
//		if(movingNorth){
//			setPosY(getPosY() - getSpeed());
//		}
//		if(movingSouth){
//			setPosY(getPosY() + getSpeed());
//		}
//		if(movingEast){
//			setPosX(getPosX() - getSpeed());
//		}
//		if(movingWest){
//			setPosX(getPosX() + getSpeed());
//		}
		
		if(movingNorth && collidedNorth){
			setPosY(getPosY() - getSpeed());
		}
		if(movingSouth && collidedSouth){
			setPosY(getPosY() + getSpeed());
		}
		if(movingEast && collidedEast){
			setPosX(getPosX() - getSpeed());
		}
		if(movingWest && collidedWest){
			setPosX(getPosX() + getSpeed());
		}
	}
	
	public void clearMoveAndCollisionFlags(){
		movingNorth = false;
		movingSouth = false;
		movingEast = false;
		movingWest = false;
		
		collidedNorth = false;
		collidedSouth = false;
		collidedEast = false;
		collidedWest = false;
	}
	
	public boolean isCollidedNorth(){
		return collidedNorth;
	}
	
	public void setCollidedNorth(boolean collidedNorth){
		this.collidedNorth = collidedNorth;
	}
	
	public boolean isCollidedSouth(){
		return collidedSouth;
	}
	
	public void setCollidedSouth(boolean collidedSouth){
		this.collidedSouth = collidedSouth;
	}
	
	public boolean isCollidedEast(){
		return collidedEast;
	}
	
	public void setCollidedEast(boolean collidedEast){
		this.collidedEast = collidedEast;
	}
	
	public boolean isCollidedWest(){
		return collidedWest;
	}
	
	public void setCollidedWest(boolean collidedWest){
		this.collidedWest = collidedWest;
	}
}
