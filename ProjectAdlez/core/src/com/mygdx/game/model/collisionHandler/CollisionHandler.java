package com.mygdx.game.model.collisionHandler;

import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.WorldObjectObserver;
import com.mygdx.game.model.characters.ICharacter;
import com.mygdx.game.model.characters.IEnemy;
import com.mygdx.game.model.characters.IPlayer;
import com.mygdx.game.model.characters.actions.IAttack;
import com.mygdx.game.model.characters.actions.IInteraction;
import com.mygdx.game.model.core.IWorldObject;

import java.util.List;

/**
 * Created by Michel on 30.4.2016.
 */
public class CollisionHandler implements WorldObjectObserver{
	
	private List<IWorldObject> worldObjects;
	private List<IAttack> attacks;
	private List<IInteraction> interactions;
	private static CollisionHandler collisionHandler = new CollisionHandler();
	
	public static CollisionHandler getInstance() {
		return collisionHandler;
	}
	
	private CollisionHandler(){
		
	}
	
	public void initiate(List<IWorldObject> worldObjects,
						 List<IAttack> attacks, List<IInteraction> interactions){
		this.worldObjects = worldObjects;
		this.attacks = attacks;
		this.interactions = interactions;
	}

	public void updateAttacks() {
		updateWorld(attacks);
	}

	public void updateInteractions() {
		updateWorld(interactions);
	}

	public void updateWorld(List<?> generics) {
		for (Object generic: generics) {
			IWorldObject object = (IWorldObject) generic;
			for (IWorldObject other: worldObjects) {
				if(other != object){
					checkCollision(other, object);
				}
			}
		}
	}
	
	private void checkCollision(IWorldObject primary, IWorldObject other){
		if(collide(primary, other)){
			primary.onCollide(other);
		}
	}
	
	public static boolean collide(IWorldObject primary, IWorldObject other) {
		float width = primary.getWidth();
		float height = primary.getHeight();
		float otherWidth = other.getWidth();
		float otherHeight = other.getHeight();
		if (otherWidth <= 0 || otherHeight <= 0 || width <= 0 || height <= 0) {
			return false;
		}
		float x = primary.getPosX();
		float y = primary.getPosY();
		float otherX = other.getPosX();
		float otherY = other.getPosY();
		otherWidth += otherX;
		otherHeight += otherY;
		width += x;
		height += y;
		//      overflow || intersect
		return ((otherWidth < otherX || otherWidth > x) &&
				(otherHeight < otherY || otherHeight > y) &&
				(width < x || width > otherX) &&
				(height < y || height > otherY));
	}
	
	/** Method used for only when checking if a character has collided directly after a move action */
	private boolean hasCharacterCollided(ICharacter character){
		for (IWorldObject otherObject: worldObjects) {
			if (!otherObject.equals(character) && character.collide(otherObject)) {
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public void update(IWorldObject object, Object arg){
		if(object instanceof ICharacter && arg instanceof String){
			ICharacter character = (ICharacter) object;
			String stringArg = (String) arg;
			if(stringArg.equals("collision_check") && hasCharacterCollided(character)){
				
				// Due to lack of good AI, for now an enemy attacks the player if they have collided
				if(character instanceof IEnemy && collide(character, Adlez.getInstance().getPlayer())){
					IEnemy enemy = (IEnemy) character;
					enemy.attackPlayer();
				}
				character.handleMoveCollision();
			}
		}
	}
}
