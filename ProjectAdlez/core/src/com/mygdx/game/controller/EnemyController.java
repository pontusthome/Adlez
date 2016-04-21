package com.mygdx.game.controller;

import com.mygdx.game.model.Direction;
import com.mygdx.game.model.NPC;
import com.mygdx.game.model.Player;
import com.mygdx.game.utils.Utils;

import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * @author Pontus
 */
public class EnemyController implements CharacterActions {

    private List<NPC> enemies;
    private Player player;
    private CollisionHandler collisionHandler = new CollisionHandler();

    public EnemyController(List<NPC> enemies, Player player) {
        this.player = player;
        this.enemies = enemies;
    }

    @Override
    public void update() {
        for (NPC enemy : enemies) {
            float playerX = player.getPosX();
            float playerY = player.getPosY();
            float x = enemy.getPosX();
            float y = enemy.getPosY();
            boolean inRange = Utils.inRange(playerX, x, playerY, y, 200);
            if (playerY > y && Math.abs(playerY - y) > 1 && inRange) {
                enemy.moveNorth();
                enemy.setDirection(Direction.EAST);
                if (collisionHandler.checkCollision(enemy)) {
                    enemy.moveSouth();
                }
            }
            if (playerY < y && Math.abs(playerY - y) > 1 && inRange) {
                enemy.moveSouth();
                enemy.setDirection(Direction.EAST);
                if (collisionHandler.checkCollision(enemy)) {
                    enemy.moveNorth();
                }
            }
            if (playerX < x && Math.abs(playerX - x) > 1 && inRange) {
                enemy.moveWest();
                enemy.setDirection(Direction.EAST);
                if (collisionHandler.checkCollision(enemy)) {
                    enemy.moveEast();
                }
            }
            if (playerX > x && Math.abs(playerX - x) > 1 && inRange) {
                enemy.moveEast();
                enemy.setDirection(Direction.EAST);
                if (collisionHandler.checkCollision(enemy)) {
                    enemy.moveWest();
                }
            }
        }
    }
}
