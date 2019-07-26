package com.compilation.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.compilation.game.MainGame;
import com.compilation.game.ecs.ECSEngine;
import com.compilation.game.world.World;

public class GameScreen implements Screen {
    private final MainGame game;

    private ECSEngine engine;

    private World world;
    private Viewport viewport;
    private OrthographicCamera cam;

    public GameScreen(MainGame game) {
        this.game = game;
        MainGame.multiplexer.clear(); // remove all previous input processors from the multiplexer
        cam = new OrthographicCamera();
        viewport = new ScreenViewport(cam);
        world = new World(game);
        engine = new ECSEngine(cam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.25f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.translate(1f * cam.zoom, 1f * cam.zoom);
        cam.zoom *= 1.001;
        world.render(cam);
        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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

    }
}
