package uy.com.agm.gamethree.assets.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import uy.com.agm.gamethree.screens.PlayScreen;

/**
 * Created by AGM on 12/22/2017.
 */

public class AssetHero {
    private static final String TAG = AssetHero.class.getName();

    // Constants (meters = pixels * resizeFactor / PPM)
    public static final float WIDTH_METERS = 128.0f * 0.7f / PlayScreen.PPM;
    public static final float HEIGHT_METERS = 128.0f * 0.7f / PlayScreen.PPM;

    private TextureRegion heroStandUp;
    private TextureRegion heroStandDown;
    private TextureRegion heroHead;
    private Animation heroDeadAnimation;
    private Animation heroMovingUpAnimation;
    private Animation heroMovingDownAnimation;
    private Animation heroMovingLeftRightAnimation;

    public AssetHero(TextureAtlas atlas) {
        Array<TextureAtlas.AtlasRegion> regions;

        heroStandUp = atlas.findRegion("heroUp", 1);
        heroStandDown = atlas.findRegion("heroDown", 1);
        heroHead = atlas.findRegion("heroHead");

        // Animation
        regions = atlas.findRegions("heroUp");
        heroMovingUpAnimation = new Animation(0.5f / 18.0f, regions, Animation.PlayMode.LOOP);
        regions.clear();

        // Animation
        regions = atlas.findRegions("heroDown");
        heroMovingDownAnimation = new Animation(0.5f / 18.0f, regions, Animation.PlayMode.LOOP);
        regions.clear();

        // Animation
        regions = atlas.findRegions("heroUp");
        heroMovingLeftRightAnimation = new Animation(0.5f / 18.0f, regions, Animation.PlayMode.LOOP); // We use the same animation as heroMovingUpAnimation
        regions.clear();

        // Animation
        regions = atlas.findRegions("heroDead");
        heroDeadAnimation = new Animation(0.5f / 12.0f, regions, Animation.PlayMode.LOOP);
        regions.clear();
    }

    public TextureRegion getHeroStandUp() {
        return heroStandUp;
    }

    public TextureRegion getHeroStandDown() {
        return heroStandDown;
    }

    public TextureRegion getHeroHead() {
        return heroHead;
    }

    public Animation getHeroDeadAnimation() {
        return heroDeadAnimation;
    }

    public Animation getHeroMovingUpAnimation() {
        return heroMovingUpAnimation;
    }

    public Animation getHeroMovingDownAnimation() {
        return heroMovingDownAnimation;
    }

    public Animation getHeroMovingLeftRightAnimation() {
        return heroMovingLeftRightAnimation;
    }
}
