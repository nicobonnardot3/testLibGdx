package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Application;

public class MainMenuScreen implements Screen {
    public final Application app;

    private Stage stage;
    private Skin skin;
    private TextButton playButton, exitButton;

    public MainMenuScreen(final Application app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, app.camera));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        this.skin = new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas", TextureAtlas.class));
        this.skin.add("default-font", app.font35);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));

        initButtons();
    }

    private void initButtons() {
        float menuButtonWidth = 450;
        float menuButtonHeight = 70;
        float menuButtonMargin = 20;

        playButton = new TextButton("Play", skin, "default");
        playButton.setPosition(app.camera.viewportWidth / 2 - (menuButtonWidth / 2), app.camera.viewportHeight / 2 - menuButtonHeight / 2);
        playButton.setSize(menuButtonWidth, menuButtonHeight);

        exitButton = new TextButton("Exit", skin, "default");
        exitButton.setPosition(app.camera.viewportWidth / 2 - (menuButtonWidth / 2), app.camera.viewportHeight / 2 - menuButtonHeight / 2 - (menuButtonHeight + menuButtonMargin));
        exitButton.setSize(menuButtonWidth, menuButtonHeight);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.playScreen);
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage.addActor(playButton);
        stage.addActor(exitButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();

        String mainMenuText = "Main Menu";

        final GlyphLayout layout = new GlyphLayout(app.font80, mainMenuText);

        final float fontX = app.camera.viewportWidth / 2 - layout.width / 2;
        final float fontY = 2 * app.camera.viewportHeight / 3 + layout.height / 2;

        app.batch.begin();
        app.font80.draw(app.batch, layout, fontX, fontY);
        app.font35.draw(app.batch, "Main Menu screen", 55, 55);
        app.batch.end();
    }

    private void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
