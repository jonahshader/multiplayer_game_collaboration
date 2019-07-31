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

    private TextureAtlas tempCharacterAtlas;
    private int entityCount;

    public GameScreen(MainGame game) {
        this.game = game;
        MainGame.multiplexer.clear(); // remove all previous input processors from the multiplexer
        cam = new OrthographicCamera();
        viewport = new ScreenViewport(cam);
        world = new World(game);
        engine = new ECSEngine(cam, world);
        tempCharacterAtlas = new TextureAtlas("textures/characters.pack");
        entityCount = 1;

        Entity player = engine.createEntity();
        Position position = engine.createComponent(Position.class);
        Velocity velocity = engine.createComponent(Velocity.class);
        Acceleration acceleration = engine.createComponent(Acceleration.class);
        PlayerControlled playerControlled = engine.createComponent(PlayerControlled.class);
        Spectating spectating = engine.createComponent(Spectating.class);
        Graphic graphic = engine.createComponent(Graphic.class);
        MaxSpeed maxSpeed = engine.createComponent(MaxSpeed.class);

        playerControlled.acceleration = 2048;
        graphic.sprite = new Sprite(tempCharacterAtlas.findRegion("character"));
        graphic.sprite.setOrigin(32f, 32f);
        spectating.zoom = 1f;
        maxSpeed.speed = 512*8;

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
//        for (int i = 0 ; i < 3; i++) {
//            Entity roamingCharacter = engine.createEntity();
//
//            Position position = engine.createComponent(Position.class);
//            Velocity velocity = engine.createComponent(Velocity.class);
//            Graphic graphic = engine.createComponent(Graphic.class);
//            velocity.x = (float) ((Math.random() - 0.5) * 1024);
//            velocity.y = (float) ((Math.random() - 0.5) * 1024);
//            graphic.sprite = new Sprite(tempCharacterAtlas.findRegion("character"));
//            graphic.sprite.setOrigin(32f, 32f);
//
//            roamingCharacter.add(position);
//            roamingCharacter.add(velocity);
//            roamingCharacter.add(graphic);
//
//            entityCount++;
//            engine.addEntity(roamingCharacter);
//        }
//        System.out.println(entityCount + " " + 1/delta);

        world.run();

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
