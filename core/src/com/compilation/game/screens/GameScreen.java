package com.compilation.game.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.compilation.game.MainGame;
import com.compilation.game.ecs.ECSEngine;
import com.compilation.game.ecs.components.*;
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

        Entity player = engine.createEntity();
        Position position = engine.createComponent(Position.class);
        Velocity velocity = engine.createComponent(Velocity.class);
        Acceleration acceleration = engine.createComponent(Acceleration.class);
        PlayerControlled playerControlled = engine.createComponent(PlayerControlled.class);
        Spectating spectating = engine.createComponent(Spectating.class);
        Graphic graphic = engine.createComponent(Graphic.class);
        MaxSpeed maxSpeed = engine.createComponent(MaxSpeed.class);

        playerControlled.acceleration = 2048;
        TextureAtlas characterAtlas = new TextureAtlas("textures/characters.pack");
        graphic.sprite = new Sprite(characterAtlas.findRegion("character"));
        graphic.sprite.setOrigin(32f, 32f);
        graphic.sprite.getX();
        spectating.zoom = 1f;
        maxSpeed.speed = 512;

        player.add(position);
        player.add(velocity);
        player.add(acceleration);
        player.add(playerControlled);
        player.add(spectating);
        player.add(graphic);
        player.add(maxSpeed);

        engine.addEntity(player);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.25f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
