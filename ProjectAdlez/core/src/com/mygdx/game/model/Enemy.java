package com.mygdx.game.model;

import com.mygdx.game.model.handler.CollisionHandler2;
import com.mygdx.game.utils.AssetStrings;
import com.mygdx.game.utils.Utils;

import java.io.Serializable;

/**
 * Created by Michel on 1.5.2016.
 */
public class Enemy extends NPC implements IEnemy{

	public static final int REGULAR_LEVEL_ONE = 1;
	public static final int REGULAR_LEVEL_TWO = 2;
	public static final int DARK_ONE_LEVEL_ONE = 3;
	public static final int DOG_LEVEL_ONE = 4;

	private int type;
	private int range = 70;
	
	private CollisionHandler2 collisionHandler = CollisionHandler2.getInstance();
	private IPlayer player = Adlez.getInstance().getPlayer();

	public Enemy(int direction, float speed, int width, 
				 int height, float posX, float posY, 
				 int maxHealth, int attackDamage, 
				 int gold, int mana, int type){
		
		super(direction, speed, width, height, 
				posX, posY, maxHealth, attackDamage, 
				gold, mana);

		this.type = type;
	}
	
	@Override
	public void onCollide(Collidable other){
		super.onCollide(other);
		if(other instanceof IAttack && ((IAttack) other).byPlayer()){
			IAttack attack = (IAttack) other;
			setHealth(getHealth() - attack.getDamage());
			if(getHealth() <= 0) {
				attack.getCharacter().setGold(attack.getCharacter().getGold() + getGold());
			}
		}
		if(other instanceof IPlayer && getAttackCooldown() > ATTACK_COOLDOWN_LIMIT){
			attackPlayer();
			resetAttackCooldown();
		}
	}

	@Override
	public void update(float deltaT) {
		clearMoveFlags();
		followPlayer(deltaT);
		super.update(deltaT);
	}

	@Override
	public void followPlayer(float deltaT) {
		float playerX = player.getPosX();
		float playerY = player.getPosY();

		boolean inRange = Utils.inRange(playerX, getPosX(), playerY, getPosY(), range);

		if (playerY > getPosY() && Math.abs(playerY - getPosY()) > 1 && inRange) {
			setMovingNorth();
		}else if (playerY < getPosY() && Math.abs(playerY - getPosY()) > 1 && inRange) {
			setMovingSouth();
		}
		if (playerX < getPosX() && Math.abs(playerX - getPosX()) > 1 && inRange) {
			setMovingWest();
		}else if (playerX > getPosX() && Math.abs(playerX - getPosX()) > 1 && inRange) {
			setMovingEast();
		}

		move(deltaT);
	}

	@Override
	public int getType() {
		return type;
	}
	
	private void attackPlayer(){
		IAttack enemyAttack = new EnemyAOEAttack(this);
		enemyAttack.setSound(new LibGDXSoundAdapter(AssetStrings.MELEE_ATTACK_SOUND));
		enemyAttack.playSound(0.1f);
		Adlez.getInstance().addAttack(enemyAttack);
	}
	
	@Override
	public void handleMoveCollision(int direction){
		if (collisionHandler.characterCollided(this)) {
			
			// Preliminary implementation of enemy attack
			if(CollisionHandler2.collide(this, player) && 
					getAttackCooldown() >= ATTACK_COOLDOWN_LIMIT){
				onCollide(player);
			}
			
			switch(direction){
				case Direction.NORTH:
					setPosY(getOldPosY());
					break;
				case Direction.SOUTH:
					setPosY(getOldPosY());
					break;
				case Direction.EAST:
					setPosX(getOldPosX());
					break;
				case Direction.WEST:
					setPosX(getOldPosX());
					break;
			}
		}
	}
}
