package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.controller.CharacterActions;
import com.mygdx.game.model.NPC;
import com.mygdx.game.model.Player;
import com.mygdx.game.utils.Utils;
import com.mygdx.game.controller.CharacterView;

/**
 * @author Pontus
 */
public class EnemyController implements CharacterActions {

    private NPC enemy;
    private Player player;
    private CharacterView enemyView;

    public EnemyController(NPC enemy, String characterImg, Player player) {
        this.enemy = enemy;
        this.player = player;
        enemyView = new CharacterView(characterImg);
    }

    @Override
    public void update() {
        enemy.savePosition();

        float playerX = player.getPosX();
        float playerY = player.getPosY();
        float x = enemy.getPosX();
        float y = enemy.getPosY();
        boolean inRange = Utils.inRange(playerX, x, playerY, y, 200);
        if (playerY > y && Math.abs(playerY - y) > 1 && inRange) {
            enemy.moveNorth();
        }
        if (playerY < y && Math.abs(playerY - y) > 1 && inRange) {
            enemy.moveSouth();
        }
        if (playerX < x && Math.abs(playerX - x) > 1 && inRange) {
            enemy.moveWest();
        }
        if (playerX > x && Math.abs(playerX - x) > 1 && inRange) {
            enemy.moveEast();
        }
        enemyView.viewUpdate(enemy.getDirection());
    }

    public TextureRegion getCurrentFrame() {
        return enemyView.getCurrentFrame();
    }
}
