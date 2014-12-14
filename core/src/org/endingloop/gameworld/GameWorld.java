package org.endingloop.gameworld;

import org.endingloop.gameobjects.Nose;

public class GameWorld {
	private Nose nose;
	
	public GameWorld(int midPointY){
		//Initialize nose
		nose = new Nose(33, midPointY - 5, 17, 12);
	}
	 
	public void update(float delta) {
			nose.update(delta);
    }
	public Nose getNose(){
		return nose;
	}
}
