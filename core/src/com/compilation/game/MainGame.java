package com.compilation.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.compilation.game.managers.FontManager;
import com.compilation.game.screens.MainMenuScreen;

public class MainGame extends Game {
	public static SpriteBatch batch;
	public static ShapeRenderer shapeBatch;
	public static FontManager fontManager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeBatch = new ShapeRenderer();
		fontManager = new FontManager();
		setScreen(new MainMenuScreen(this));	// change screen to main menu
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeBatch.dispose();
		fontManager.dispose();
		super.dispose();
	}
}
