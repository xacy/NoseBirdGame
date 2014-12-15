package org.endingloop.gameworld;

import org.endingloop.gameobjects.Grass;
import org.endingloop.gameobjects.Nose;
import org.endingloop.gameobjects.Pipe;
import org.endingloop.gameobjects.ScrollHandler;
import org.endingloop.nbhelpers.AssetLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameRenderer {
	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	
	private SpriteBatch batcher;
	
	private int midPointY;
    private int gameHeight;
    
    // 	Game Objects
    private Nose nose;
    private ScrollHandler scroller;
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;

    // Game Assets
    private TextureRegion bg, grass;
    private Animation noseAnimation; 
    private TextureRegion noseMid, noseDown, noseUp;
    private TextureRegion skullUp, skullDown, bar;
	
	public GameRenderer(GameWorld world, int gameHeight, int midPointY){
		myWorld=world;
		
		// The word "this" refers to this instance.
        // We are setting the instance variables' values to be that of the
        // parameters passed in from GameScreen.
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);
		
		batcher = new SpriteBatch();
        // Attach batcher to camera
        batcher.setProjectionMatrix(cam.combined);
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		// Call helper methods to initialize instance variables
        initGameObjects();
        initAssets();
	}
	
	public void render(float runTime) {

		 Gdx.gl.glClearColor(0, 0, 0, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	        shapeRenderer.begin(ShapeType.Filled);

	        // Draw Background color
	        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
	        shapeRenderer.rect(0, 0, 136, midPointY + 66);

	        // Draw Grass
	        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
	        shapeRenderer.rect(0, midPointY + 66, 136, 11);

	        // Draw Dirt
	        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
	        shapeRenderer.rect(0, midPointY + 77, 136, 52);

	        shapeRenderer.end();

	        batcher.begin();
	        batcher.disableBlending();
	        batcher.draw(bg, 0, midPointY + 23, 136, 43);

	        // 1. Draw Grass
	        drawGrass();

	        // 2. Draw Pipes
	        drawPipes();
	        batcher.enableBlending();

	        // 3. Draw Skulls (requires transparency)
	        drawSkulls();

	        if (nose.shouldntFlap()) {
	            batcher.draw(noseMid, nose.getX(), nose.getY(),
	                    nose.getWidth() / 2.0f, nose.getHeight() / 2.0f,
	                    nose.getWidth(), nose.getHeight(), 1, 1, nose.getRotation());

	        } else {
	            batcher.draw(noseAnimation.getKeyFrame(runTime), nose.getX(),
	                    nose.getY(), nose.getWidth() / 2.0f,
	                    nose.getHeight() / 2.0f, nose.getWidth(), nose.getHeight(),
	                    1, 1, nose.getRotation());
	        }

	        batcher.end();

    }
	
	 private void initGameObjects() {
        nose = myWorld.getNose();
        scroller = myWorld.getScroller();
        frontGrass = scroller.getFrontGrass();
        backGrass = scroller.getBackGrass();
        pipe1 = scroller.getPipe1();
        pipe2 = scroller.getPipe2();
        pipe3 = scroller.getPipe3();        
    }

    private void initAssets() {
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        noseAnimation = AssetLoader.noseAnimation;
        noseMid = AssetLoader.nose;
        noseDown = AssetLoader.noseDown;
        noseUp = AssetLoader.noseUp;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
    }
    private void drawGrass() {
        // Draw the grass
        batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
                frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(grass, backGrass.getX(), backGrass.getY(),
                backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls() {
        // Temporary code! Sorry about the mess :)
        // We will fix this when we finish the Pipe class.

        batcher.draw(skullUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        batcher.draw(skullUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        batcher.draw(skullUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
    }

    private void drawPipes() {
        // Temporary code! Sorry about the mess :)
        // We will fix this when we finish the Pipe class.
        batcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        batcher.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

        batcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        batcher.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

        batcher.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        batcher.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
    }
}
