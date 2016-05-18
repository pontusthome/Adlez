package com.mygdx.game.model;

import com.mygdx.game.utils.AssetStrings;
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
//		if(other instanceof IPlayer && getAttackCooldown() > ATTACK_COOLDOWN_LIMIT){
//			attackPlayer();
//			resetAttackCooldown();
//		}
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
}
