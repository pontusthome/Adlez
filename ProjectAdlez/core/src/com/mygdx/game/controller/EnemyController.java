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
    private CharacterView enemyView;
    private Adlez adlez = Adlez.getInstance();

    public EnemyController(IEnemy enemy) {
        this.enemy = enemy;

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

        if(!enemy.isAlive()){
            adlez.removeEnemyFromWorld(enemy);
            return;
        }

        enemy.update(deltaT);
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
