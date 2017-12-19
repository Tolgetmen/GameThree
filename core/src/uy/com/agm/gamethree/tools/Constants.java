package uy.com.agm.gamethree.tools;

/**
 * Created by amorales on 14/12/2017.
 */

public class Constants {
    // ---- Game -----

    // GUI Width (pixels)
    // Visible game world is V_WIDTH / PPM meters wide
    public static final int V_WIDTH = 480;

    // GUI Height (pixels)
    // Visible game world is V_WIDTH / PPM meters tall
    public static final int V_HEIGHT = 800;

    // World step parameters
    public static final float WORLD_TIME_STEP = 1 / 60.0f;
    public static final int WORLD_VELOCITY_ITERATIONS = 6;
    public static final int WORLD_POSITION_ITERATIONS = 2;

    // Box2D Scale(Pixels Per Meter)
    public static final float PPM = 100;

    // Game cam velocity (m/s)
    public static final float GAMECAM_VELOCITY = 0.5f;

    // Debug boundaries enabled by default
    public static final boolean DEBUG_BOUNDARIES = true;

    // ---- Dinamic objects -----

    // Location of description file for texture atlas
    public static final String TEXTURE_ATLAS_OBJECTS = "dinamicObjects/dinamicObjects.pack";

    // Box2D Collision Bits
    public static final short BORDERS_BIT = 1;
    public static final short HERO_BIT = 2;
    public static final short OBSTACLE_BIT = 4;
    public static final short POWERBOX_BIT = 8;
    public static final short ENEMY_BIT = 16;
    public static final short ITEM_BIT = 32;
    public static final short WEAPON_BIT = 64;

    // Hero (meters = pixels / PPM)
    public static final float WEIGHTING_HERO_SPEED = 18.0f / PPM;
    public static final float LEN_HERO_SPEED = 5.0f;
    public static final float MAX_LINEAR_VELOCITY = 400f;
    public static final float LINEAR_VELOCITY = 4.0f;
    public static final float HERO_CIRCLESHAPE_RADIUS_METERS = 29.0f / PPM;
    public static final float HERO_WIDTH_METERS = 57.0f / PPM;
    public static final float HERO_HEIGHT_METERS = 71.0f / PPM;

    // PowerBox (meters = pixels / PPM)
    public static final float POWERBOX_CIRCLESHAPE_RADIUS_METERS = 29.0f / PPM;
    public static final float POWERBOX_WIDTH_METERS = 56.0f / PPM;
    public static final float POWERBOX_HEIGHT_METERS = 60.2f / PPM;

    // Item (meters = pixels / PPM)
    public static final float ITEM_OFFSET_METERS = 40.0f / PPM;

    // PowerOne (meters = pixels / PPM)
    public static final float POWERONE_CIRCLESHAPE_RADIUS_METERS = 29.0f / PPM;
    public static final float POWERONE_WIDTH_METERS = 52.0f / PPM;
    public static final float POWERONE_HEIGHT_METERS = 52.0f / PPM;
    public static final float POWERONE_VELOCITY_X = 0.7f;
    public static final float POWERONE_VELOCITY_Y = 0.0f;
    public static final float POWERONE_WAITING_SECONDS = 5.0f;
    public static final float POWERONE_FADING_SECONDS = 5.0f;

    // EnemyOne (meters = pixels / PPM)
    public static final float ENEMYONE_CIRCLESHAPE_RADIUS_METERS = 29.0f / PPM;
    public static final float ENEMYONE_VELOCITY_X = 1.0f;
    public static final float ENEMYONE_VELOCITY_Y = -1.0f;
    public static final float ENEMYONE_WIDTH_METERS = 66.0f / PPM;
    public static final float ENEMYONE_HEIGHT_METERS = 55.0f / PPM;

    // Weapon (meters = pixels / PPM)
    public static final float WEAPON_OFFSET_METERS = 64.0f / PPM;

    // EnergyBall (meters = pixels / PPM)
    public static final float ENERGYBALL_WIDTH_METERS = 19.2f / PPM;
    public static final float ENERGYBALL_HEIGHT_METERS = 60.0f / PPM;
    public static final float ENERGYBALL_VELOCITY_X = 0.0f;
    public static final float ENERGYBALL_VELOCITY_Y = 6.0f;
    public static final float ENERGYBALL_CIRCLESHAPE_RADIUS_METERS = 15.0f / PPM;
}
