package org.endingloop.gameworld;

import org.endingloop.gameobjects.Nose;
import org.endingloop.gameobjects.ScrollHandler;
import org.endingloop.nbhelpers.AssetLoader;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class GameWorld {
	private Nose nose;
	private ScrollHandler scroller;
	
	private Rectangle ground,sky;
	
	private int score = 0;
	
	public GameWorld(int midPointY){
		//Initialize nose
		nose = new Nose(33, midPointY - 5, 17, 12);
		 // The grass should start 66 pixels below the midPointY
        scroller = new ScrollHandler(this,midPointY + 66);
        
        ground = new Rectangle(0, midPointY + 66, 136, 11);
        sky = new Rectangle(0, 0, 136, 1);
	}
	 
	public void update(float delta) {
		
		if (delta > .15f) {
            delta = .15f;
        }
		
		nose.update(delta);
		scroller.update(delta);
		
		if (nose.isAlive() && scroller.collides(nose) || Intersector.overlaps(nose.getBoundingCircle(), sky)) {
	        scroller.stop();
	        AssetLoader.dead.play();
	        nose.die();
	    }
		if (Intersector.overlaps(nose.getBoundingCircle(), ground)) {
            scroller.stop();
            nose.die();
            nose.decelerate();
        }
		
    }
	public Nose getNose(){
		return nose;
	}
    public ScrollHandler getScroller() {
        return scroller;
    }
    
    public int getScore() {
        return score;
    }
    
    public void addScore(int increment) {
        score += increment;
    }
}
