package com.compilation.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.compilation.game.MainGame;
import com.compilation.game.world.World;

public class GameScreen implements Screen {

    private final MainGame game;

    private Engine engine = new Engine();

    private World world;

    public GameScreen(MainGame game) {
        this.game = game;
//        Gdx.input.setInputProcessor();
        Gdx.input.setInputProcessor(null);

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
        world.render();
//        MainGame.batch.end();
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

    }
}
