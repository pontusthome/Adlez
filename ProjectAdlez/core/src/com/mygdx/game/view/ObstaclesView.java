package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.Obstacles;

import java.util.List;

/**
 * Created by martinso on 27/04/16.
 */
public class ObstaclesView {

    private Texture obstacleTexture;
    private Adlez adlez = Adlez.getInstance();
    private List<Obstacles> obstacles = adlez.getObstacles();
    private SpriteBatch spriteBatch;

    public ObstaclesView(SpriteBatch spriteBatch, String obstacleImg) {
        this.spriteBatch = spriteBatch;
        obstacleTexture = new Texture((Gdx.files.internal((obstacleImg))));
    }

    public void generateObstacles() {
        for(Obstacles obst : obstacles) {
            spriteBatch.draw(obstacleTexture, obst.getPosX(), obst.getPosY());
        }
    }
}
