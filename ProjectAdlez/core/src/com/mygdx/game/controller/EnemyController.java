package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Character;
import com.mygdx.game.model.INPC;
import com.mygdx.game.model.IPlayer;
import com.mygdx.game.utils.Utils;

/**
 * @author Pontus
 */
public class EnemyController implements IController {

    private INPC enemy;
    private IPlayer player;
    private CharacterView enemyView;

    public EnemyController(INPC enemy, String characterImg, IPlayer player) {
        this.enemy = enemy;
        this.player = player;
        enemyView = new CharacterView(characterImg);
    }

    @Override
    public void update() {
        Character character = (Character) enemy;
        character.clearMoveAndCollisionFlags();
        float playerX = player.getPosX();
        float playerY = player.getPosY();
        float x = enemy.getPosX();
        float y = enemy.getPosY();
        boolean inRange = Utils.inRange(playerX, x, playerY, y, 200);
        if (playerY > y && Math.abs(playerY - y) > 1 && inRange) {
            character.setMovingNorth(true);
        }
        if (playerY < y && Math.abs(playerY - y) > 1 && inRange) {
            character.setMovingSouth(true);
        }
        if (playerX < x && Math.abs(playerX - x) > 1 && inRange) {
            character.setMovingWest(true);
        }
        if (playerX > x && Math.abs(playerX - x) > 1 && inRange) {
            character.setMovingEast(true);
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
