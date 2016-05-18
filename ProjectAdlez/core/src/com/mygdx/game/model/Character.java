package com.mygdx.game.model;

import com.mygdx.game.model.handler.CollisionHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	private float vX;
	private float vY;
	private int velocityScalar = 50;
	private float oldPosX;
	private float oldPosY;
	
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
		
		oldPosX = getPosX();
		oldPosY = getPosY();
	}

	@Override
	public void moveNorth(float deltaT) {
		oldPosY = getPosY();
		setPosY(getPosY() + getSpeed() * deltaT * velocityScalar);
		setDirection(Direction.NORTH);
		movingNorth = true;
	}
	
	@Override
	public void moveSouth(float deltaT) {
		oldPosY = getPosY();
		setPosY(getPosY() - getSpeed() * deltaT * velocityScalar);
		setDirection(Direction.SOUTH);
		movingSouth = true;
	}
	
	@Override
	public void moveEast(float deltaT) {
		oldPosX = getPosX();
		setPosX(getPosX() + getSpeed() * deltaT * velocityScalar);
		setDirection(Direction.EAST);
		movingEast = true;
	}
	
	@Override
	public void moveWest(float deltaT) {
		oldPosX = getPosX();
		setPosX(getPosX() - getSpeed() * deltaT * velocityScalar);
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
		// Collisions with objects & other characters are handled directly after moving for now
		if(other instanceof ICharacter && this != other){
			undoCharacterMove(other);
		} else if(other instanceof IWall){
			undoCharacterMove(other);
		} else if(other instanceof IObstacle){
			undoCharacterMove(other);
		} else if(other instanceof IChest){
			undoCharacterMove(other);
		} else if(other instanceof IAreaConnection){
			undoCharacterMove(other);
		}
	}
	
	public void undoCharacterMove(Collidable other){
		IWorldObject otherWorldObject = (IWorldObject) other;
		
		if(collisionFromTop(otherWorldObject) || collisionFromBottom(otherWorldObject)){
			setPosY(oldPosY);
			setVy(0);
		}
		if(collisionFromRight(otherWorldObject) || collisionFromLeft(otherWorldObject)){
			setPosX(oldPosX);
			setVx(0);
		}
	}
	
	public void clearMoveFlags(){
		movingNorth = false;
		movingSouth = false;
		movingEast = false;
		movingWest = false;
	}
	
	@Override
	public boolean isMovingNorth(){
		return movingNorth;
	}
	
	@Override
	public boolean isMovingSouth(){
		return movingSouth;
	}
	
	@Override
	public boolean isMovingEast(){
		return movingEast;
	}
	
	@Override
	public boolean isMovingWest(){
		return movingWest;
	}
	
	@Override
	public boolean isAlive(){
		return getHealth() > 0;
	}
	
	private boolean collisionFromTop(IWorldObject other){
		float otherBottom = other.getPosY();
		float oldTop = oldPosY + getHeight();
		float top = getPosY() + getHeight();
		
		return oldTop <= otherBottom &&	top > otherBottom;
	}
	
	private boolean collisionFromBottom(IWorldObject other){
		float otherTop = other.getPosY() + other.getHeight();
		float oldBottom = oldPosY ;
		float bottom = getPosY();
		
		return oldBottom >= otherTop &&	bottom < otherTop;
	}
	
	private boolean collisionFromLeft(IWorldObject other){
		float otherRight = other.getPosX() + other.getWidth();
		float oldLeft = oldPosX;
		float left = getPosX();
		
		return oldLeft >= otherRight && left < otherRight;
	}
	
	private boolean collisionFromRight(IWorldObject other){
		float otherLeft = other.getPosX();
		float oldRight = oldPosX + getWidth();
		float right = getPosX() + getWidth();
		
		return oldRight <= otherLeft && right > otherLeft;
	}
	
	public float getVx(){
		return vX;
	}
	
	public void setVx(float vX){
		this.vX = vX;
	}
	
	public float getVy(){
		return vY;
	}
	
	public void setVy(float vY){
		this.vY = vY;
	}
	
	public void move(float deltaT){
		if(vX > 0){
			moveEast(deltaT);
		}else if(vX < 0){
			moveWest(deltaT);
		}
		
		if(vY > 0){
			moveNorth(deltaT);
		}else if(vY < 0){
			moveSouth(deltaT);
		}
	}
}
