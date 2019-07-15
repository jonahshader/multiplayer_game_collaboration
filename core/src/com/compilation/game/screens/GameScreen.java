package com.compilation.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Screen;
import com.compilation.game.Entities.Player;

public class GameScreen implements Screen {

    Engine engine = new Engine();
    Player mainPlayer;

    @Override
    public void show() {
        mainPlayer = new Player(engine);
    }

    @Override
    public void render(float delta) {

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
