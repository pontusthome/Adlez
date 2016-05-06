package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.Adlez;
import com.mygdx.game.model.ICharacter;
import com.mygdx.game.model.IPlayer;
import com.mygdx.game.model.Player;

import static com.mygdx.game.utils.AssetStrings.BAR_DIVIDER;
import static com.mygdx.game.utils.AssetStrings.MOVE_SPRITES_IMAGE;

/**
 * Created by Viktor on 2016-04-26.
 */
public class Hud{

    private Adlez adlez = Adlez.getInstance();
    private GameScreen gameScreen;
    private ICharacter player;
    public Stage stage;
    public Viewport viewport;

    private Label healthLabel;
    private Label manaLabel;
    private Label experienceLabel;
    private Label goldLabel;

    private IStatusBar playerHealthBar;
    private IStatusBar playerManaBar;
    private IStatusBar playerExperienceBar;

    public Hud(GameScreen screen) {

        this.gameScreen = screen;
        this.player = adlez.getPlayer();
        this.viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        this.stage = new Stage();

        playerHealthBar = new HealthBar(this.player);
        playerManaBar = new ManaBar(this.player);
        playerExperienceBar = new ExperienceBar(this.player);

        healthLabel = new Label("Health: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        manaLabel = new Label("Mana: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        experienceLabel = new Label("Experience: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        Table table = new Table();
        table.top();
        table.setFillParent(true);

        table.add(healthLabel).expandX().padTop(10);
        table.add(manaLabel).expandX().padTop(10);
        table.add(experienceLabel).expandX().padTop(10);
        table.row();
        table.add(playerHealthBar.getBarImage());
        table.add(playerManaBar.getBarImage());
        table.add(playerExperienceBar.getBarImage());
        stage.addActor(table);

    }
}
