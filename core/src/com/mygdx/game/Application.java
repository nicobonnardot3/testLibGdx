package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.screens.LoadingScreen;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.screens.PlayScreen;
import com.mygdx.game.screens.SplashScreen;

public class Application extends Game {
    public static final String TITLE = "TGD";
    public static final float VERSION = 0.1f;
    public static final int V_WIDTH = 1024;
    public static final int V_HEIGHT = 720;
    public OrthographicCamera camera;
    public Batch batch;
    public BitmapFont font35, font80;
    public AssetManager assets;
    public LoadingScreen loadingScreen;
    public SplashScreen splashScreen;
    public MainMenuScreen mainmenuScreen;
    public PlayScreen playScreen;

    @Override
    public void create() {
        assets = new AssetManager();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 720);
        batch = new SpriteBatch();

        initFonts();

        loadingScreen = new LoadingScreen(this);
        splashScreen = new SplashScreen(this);
        mainmenuScreen = new MainMenuScreen(this);
        playScreen = new PlayScreen(this);

        this.setScreen(new LoadingScreen(this));
    }

    private void initFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/ToThePointRegular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 35;
        parameter.color = Color.WHITE;
        font35 = generator.generateFont(parameter);

        parameter.size = 80;
        font80 = generator.generateFont(parameter);

        generator.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font35.dispose();
        assets.dispose();
        loadingScreen.dispose();
        splashScreen.dispose();
        mainmenuScreen.dispose();
        playScreen.dispose();
    }
}
