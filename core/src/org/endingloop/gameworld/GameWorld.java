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
	private float runTime = 0;
	private int midPointY;
	
	private GameState currentState;
	
    public enum GameState {

        MENU,READY, RUNNING, GAMEOVER, HIGHSCORE

    }
	
	public GameWorld(int midPointY){

        currentState = GameState.MENU;
        this.midPointY = midPointY;
		//Initialize nose
		nose = new Nose(33, midPointY - 5, 17, 12);
		 // The grass should start 66 pixels below the midPointY
        scroller = new ScrollHandler(this,midPointY + 66);
        
        ground = new Rectangle(0, midPointY + 66, 136, 11);
        sky = new Rectangle(0, 0, 136, 1);

	}
	
	public void update(float delta) {
		runTime += delta;

        switch (currentState) {
        case MENU:
        case READY:
            updateReady(delta);
            break;

        case RUNNING:
        	updateRunning(delta);
            break;
        default:
            break;
        }

    }
	 
	public void updateRunning(float delta) {
		
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
            currentState = GameState.GAMEOVER;
            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }
		
		
    }

    private void updateReady(float delta) {
        // Do nothing for now
    	nose.updateReady(runTime);
        scroller.updateReady(delta);
    }
	
	public Nose getNose(){
		return nose;
	}
	public int getMidPointY() {
        return midPointY;
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
    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void ready() {
        currentState = GameState.READY;
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        nose.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }
    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

}
