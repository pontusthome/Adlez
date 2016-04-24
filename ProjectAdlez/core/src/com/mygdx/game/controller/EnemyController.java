package com.mygdx.game.controller;

import com.mygdx.game.model.Direction;
import com.mygdx.game.model.NPC;
import com.mygdx.game.model.Player;
import com.mygdx.game.utils.Utils;
import com.mygdx.game.view.CharacterView;

import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * @author Pontus
 */
public class EnemyController implements CharacterActions {

    private NPC enemy;
    private CharacterView view;
    private Player player;

    private CollisionHandler collisionHandler = new CollisionHandler();

    public EnemyController(NPC enemy, CharacterView view, Player player) {
        this.enemy = enemy;
        this.view = view;
        this.player = player;
    }

    @Override
    public void update() {
        float playerX = player.getPosX();
        float playerY = player.getPosY();
        float x = enemy.getPosX();
        float y = enemy.getPosY();
        boolean inRange = Utils.inRange(playerX, x, playerY, y, 200);
        if (playerY > y && Math.abs(playerY - y) > 1 && inRange) {
            enemy.moveNorth();
            enemy.setDirection(Direction.NORTH);
            if (collisionHandler.checkCollision(enemy)) {
                enemy.moveSouth();
            }
        }
        if (playerY < y && Math.abs(playerY - y) > 1 && inRange) {
            enemy.moveSouth();
            enemy.setDirection(Direction.SOUTH);
            if (collisionHandler.checkCollision(enemy)) {
                enemy.moveNorth();
            }
        }
        if (playerX < x && Math.abs(playerX - x) > 1 && inRange) {
            enemy.moveWest();
            enemy.setDirection(Direction.WEST);
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
        view.update(enemy.getDirection());
    }
    
    public NPC getEnemy(){
        return enemy;
    }
    
    public boolean isAlive(){
        return enemy.isAlive();
    }
}
