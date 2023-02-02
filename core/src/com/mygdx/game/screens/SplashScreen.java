package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Application;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SplashScreen implements Screen {
    private final Application app;
    private Stage stage;
    private Image splashimage;

    public SplashScreen(final Application app) {
        this.app = app;
        this.stage = new Stage(new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, app.camera));
    }

    @Override
    public void show() { // called once when this screen shows
        Gdx.input.setInputProcessor(stage);

        Runnable transitionRunnable = new Runnable() {
            @Override
            public void run() {
                app.setScreen(app.mainmenuScreen);
            }
        };

        Texture splashTex = app.assets.get("img/bad-logic.jpg", Texture.class);
        splashimage = new Image(splashTex);
        splashimage.setOrigin(splashimage.getWidth() / 2, splashimage.getHeight() / 2);
        splashimage.setPosition(stage.getWidth() / 2 - (float) (splashTex.getWidth() / 2), -splashTex.getHeight());
        splashimage.addAction(
                sequence(
                        alpha(0),
                        scaleTo(0, 0),
                        parallel(
                                alpha(1, 1.5f),
                                moveTo(stage.getWidth() / 2 - (float) (splashTex.getWidth() / 2), (stage.getHeight() / 2) - (float) (splashTex.getHeight() / 2), 1.5f, Interpolation.bounce),
                                scaleBy(1.5f, 1.5f, 2f)
                        ),
                        scaleBy(-.5f, -.5f, .5f),
                        alpha(0, .5f),
                        run(transitionRunnable)
                )
        );
        stage.addActor(splashimage);
    }

    @Override
    public void render(float delta) { // screen dependant render function
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();

        app.batch.begin();
        app.font35.draw(app.batch, "Splashscreen!", 55, 55);
        app.batch.end();
    }

    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() { // called when screen is changed

    }

    @Override
    public void dispose() {

    }
}
