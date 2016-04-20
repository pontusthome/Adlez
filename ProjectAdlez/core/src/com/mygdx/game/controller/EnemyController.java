package com.mygdx.game.controller;

import com.mygdx.game.model.Direction;
import com.mygdx.game.model.NPC;
import com.mygdx.game.model.Player;

import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;

/**
 * Created by Pontus on 2016-04-20.
 */
public class EnemyController implements CharacterActions {

    private List<NPC> enemies;
    private Player player;

    private int move;

    public EnemyController(List<NPC> enemies, Player player) {
        this.player = player;
        this.enemies = enemies;
        move = 0;
    }

    @Override
    public void update() {
        for (NPC enemy : enemies) {
            float playerX = player.getPosX();
            float playerY = player.getPosY();
            float x = enemy.getPosX();
            float y = enemy.getPosY();
            if (playerY > y && Math.abs(playerY - y) > 1) {
                enemy.move(0, enemy.getSpeed());
                enemy.setDirection(Direction.NORTH);
            }
            if (playerY < y && Math.abs(playerY - y) > 1) {
                enemy.move(0, -enemy.getSpeed());
                enemy.setDirection(Direction.SOUTH);
            }
            if (playerX < x && Math.abs(playerX - x) > 1) {
                enemy.move(-enemy.getSpeed(), 0);
                enemy.setDirection(Direction.WEST);
            }
            if (playerX > x && Math.abs(playerX - x) > 1) {
                enemy.move(enemy.getSpeed(), 0);
                enemy.setDirection(Direction.EAST);
            }
        }
    }
}
