package org.endingloop.nosebird;

import org.endingloop.screens.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class NBGame extends Game{

	@Override
	public void create() {
		// TODO Auto-generated method stub
		Gdx.app.log("NBGame", "created");
		setScreen(new GameScreen());
	}
	
}
