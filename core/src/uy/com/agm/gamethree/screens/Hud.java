package uy.com.agm.gamethree.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.Scaling;

import uy.com.agm.gamethree.assets.Assets;
import uy.com.agm.gamethree.game.Constants;
import uy.com.agm.gamethree.screens.util.ScreenEnum;
import uy.com.agm.gamethree.screens.util.ScreenManager;
import uy.com.agm.gamethree.tools.AudioManager;
import uy.com.agm.gamethree.widget.HealthBar;

/**
 * Created by AGM on 1/18/2018.
 */

public class Hud extends AbstractScreen {
    private static final String TAG = Hud.class.getName();

    private PlayScreen screen;

    private I18NBundle i18NGameThreeBundle;
    private Label.LabelStyle labelStyleBig;
    private Label.LabelStyle labelStyleSmall;

    private Table upperTable;
    private int score;
    private Label scoreValueLabel;
    private int time;
    private float timeCount;
    private Label timeValueLabel;
    private int lives;
    private Label livesValueLabel;
    private int silverBullets;
    private Label silverBulletValueLablel;

    private Table powerTable;
    private Label powerNameLabel;
    private int powerTime;
    private float powerTimeCount;
    private Label powerTimeValueLabel;

    private Table centerTable;
    private Label messageLabel;
    private Image image;
    private float overlayTimer;
    private float overlaySeconds;
    private boolean overlayTemporaryScreen;

    private Table bottomTable;

    private Table fpsTable;
    private int fps;
    private Label fpsValueLabel;

    private Table healthBarTable;
    private Label enemyNameLabel;
    private HealthBar healthBar;

    private Table buttonsTable;
    private Label pauseLabel;
    private Label resumeLabel;
    private Label quitLabel;

    private boolean timeIsUp; // True when the level time reaches 0

    private Stack stack;

    public Hud(PlayScreen screen, Integer time, Integer lives) {
        super();

        // Define tracking variables
        this.screen = screen;
        score = 0;
        this.time = time;
        timeCount = 0;
        this.lives = lives;
        silverBullets = 0;
        powerTime = 0;
        powerTimeCount = 0;
        fps = 0;
        healthBar = new HealthBar();
        timeIsUp = false;
        overlayTimer = 0;
        overlaySeconds = 0;
        overlayTemporaryScreen = false;

        // I18n
        i18NGameThreeBundle = Assets.getInstance().getI18NGameThree().getI18NGameThreeBundle();

        // Personal fonts
        labelStyleBig = new Label.LabelStyle();
        labelStyleBig.font = Assets.getInstance().getFonts().getDefaultBig();

        labelStyleSmall = new Label.LabelStyle();
        labelStyleSmall.font = Assets.getInstance().getFonts().getDefaultSmall();
    }

    private void defineUpperTable() {
        // Define a table used to organize our hud's labels
        upperTable = new Table();

        // Cell height
        upperTable.row().height(Constants.HUD_UPPERTABLE_CELL_HEIGHT);

        // Debug lines
        upperTable.setDebug(Constants.DEBUG_MODE);

        // Top-Align table
        upperTable.top();

        // Make the table fill the entire stage
        upperTable.setFillParent(true);

        // Define our labels based on labelStyle
        Label scoreLabel = new Label(i18NGameThreeBundle.format("hud.score"), labelStyleSmall);
        Label timeLabel = new Label(i18NGameThreeBundle.format("hud.time"), labelStyleSmall);

        // Add labels to the table giving them all equal width with expandX
        upperTable.add(scoreLabel).expandX();
        upperTable.add(timeLabel).expandX();
        upperTable.add(new Image(new TextureRegionDrawable(Assets.getInstance().getHero().getHeroHead()), Scaling.fit)).expandX();
        upperTable.add(new Image(new TextureRegionDrawable(Assets.getInstance().getSilverBullet().getSilverBulletStand()), Scaling.fit)).expandX();

        // Add a second row to our table
        upperTable.row().height(Constants.HUD_UPPERTABLE_CELL_HEIGHT);

        // Define label values based on labelStyle
        scoreValueLabel = new Label(String.format(Constants.HUD_FORMAT_SCORE, score), labelStyleSmall);
        timeValueLabel = new Label(String.format(Constants.HUD_FORMAT_TIME, time), labelStyleSmall);
        livesValueLabel = new Label(String.format(Constants.HUD_FORMAT_LIVES, lives), labelStyleSmall);
        silverBulletValueLablel = new Label(String.format(Constants.HUD_FORMAT_SILVER_BULLETS, silverBullets), labelStyleSmall);

        // Add values
        upperTable.add(scoreValueLabel).expandX();
        upperTable.add(timeValueLabel).expandX();
        upperTable.add(livesValueLabel).expandX();
        upperTable.add(silverBulletValueLablel).expandX();

        // Add a third row to our table
        upperTable.row();

        // Add power info
        definePowerTable();
        upperTable.add(powerTable).colspan(upperTable.getColumns());

        // Add table to the stage
        addActor(upperTable);
    }

