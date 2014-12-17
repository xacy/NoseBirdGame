package org.endingloop.nosebird;

import org.endingloop.nbhelpers.AssetLoader;
import org.endingloop.screens.GameScreen;
import org.endingloop.screens.SplashScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class NBGame extends Game{

	@Override
	public void create() {
		// TODO Auto-generated method stub
		Gdx.app.log("NBGame", "created");
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}
	
	 @Override
	    public void dispose() {
	        super.dispose();
	        AssetLoader.dispose();
	    }
	
}
