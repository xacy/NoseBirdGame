package org.endingloop.gameworld;

import org.endingloop.gameobjects.Nose;
import org.endingloop.gameobjects.ScrollHandler;
import org.endingloop.nbhelpers.AssetLoader;

public class GameWorld {
	private Nose nose;
	private ScrollHandler scroller;
	
	private boolean isAlive = true;
	
	public GameWorld(int midPointY){
		//Initialize nose
		nose = new Nose(33, midPointY - 5, 17, 12);
		 // The grass should start 66 pixels below the midPointY
        scroller = new ScrollHandler(midPointY + 66);
	}
	 
	public void update(float delta) {
		nose.update(delta);
		scroller.update(delta);
		
		if (isAlive && scroller.collides(nose)) {
	        scroller.stop();
	        AssetLoader.dead.play();
	        isAlive = false;
	    }
		
    }
	public Nose getNose(){
		return nose;
	}
    public ScrollHandler getScroller() {
        return scroller;
    }
}