    private void definePowerTable() {
        // Define a table used to organize the power info
        powerTable = new Table();

        // Cell height
        powerTable.row().height(Constants.HUD_UPPERTABLE_CELL_HEIGHT);

        // Debug lines
        powerTable.setDebug(Constants.DEBUG_MODE);

        // Define a label based on labelStyle
        powerNameLabel = new Label("POWERNAME", labelStyleSmall);

        // Add values
        powerTable.add(powerNameLabel);

        // Add a second row to the table
        powerTable.row().height(Constants.HUD_UPPERTABLE_CELL_HEIGHT);

        // Define a label based on labelStyle
        powerTimeValueLabel = new Label(String.format(Constants.HUD_FORMAT_POWER_TIME, powerTime), labelStyleSmall);

        // Add values
        powerTable.add(powerTimeValueLabel);

        // Initially hidden
        powerTable.setVisible(false);
    }

    private void defineCenterTable() {
        // Define a new table used to display a message
        centerTable = new Table();

        // Debug lines
        centerTable.setDebug(Constants.DEBUG_MODE);

        // Center-Align table
        centerTable.center();

        // Make the table fill the entire stage
        centerTable.setFillParent(true);

        // Define a label based on labelStyle
        messageLabel = new Label("MESSAGE", labelStyleBig);
        image = new Image();

        // Add values
        stack = new Stack();
        stack.add(messageLabel);
        stack.add(image);
        centerTable.add(stack);

        // Add our table to the stage
        addActor(centerTable);

        // Initially hidden
        messageLabel.setVisible(false);
        image.setVisible(false);
        centerTable.setVisible(false);
    }

    private void defineBottomTable() {
        // Define a new table used to display a FPS counter and an Enemy's health bar
        bottomTable = new Table();

        // Debug lines
        bottomTable.setDebug(Constants.DEBUG_MODE);

        // Bottom-Align table
        bottomTable.bottom();

        // Make the table fill the entire stage
        bottomTable.setFillParent(true);

        // Add health bar info
        defineHealthBarTable();
        bottomTable.add(healthBarTable);

        // FPS info
        defineFpsTable();

        if (Constants.DEBUG_MODE) {
            // Add a second row to the table
            bottomTable.row();
            // Add FPS info
            bottomTable.add(fpsTable);
        }

        // Add the table to the stage
        addActor(bottomTable);
    }

    private void defineFpsTable() {
        // Define a new table used to display a FPS counter
        fpsTable = new Table();

        // Debug lines
        fpsTable.setDebug(Constants.DEBUG_MODE);

        // Define a label based on labelStyle
        Label fpsLabel = new Label(i18NGameThreeBundle.format("hud.FPS"), labelStyleSmall);

        // Add a label to the table
        fpsTable.add(fpsLabel);

        // Add a second row to our table
        fpsTable.row();

        // Define a label value based on labelStyle
        fpsValueLabel = new Label(String.format(Constants.HUD_FORMAT_POWER_TIME, fps), labelStyleSmall);

        // Add value
        fpsTable.add(fpsValueLabel);

        // Hidden if not in debug mode
        fpsTable.setVisible(Constants.DEBUG_MODE);
    }

    private void defineHealthBarTable() {
        // Define a new table used to display an enemy's health bar
        healthBarTable = new Table();

        // Debug lines
        healthBarTable.setDebug(Constants.DEBUG_MODE);

        // Define a label based on labelStyle
        enemyNameLabel = new Label("ENEMY_NAME", labelStyleSmall);

        // Add value
        healthBarTable.add(enemyNameLabel);

        // Add a second row to our table
        healthBarTable.row();

        // Add health bar
        healthBarTable.add(healthBar).padBottom(Constants.HUD_HEALTHBAR_PADBOTTOM);

        // Initially hidden
        healthBarTable.setVisible(false);
    }

