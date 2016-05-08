package com.mygdx.game.model;

/**
 * Created by Michel on 8.5.2016.
 */
public abstract class Action extends WorldObject implements IAction{
	
	private HitBox hitBox = new HitBox();
	private GameSound sound;
	private boolean isFinished;
	private ICharacter character;
	
	public Action(){
		
	}
	
	/** Set position of action in front of character. For now also set size the same as character's size */
	public Action(ICharacter character){
		this.character = character;
		setInitLocation(character);
		hitBox = new HitBox(getPosX(), getPosY(), getWidth(), getHeight());
	}
	
	@Override
	public HitBox getHitBox(){
		return hitBox;
	}
	
	@Override
	public boolean isFinished(){
		return isFinished;
	}
	
	@Override
	public void setFinished(){
		isFinished = true;
	}
	
	@Override
	public void setSound(GameSound sound){
		this.sound = sound;
	}
	
	@Override
	public GameSound getSound(){
		return sound;
	}
	
	public void playSound(float volume){
		sound.play(volume);
	}
	
	@Override
	public ICharacter getCharacter(){
		return character;
	}
}
