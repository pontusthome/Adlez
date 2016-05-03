package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.Adlez;
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
    private IPlayer player;
    public Stage stage;
    public Viewport viewport;

    private Label healthLabel;
    private Label manaLabel;
    private Label areaLabel;
    private Label goldLabel;


    private ProgressBar healthBar;
    private ProgressBar.ProgressBarStyle barStyle;
    private TextureRegionDrawable textureBar;
    private Skin barSkin;
    private Skin barBackgroundSkin;

    public Hud(GameScreen screen) {

        this.gameScreen = screen;
        this.player = adlez.getPlayer();
        this.viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        this.stage = new Stage();


        barSkin = new Skin();
        Pixmap pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        barSkin.add("red", new Texture(pixmap));

        barBackgroundSkin = new Skin();
        Pixmap pixmap2 = new Pixmap(100, 10, Pixmap.Format.RGBA8888);
        pixmap2.setColor(Color.WHITE);
        pixmap2.fill();
        barBackgroundSkin.add("white", new Texture(pixmap2));

        barStyle = new ProgressBar.ProgressBarStyle(barBackgroundSkin.newDrawable("white", Color.WHITE),
                barSkin.newDrawable("red", Color.RED));
        barStyle.knobBefore = barStyle.knob;

        this.healthBar = new ProgressBar(0, 100, 0.5f, false, barStyle);
        healthBar.setPosition(10, 10);
        healthBar.setSize(100, 10);
        healthBar.setAnimateDuration(1);
        healthBar.setValue(10);


        healthLabel = new Label("Health: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        manaLabel = new Label("Mana: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        areaLabel = new Label("Area: ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        Table table = new Table();
        table.top();
        table.setFillParent(true);

        table.add(healthLabel).expandX().padTop(10);
        table.add(manaLabel).expandX().padTop(10);
        table.add(areaLabel).expandX().padTop(10);
        table.row();
        table.add(healthBar);
        stage.addActor(table);
    }
}
