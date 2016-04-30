package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Michel on 30.4.2016.
 */
public class LibGDXSoundAdapter implements GameSound{
	
	private Sound sound;
	
	public LibGDXSoundAdapter(String soundAssetString){
		sound = Gdx.audio.newSound(Gdx.files.internal(soundAssetString));
	}
	
	@Override
	public void play(Float volume){
		sound.play(volume);
	}
	
	@Override
	public void setSound(String soundAssetString){
		sound = Gdx.audio.newSound(Gdx.files.internal(soundAssetString));
	}
}
