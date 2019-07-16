package com.compilation.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.compilation.game.MainGame;
import com.compilation.game.ecs.entities.Player;

public class GameScreen implements Screen {

    private final MainGame game;

    private Engine engine = new Engine();
    private Player mainPlayer;

    public GameScreen(MainGame game) {
        this.game = game;
//        Gdx.input.setInputProcessor();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void show() {
        mainPlayer = new Player(engine);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.25f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
        mainPlayer.Destroy(engine);
    }
}
