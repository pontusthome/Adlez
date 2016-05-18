package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.*;
import com.mygdx.game.model.Character;
import com.mygdx.game.model.handler.CollisionHandler;
import com.mygdx.game.model.handler.CollisionHandler2;
import com.mygdx.game.utils.AssetStrings;
import com.mygdx.game.utils.Utils;
import com.mygdx.game.view.CharacterView;
import com.mygdx.game.view.ICharacterView;

/**
 * @author Pontus
 */
public class EnemyController implements ICharacterController{

    private IEnemy enemy;
    private IPlayer player;
    private CharacterView enemyView;
    private Adlez adlez = Adlez.getInstance();
    
    // Values to determine when an enemy is allowed to attack again, temporary solution
    private int attackInterval = 2000; // In milliseconds
    private long timeAccumulator = 2000;
    private long lastUpdateTime = System.currentTimeMillis();

    public EnemyController(IEnemy enemy) {
        this.enemy = enemy;
        this.player = adlez.getPlayer();
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
            case Enemy.DOG_LEVEL_ONE:
                enemyView = new CharacterView(AssetStrings.BLUE_DOG_MOVE);
                break;
            default:
                enemyView = new CharacterView(AssetStrings.RED_PLAYER_MOVE);
                break;
        }
    }

    @Override
    public void update(float deltaT) {
    
        // Accumulation of time to see if enemy can attack again, temporary solution
        long updateTime = System.currentTimeMillis();
        timeAccumulator += updateTime - lastUpdateTime;
        lastUpdateTime = updateTime;
        
        if(!enemy.isAlive()){
            adlez.removeEnemyFromWorld(enemy);
            return;
        }
        
        ((Character) enemy).clearMoveFlags();
        float playerX = player.getPosX();
        float playerY = player.getPosY();
        float x = enemy.getPosX();
        float y = enemy.getPosY();
        
        boolean inRange = Utils.inRange(playerX, x, playerY, y, 70);
        if (playerY > y && Math.abs(playerY - y) > 1 && inRange) {
            enemy.setMovingNorth();
        }else if (playerY < y && Math.abs(playerY - y) > 1 && inRange) {
            enemy.setMovingSouth();
        }
        if (playerX < x && Math.abs(playerX - x) > 1 && inRange) {
            enemy.setMovingWest();
        }else if (playerX > x && Math.abs(playerX - x) > 1 && inRange) {
            enemy.setMovingEast();
        }
        
        enemy.move(deltaT);
        
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
