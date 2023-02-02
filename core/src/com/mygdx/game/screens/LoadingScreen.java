package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Application;

public class LoadingScreen implements Screen {
    private final Application app;
    private final ShapeRenderer shapeRenderer;
    private float progress;

    public LoadingScreen(final Application app) {
        this.app = app;
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {
        this.progress = 0f;
        queueAssets();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        float progressRectWidth = 700;
        float progressRectHeight = 32;
        float progressRectPosX = app.camera.viewportWidth / 2 - (progressRectWidth / 2);
        float progressRectPosY = app.camera.viewportHeight / 2 - (progressRectHeight / 2);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(progressRectPosX, progressRectPosY, progressRectWidth, progressRectHeight);

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(progressRectPosX, progressRectPosY, progress * progressRectWidth, progressRectHeight);

        shapeRenderer.end();

        app.batch.begin();
        app.font35.draw(app.batch, "Loading...", 55, 55);
        app.batch.end();
    }

    private void update(float delta) {
        progress = MathUtils.lerp(progress, app.assets.getProgress(), .1f);
        if (app.assets.update() && progress > .999f) {
            app.setScreen(app.splashScreen);
        }
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
        shapeRenderer.dispose();
    }

    private void queueAssets() {

        app.assets.load("img/bad-logic.jpg", Texture.class);
        app.assets.load("ui/uiskin.atlas", TextureAtlas.class);
    }
}
