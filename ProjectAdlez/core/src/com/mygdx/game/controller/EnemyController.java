package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.*;
import com.mygdx.game.model.Character;
import com.mygdx.game.utils.AssetStrings;
import com.mygdx.game.utils.Utils;

/**
 * @author Pontus
 */
public class EnemyController implements IController {

    private IEnemy enemy;
    private IPlayer player;
    private CharacterView enemyView;

    public EnemyController(IEnemy enemy) {
        this.enemy = enemy;
        this.player = Adlez.getInstance().getPlayer();
        switch (enemy.getType()) {
            case Enemy.REGULAR_LEVEL_ONE:
                enemyView = new CharacterView(AssetStrings.RED_PLAYER_MOVE);
                break;
            case Enemy.REGULAR_LEVEL_TWO:
                enemyView = new CharacterView(AssetStrings.BLUE_PLAYER_MOVE);
                break;
            case Enemy.DARK_ONE_LEVEL_ONE:
                enemyView = new CharacterView(AssetStrings.DARK_BLUE_PLAYER_MOVE);
                break;
            default:
                enemyView = new CharacterView(AssetStrings.RED_PLAYER_MOVE);
                break;
        }
    }

    @Override
    public void update() {
        ((Character) enemy).clearMoveFlags();
        float playerX = player.getPosX();
        float playerY = player.getPosY();
        float x = enemy.getPosX();
        float y = enemy.getPosY();
        boolean inRange = Utils.inRange(playerX, x, playerY, y, 70);
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
    
    @Override
    public void render(SpriteBatch batch){
        batch.draw(getCurrentFrame(), enemy.getPosX(), enemy.getPosY());
    }

    @Override
    public ICharacterView getView() {
        return enemyView;
    }

    public TextureRegion getCurrentFrame() {
        return enemyView.getCurrentFrame();
    }
}
