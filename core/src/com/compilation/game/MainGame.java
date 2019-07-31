package com.compilation.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.compilation.game.managers.FontManager;
import com.compilation.game.managers.SqlManager;
import com.compilation.game.screens.LoginScreen;
import com.compilation.game.screens.MainMenuScreen;

import java.io.Console;

public class MainGame extends Game {
	public static SpriteBatch batch;
	public static ShapeRenderer shapeBatch;
	public static FontManager fontManager;
	public static InputMultiplexer multiplexer;
	public static SqlManager sql;

	@Override
	public void create() {

		sql = new SqlManager();

		try
		{
			if(!sql.testConnection()){
				throw new Exception();
			}
		}
		catch(Exception e){
			System.out.println("Unable to connect to the sql database");
		}

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