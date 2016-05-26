package com.mygdx.game.model.characters;

import com.mygdx.game.model.core.ObservableWorldObject;
import com.mygdx.game.model.core.WorldObjectObserver;
import com.mygdx.game.model.core.Collidable;
import com.mygdx.game.model.core.Direction;
import com.mygdx.game.model.core.WorldObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michel on 2016-04-19.
 */
public abstract class Character extends WorldObject implements ICharacter, ObservableWorldObject{

	private int attackDamage;
	private int health;
	private int maxHealth;
	private int mana;
	private int maxMana;
	private int level;
	private int gold;
	private String name;
	private int direction;
	private float speed;
	private boolean movingNorth;
	private boolean movingSouth;
	private boolean movingEast;
	private boolean movingWest;
	private int velocityScalar = 50;
	private float oldPosX;
	private float oldPosY;
	private boolean moved;
	private List<WorldObjectObserver> observers;
	
	/** Speed components in x & y. Are set to 1 if only moving in either x or y, otherwise adjusted so 
	 * that the character doesn't move faster when moving diagonally.
	 */
	private float vXComponent = 0;
	private float vYComponent = 0;
	
	/** For debugging purposes
	 public IAttack latestAttack = new MeleeAttack();
	 public IInteraction latestInteraction = new Interaction();
	 */
	
	
	/**
	 * Cooldown for attacking so that a character isn't allowed to attack non-sto . Only used for enemy attacks for 
	 * now, the player is allowed to attack as fast as the user can press a button, so to speak.
	 */
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
		
		clearMoveFlags();
		
		oldPosX = getPosX();
		oldPosY = getPosY();
		
		observers = new ArrayList<>();
	}

	@Override
	public void moveNorth(float deltaT) {
		oldPosY = getPosY();
		setPosY(getPosY() + vYComponent * getSpeed() * deltaT * velocityScalar);
		setDirection(Direction.NORTH);
		notifyObservers("check_collision");
	}
	
	@Override
	public void moveSouth(float deltaT) {
		oldPosY = getPosY();
		setPosY(getPosY() - vYComponent * getSpeed() * deltaT * velocityScalar);
		setDirection(Direction.SOUTH);
		notifyObservers("check_collision");
	}
	
	@Override
	public void moveEast(float deltaT) {
		oldPosX = getPosX();
		setPosX(getPosX() + vXComponent * getSpeed() * deltaT * velocityScalar);
		setDirection(Direction.EAST);
		notifyObservers("check_collision");
	}
	
	@Override
	public void moveWest(float deltaT) {
		oldPosX = getPosX();
		setPosX(getPosX() - vXComponent * getSpeed() * deltaT * velocityScalar);
		setDirection(Direction.WEST);
		notifyObservers("check_collision");
	}

	@Override
	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	@Override
	public boolean moved() {
		return moved;
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
		if (health < 0) {
			health = 0;
		}
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
	
	private void clearMoveFlags(){
		movingNorth = false;
		movingSouth = false;
		movingEast = false;
		movingWest = false;
	}
	
	@Override
	public boolean isAlive(){
		return getHealth() > 0;
	}
	
	@Override
	public void move(float deltaT){
		/** Check if moving diagonally. If so, set x & y speed components so that the total speed is equal to the 
		 *  characters speed.
		 */
		if((movingWest || movingEast) && (movingNorth || movingSouth)){
			vXComponent = (float) Math.cos(Math.toRadians(45));
			vYComponent = (float) Math.cos(Math.toRadians(45));
		}else{
			vXComponent = 1;
			vYComponent = 1;
		}
		
		if(movingNorth){
			moveNorth(deltaT);
		}else if(movingSouth){
			moveSouth(deltaT);
		}
		
		if(movingEast){
			moveEast(deltaT);
		}else if(movingWest){
			moveWest(deltaT);
		}

		setMoved(false);
		if (	movingSouth ||
				movingNorth ||
				movingWest ||
				movingEast) {
			setMoved(true);
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
		move(deltaT);
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
	
	@Override
	public void handleMoveCollision(){
		switch(getDirection()){
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
	
	@Override
	public void meleeAttack(){
		notifyObservers("melee_attack");
	}
	
	@Override
	public void aoeMeleeAttack(){
		notifyObservers("aoe_melee_attack");
	}
	
	@Override
	public void aoeMagicAttack(){
		notifyObservers("aoe_magic_attack");
	}
	
	@Override
	public void rangeMagicAttack(){
		notifyObservers("range_magic_attack");
	}
	
	@Override
	public void interact(){
		notifyObservers("interaction");
	}
	
	@Override
	public void useMana(int manaUsage) {
		setMana(getMana() - manaUsage);
	}

	/** For debugging purposes
	public IAttack getLatestAttack(){
		return latestAttack;
	}
	 
	public IInteraction getLatestInteraction(){
		return latestInteraction;
	}
	 
	 */
	@Override
	public void addObserver(WorldObjectObserver observer){
		if(!observers.contains(observer)){
			observers.add(observer);
		}
	}
	
	@Override
	public void removeObserver(WorldObjectObserver observer){
		observers.remove(observer);
	}
	
	@Override
	public void notifyObservers(String action){
		for(WorldObjectObserver observer : observers){
			observer.update(this, action);
		}
	}
}
