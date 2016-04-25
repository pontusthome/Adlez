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
public class EnemyController extends CharacterView implements CharacterActions {

    private NPC enemy;
    private Player player;

    public EnemyController(NPC enemy, String characterImg, Player player) {
        this.enemy = enemy;
        this.player = player;
        characterTexture = new Texture((Gdx.files.internal((characterImg))));
        TextureRegion[][] tmp = TextureRegion.split(characterTexture,
                characterTexture.getWidth() / col,
                characterTexture.getHeight() / row);
        characterFrames = new TextureRegion[col * row];

        // Setting frames from player sheet.
        int index = 0;
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                characterFrames[index++] = tmp[j][i];
            }
        }
        // Sheet index:
        // 0 2 4 6
        // 1 3 5 7

        animation = new Animation(1, characterFrames);
        stateTime = 0f;
        currentFrame = animation.getKeyFrame(0);

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
        viewUpdate(enemy.getDirection());
    }
}
