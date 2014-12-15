package org.endingloop.nbhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	public static Texture texture;
    public static TextureRegion bg, grass;

    public static Animation noseAnimation;
    public static TextureRegion nose, noseDown, noseUp;

    public static TextureRegion skullUp, skullDown, bar;
    
    public static Sound dead;
    
    public static void load() {

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        noseDown = new TextureRegion(texture, 136, 0, 17, 12);
        noseDown.flip(false, true);

        nose = new TextureRegion(texture, 153, 0, 17, 12);
        nose.flip(false, true);

        noseUp = new TextureRegion(texture, 170, 0, 17, 12);
        noseUp.flip(false, true);

        TextureRegion[] noses = { noseDown, nose, noseUp };
        noseAnimation = new Animation(0.06f, noses);
        noseAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);
        
        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));

    }
    
    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
    }

}
