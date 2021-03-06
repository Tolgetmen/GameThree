package uy.com.agm.gamethree.assets.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import uy.com.agm.gamethree.screens.PlayScreen;

/**
 * Created by AGM on 12/22/2017.
 */

public class AssetPowerOne {
    private static final String TAG = AssetPowerOne.class.getName();

    // Constants (meters = pixels * resizeFactor / PPM)
    public static final float WIDTH_METERS = 52.0f * 1.0f / PlayScreen.PPM;
    public static final float HEIGHT_METERS = 52.0f * 1.0f / PlayScreen.PPM;

    private TextureRegion powerOneStand;
    private Animation powerOneAnimation;

    public AssetPowerOne(TextureAtlas atlas) {
        Array<TextureAtlas.AtlasRegion> regions;

        powerOneStand = atlas.findRegion("powerOne", 1);

        // Animation
        regions = atlas.findRegions("powerOne");
        powerOneAnimation = new Animation(1.0f / 16.0f, regions, Animation.PlayMode.LOOP);
        regions.clear();
    }

    public TextureRegion getPowerOneStand() {
        return powerOneStand;
    }

    public Animation getPowerOneAnimation() {
        return powerOneAnimation;
    }
}