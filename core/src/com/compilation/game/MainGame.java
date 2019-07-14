package com.compilation.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.compilation.game.screens.MainMenuScreen;

public class MainGame extends Game {
	public static SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();		// initialize sprite batch
		setScreen(new MainMenuScreen());		// change screen to main menu
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		super.dispose();
	}
}
