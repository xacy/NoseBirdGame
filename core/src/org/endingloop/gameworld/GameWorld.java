package org.endingloop.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Circle;

public class GameWorld {
	 private Rectangle rect = new Rectangle(0, 0, 17, 12);
	 private Circle circ = new Circle(0,184, 20);
	 
	public void update(float delta) {
        Gdx.app.log("GameWorld", "update");
        rect.x++;
        circ.x++;
        if (rect.x > 137) {
            rect.x = 0;
        }
        if(circ.x>137){
        	circ.x=0;
        }
    }
	public Rectangle getRect() {
	        return rect;
	}
	public Circle getCirc(){
		return circ;
	}
}
