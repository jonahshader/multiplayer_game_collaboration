package com.compilation.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.compilation.game.MainGame;

public class MainMenuScreen implements Screen {
    private final MainGame game;
    private Texture img;

    private Stage stage;
    private Table table;

    public MainMenuScreen(MainGame game) {
        this.game = game;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);  // take up entire screen
        stage.addActor(table); // add it to the stage

        table.setDebug(true); // draw debug lines

        // add widgets to the table here.


        img = new Texture("badlogic.jpg");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.75f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        MainGame.batch.begin();
//        MainGame.batch.draw(img, 0, 0);
//        MainGame.batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        img.dispose();
    }
}
