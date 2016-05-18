package com.mygdx.game.model;

import com.mygdx.game.model.handler.CollisionHandler;
import com.mygdx.game.model.handler.CollisionHandler2;

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
	private int velocityScalar = 50;
	private float oldPosX;
	private float oldPosY;
	private CollisionHandler2 collisionHandler;
	private float vXComponent = 0;
	private float vYComponent = 0;
	
	// Temporary values for cooldown, should maybe be set in constructor or defined as a constant somewhere
	public static final float ATTACK_COOLDOWN_LIMIT = 2;	//In seconds
	private float attackCooldown = ATTACK_COOLDOWN_LIMIT;
	
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
		
		collisionHandler = CollisionHandler2.getInstance();
	}

	@Override
	public void moveNorth(float deltaT) {
		oldPosY = getPosY();
		setPosY(getPosY() + vYComponent * getSpeed() * deltaT * velocityScalar);
		setDirection(Direction.NORTH);
		movingNorth = true;
		handleMoveCollision(Direction.NORTH);
	}
	
	@Override
	public void moveSouth(float deltaT) {
		oldPosY = getPosY();
		setPosY(getPosY() - vYComponent * getSpeed() * deltaT * velocityScalar);
		setDirection(Direction.SOUTH);
		movingSouth = true;
		handleMoveCollision(Direction.SOUTH);
	}
	
	@Override
	public void moveEast(float deltaT) {
		oldPosX = getPosX();
		setPosX(getPosX() + vXComponent * getSpeed() * deltaT * velocityScalar);
		setDirection(Direction.EAST);
		movingEast = true;
		handleMoveCollision(Direction.EAST);
	}
	
	@Override
	public void moveWest(float deltaT) {
		oldPosX = getPosX();
		setPosX(getPosX() - vXComponent * getSpeed() * deltaT * velocityScalar);
		setDirection(Direction.WEST);
		movingWest = true;
		handleMoveCollision(Direction.WEST);
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
	
	public void move(float deltaT){
		// Check if moving in two directions. If so, modify x & y speed components.
		if((isMovingWest() || isMovingEast()) && (isMovingNorth() || isMovingSouth())){
			vXComponent = (float) Math.cos(Math.toRadians(45));
			vYComponent = (float) Math.cos(Math.toRadians(45));
		}else{
			vXComponent = 1;
			vYComponent = 1;
		}
		
		if(isMovingNorth()){
			moveNorth(deltaT);
		}else if(isMovingSouth()){
			moveSouth(deltaT);
		}
		
		if(isMovingEast()){
			moveEast(deltaT);
		}else if(isMovingWest()){
			moveWest(deltaT);
		}
		
		clearMoveFlags();
	}
	
	@Override
	public void setMovingNorth(){
		movingNorth = true;
	}
	
	@Override
	public void setMovingSouth(){
		movingSouth = true;
	}
	
	@Override
	public void setMovingEast(){
		movingEast = true;
	}
	
	@Override
	public void setMovingWest(){
		movingWest = true;
	}
	
	@Override
	public void update(float deltaT){
		attackCooldown += deltaT;
	}
		
	@Override
	public void setAttackCooldown(float attackCooldown){
		this.attackCooldown = attackCooldown;
	}
	
	@Override
	public float getAttackCooldown(){
		return attackCooldown;
	}
	
	public void resetAttackCooldown(){
		attackCooldown = 0;
	}
	
	public void handleMoveCollision(int direction){
		if (collisionHandler.characterCollided(this)) {
			switch(direction){
				case Direction.NORTH:
					setPosY(oldPosY);
					break;
				case Direction.SOUTH:
					setPosY(oldPosY);
					break;
				case Direction.EAST:
					setPosX(oldPosX);
					break;
				case Direction.WEST:
					setPosX(oldPosX);
					break;
			}
		}
	}
	
	public float getOldPosX(){
		return oldPosX;
	}
	
	public void setOldPosX(float oldPosX){
		this.oldPosX = oldPosX;
	}
	
	public float getOldPosY(){
		return oldPosY;
	}
	
	public void setOldPosY(float oldPosY){
		this.oldPosY = oldPosY;
	}
}
