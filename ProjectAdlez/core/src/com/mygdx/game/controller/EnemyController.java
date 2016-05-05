package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.Character;
import com.mygdx.game.model.INPC;
import com.mygdx.game.model.IPlayer;
import com.mygdx.game.utils.Utils;

/**
 * @author Pontus
 */
public class EnemyController implements ICharacterController{

    private INPC enemy;
    private IPlayer player;
    private CharacterView enemyView;
    private Adlez adlez = Adlez.getInstance();

    public EnemyController(INPC enemy, String characterImg, IPlayer player) {
        this.enemy = enemy;
        this.player = player;
        enemyView = new CharacterView(characterImg);
    }

    @Override
    public void update() {
        
        if(!enemy.isAlive()){
            adlez.removeEnemyFromWorld(enemy);
            return;
        }
        
        ((Character) enemy).clearMoveFlags();
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
