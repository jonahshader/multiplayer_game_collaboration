package com.compilation.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.compilation.game.MainGame;
import com.compilation.game.world.World;

public class GameScreen implements Screen {

    private final MainGame game;

    private Engine engine = new Engine();

    private World world;
    private OrthographicCamera cam;

    public GameScreen(MainGame game) {
        this.game = game;
//        Gdx.input.setInputProcessor();
        Gdx.input.setInputProcessor(null);
        cam = new OrthographicCamera();

        world = new World(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.25f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        MainGame.batch.begin();
        world.render(cam);
//        MainGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        cam.setToOrtho(false, width, height);
        cam.translate(-width/2f, -height/2f);
//        cam.update();
//        cam.zoom = 1/8f;
        cam.update();
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
