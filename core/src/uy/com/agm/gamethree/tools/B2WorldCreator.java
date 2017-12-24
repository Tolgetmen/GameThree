package uy.com.agm.gamethree.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;

import java.util.concurrent.LinkedBlockingQueue;

import uy.com.agm.gamethree.screens.PlayScreen;
import uy.com.agm.gamethree.sprites.enemies.Enemy;
import uy.com.agm.gamethree.sprites.enemies.EnemyOne;
import uy.com.agm.gamethree.sprites.powerup.Items.Item;
import uy.com.agm.gamethree.sprites.powerup.Items.PowerOne;
import uy.com.agm.gamethree.sprites.powerup.boxes.PowerBox;
import uy.com.agm.gamethree.sprites.tileobjects.Borders;
import uy.com.agm.gamethree.sprites.tileobjects.Obstacle;
import uy.com.agm.gamethree.sprites.weapons.EnemyBullet;
import uy.com.agm.gamethree.sprites.weapons.EnergyBall;
import uy.com.agm.gamethree.sprites.weapons.Weapon;

/**
 * Created by AGM on 12/4/2017.
 */

public class B2WorldCreator {
    private static final String TAG = B2WorldCreator.class.getName();

    private PlayScreen screen;
    private Array<Enemy> enemies;
    private Array<PowerBox> powerBoxes;
    private Array<Item> items;
    private Array<Weapon> weapons;
    private LinkedBlockingQueue<GameThreeActorDef> gameThreeActorsToCreate;

    public B2WorldCreator(PlayScreen screen) {
        this.screen = screen;
        TiledMap map = screen.getMap();

        // Layer: borders
        for (MapObject object : map.getLayers().get("borders").getObjects().getByType(RectangleMapObject.class)) {
            new Borders(screen, object);
        }

        // Layer: obstacles
        for (MapObject object : map.getLayers().get("obstacles").getObjects().getByType(RectangleMapObject.class)) {
            new Obstacle(screen, object);
        }

        // Layer: enemyOne
        enemies = new Array<Enemy>();
        for (MapObject object : map.getLayers().get("enemyOne").getObjects().getByType(RectangleMapObject.class)) {
            enemies.add(new EnemyOne(screen, object));
        }

        // Layer: powerBoxes
        powerBoxes = new Array<PowerBox>();
        for (MapObject object : map.getLayers().get("powerBox").getObjects().getByType(RectangleMapObject.class)) {
            powerBoxes.add(new PowerBox(screen, object));
        }

        // Items
        items = new Array<Item>();

        // Weapons
        weapons = new Array<Weapon>();

        // Queue
        gameThreeActorsToCreate = new LinkedBlockingQueue<GameThreeActorDef>();
    }

    public Array<Enemy> getEnemies() {
        return enemies;
    }

    public Array<PowerBox> getPowerBoxes() {
        return powerBoxes;
    }

    public Array<Item> getItems() {
        return items;
    }

    public Array<Weapon> getWeapons() {
        return weapons;
    }

    public void createGameThreeActor(GameThreeActorDef gameThreeActorDef) {
        gameThreeActorsToCreate.add(gameThreeActorDef);
    }

    public void handleCreatingGameThreeActors() {
        if (!gameThreeActorsToCreate.isEmpty()) {
            GameThreeActorDef gameThreeActorDef = gameThreeActorsToCreate.poll(); // Similar to pop but for a queue, removes the element
            if (gameThreeActorDef.getType() == PowerOne.class) {
                items.add(new PowerOne(screen, gameThreeActorDef.getPosition().x, gameThreeActorDef.getPosition().y));
            }
            if (gameThreeActorDef.getType() == EnergyBall.class) {
                weapons.add(new EnergyBall(screen, gameThreeActorDef.getPosition().x, gameThreeActorDef.getPosition().y));
            }
            if (gameThreeActorDef.getType() == EnemyBullet.class) {
                weapons.add(new EnemyBullet(screen, gameThreeActorDef.getPosition().x, gameThreeActorDef.getPosition().y));
            }
        }
    }
}