    private void defineButtonsTable() {
        // Define a new table used to display pause, resume and quit buttons
        buttonsTable = new Table();

        // Debug lines
        buttonsTable.setDebug(Constants.DEBUG_MODE);

        // Bottom-Align table
        buttonsTable.bottom().padLeft(Constants.HUD_BUTTONS_PAD).padRight(Constants.HUD_BUTTONS_PAD);

        // Make the container fill the entire stage
        buttonsTable.setFillParent(true);

        // Define labels based on labelStyle
        pauseLabel = new Label(i18NGameThreeBundle.format("hud.pause"), labelStyleSmall);
        quitLabel = new Label(i18NGameThreeBundle.format("hud.quit"), labelStyleSmall);
        quitLabel.setAlignment(Align.right);
        resumeLabel = new Label(i18NGameThreeBundle.format("hud.resume"), labelStyleSmall);

        // Add values
        stack = new Stack();
        stack.add(pauseLabel);
        stack.add(resumeLabel);
        buttonsTable.add(stack).width(Constants.HUD_BUTTON_WIDTH).left().expandX(); // Pause and Resume texts overlapped
        buttonsTable.add(quitLabel).width(Constants.HUD_BUTTON_WIDTH).right().expandX();

        // Events
        pauseLabel.addListener(
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        // Audio FX
                        AudioManager.getInstance().play(Assets.getInstance().getSounds().getClick());
                        setGameStatePaused();
                        return true;
                    }
                });

        resumeLabel.addListener(
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        // Audio FX
                        AudioManager.getInstance().play(Assets.getInstance().getSounds().getClick());
                        setGameStateRunning();
                        return true;
                    }
                });

        quitLabel.addListener(
                new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        // Audio FX
                        AudioManager.getInstance().play(Assets.getInstance().getSounds().getClick());
                        quit();
                        return true;
                    }
                });

        // Add the table to the stage
        addActor(buttonsTable);

        // Initially hidden
        resumeLabel.setVisible(false);
        quitLabel.setVisible(false);
    }

    public void setGameStatePaused() {
        pauseLabel.setVisible(false);
        resumeLabel.setVisible(true);
        quitLabel.setVisible(true);
        showMessage(i18NGameThreeBundle.format("hud.pauseMessage"));
        screen.setPlayScreenStatePaused();
    }

    public void setGameStateRunning() {
        pauseLabel.setVisible(true);
        resumeLabel.setVisible(false);
        quitLabel.setVisible(false);
        hideMessage();
        screen.setPlayScreenStateRunning();
    }

    private void quit() {
        if (getMessage().equals(i18NGameThreeBundle.format("hud.confirm"))) {
            ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
        } else {
            showMessage(i18NGameThreeBundle.format("hud.confirm"));
        }
    }

    @Override
    public void buildStage() {
        defineUpperTable();
        defineCenterTable();
        defineBottomTable();
        defineButtonsTable();
    }

    public void showPowerInfo(String powerName, int maxTime) {
        powerNameLabel.setText(powerName);
        powerTime = maxTime;
        powerTimeValueLabel.setText(String.format(Constants.HUD_FORMAT_POWER_TIME, powerTime));
        powerTable.setVisible(true);
    }

    public void hidePowerInfo() {
        powerTable.setVisible(false);
    }

    public boolean isPowerInfoVisible() {
        return powerTable.isVisible();
    }

    public void showMessage(String message) {
        messageLabel.setText(message);
        messageLabel.setVisible(true);
        image.setVisible(false);
        centerTable.setVisible(true);
    }

    public String getMessage() {
        String message = "";
        if (messageLabel.isVisible()) {
            message = messageLabel.getText().toString();
        }
        return message;
    }

    public void showHurryUpMessage() {
        showMessage(i18NGameThreeBundle.format("hud.hurryUp"));
    }

    public void showTimeIsUpMessage() {
        showMessage(i18NGameThreeBundle.format("hud.timeIsUp"));
    }

    public void hideMessage() {
        messageLabel.setVisible(false);
        centerTable.setVisible(false);
    }

    public boolean isMessageVisible() {
        return messageLabel.isVisible();
    }

    public void showImage(TextureRegion textureRegion) {
        image.setDrawable(new TextureRegionDrawable(textureRegion));
        image.setScaling(Scaling.fit); // Default is Scaling.stretch.
        image.setVisible(true);
        messageLabel.setVisible(false);
        centerTable.setVisible(true);
    }

    public void showImage(TextureRegion textureRegion, float seconds) {
        overlayTimer = 0;
        overlaySeconds = seconds;
        overlayTemporaryScreen = true;
        showImage(textureRegion);
    }

    public void showModalImage(TextureRegion textureRegion) {
        pauseLabel.setVisible(false);
        resumeLabel.setVisible(true);
        quitLabel.setVisible(false);
        showImage(textureRegion);
        screen.setPlayScreenStatePaused();
    }

    public void hideModalImage() {
        pauseLabel.setVisible(true);
        resumeLabel.setVisible(false);
        quitLabel.setVisible(false);
        hideImage();
        screen.setPlayScreenStateRunning();
    }

    public void hideImage() {
        image.setVisible(false);
        centerTable.setVisible(false);
    }

    public boolean isImageVisible() {
        return image.isVisible();
    }

    public void showFpsInfo() {
        fpsTable.setVisible(true);
    }

    public void hideFpsInfo() {
        fpsTable.setVisible(false);
    }

    public boolean isFpsInfoVisible() {
        return fpsTable.isVisible();
    }

    public void showHealthBarInfo(String enemyName, int energy) {
        enemyNameLabel.setText(enemyName);
        healthBar.setInitialEnergy(energy);
        healthBarTable.setVisible(true);
    }

    public void hideHealthBarInfo() {
        healthBarTable.setVisible(false);
    }

    public boolean isHealthBarInfoVisible() {
        return healthBarTable.isVisible();
    }

    public void update(float dt) {
        // Update world time
        timeCount += dt;
        if (timeCount >= 1) {
            if (time > 0) {
                time--;
                if (time <= Constants.LEVEL_TIMER_NOTIFICATION) {
                    AudioManager.getInstance().play(Assets.getInstance().getSounds().getClock());
                    AudioManager.getInstance().play(Assets.getInstance().getSounds().getBeepB());
                    if (isMessageVisible()) {
                        hideMessage();
                    } else {
                        showHurryUpMessage();
                    }
                }
            } else {
                timeIsUp = true;
            }
            timeValueLabel.setText(String.format(Constants.HUD_FORMAT_TIME, time));
            timeCount = 0;
        }

        // Update power time
        if (isPowerInfoVisible()) {
            powerTimeCount += dt;
            if (powerTimeCount >= 1) {
                if (powerTime > 0) {
                    powerTime--;
                    if (powerTime <= Constants.POWER_TIMER_NOTIFICATION) {
                        AudioManager.getInstance().play(Assets.getInstance().getSounds().getBeepA());
                    }
                    powerTimeValueLabel.setText(String.format(Constants.HUD_FORMAT_POWER_TIME, powerTime));
                } else {
                    hidePowerInfo();
                }
                powerTimeCount = 0;
            }
        }

        // Update FPS
        if (isFpsInfoVisible()) {
            fps = Gdx.graphics.getFramesPerSecond();
            fpsValueLabel.setText(String.format(Constants.HUD_FORMAT_FPS, fps));
        }

        // Overlay temporary screen
        if (overlayTemporaryScreen) {
            overlayTimer += dt;
            if (overlayTimer >= overlaySeconds) {
                overlayTemporaryScreen = false;
                overlayTimer = 0;
                hideImage();
            }
        }
    }

    public void addScore(int value) {
        score += value;
        scoreValueLabel.setText(String.format(Constants.HUD_FORMAT_SCORE, score));
    }

    public int getScore() {
        return score;
    }

    public void decreaseHealth() {
        healthBar.decrease();
    }

    public boolean isTimeIsUp() {
        return timeIsUp;
    }

    public boolean isPowerTimeUp() {
        return powerTime <= 0;
    }

    public boolean isPowerRunningOut() {
        return powerTime <= Constants.POWER_TIMER_NOTIFICATION;
    }

    public void forcePowerTimeUp() {
        hidePowerInfo();
    }

    public void decreaseLives(int quantity) {
        lives -= quantity;
        livesValueLabel.setText(String.format(Constants.HUD_FORMAT_LIVES, lives));
    }

    public void increaseSilverBullets(int quantity) {
        silverBullets += quantity;
        silverBulletValueLablel.setText(String.format(Constants.HUD_FORMAT_SILVER_BULLETS, silverBullets));
    }

    public void decreaseSilverBullets(int quantity) {
        silverBullets -= quantity;
        silverBulletValueLablel.setText(String.format(Constants.HUD_FORMAT_SILVER_BULLETS, silverBullets));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void draw() {
        act();
        // Set our batch to now draw what the Hud camera sees.
        super.draw();
    }
}
