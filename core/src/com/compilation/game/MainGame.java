package com.compilation.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.compilation.game.managers.FontManager;
import com.compilation.game.screens.LoginScreen;
import com.compilation.game.screens.MainMenuScreen;

public class MainGame extends Game {
	public static SpriteBatch batch;
	public static ShapeRenderer shapeBatch;
	public static FontManager fontManager;
	public static InputMultiplexer multiplexer;

	@Override
	public void create() {
		batch = new SpriteBatch();
		shapeBatch = new ShapeRenderer();
		fontManager = new FontManager();
		multiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(multiplexer);
//		setScreen(new LoginScreen(this));
		setScreen(new MainMenuScreen(this)); //bypass login for testing
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		shapeBatch.dispose();
		fontManager.dispose();
		super.dispose();
	}
}