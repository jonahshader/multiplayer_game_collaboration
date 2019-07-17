package com.compilation.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.compilation.game.MainGame;
import com.compilation.game.managers.FontManager;
import com.compilation.game.managers.SqlManager;
import com.compilation.game.ui.menu.Menu;
import com.compilation.game.ui.menu.mainmenuactions.ExitGameAction;
import com.compilation.game.ui.menu.mainmenuactions.PlayGameAction;
import com.compilation.game.ui.menu.mainmenuactions.SettingsAction;

public class MainMenuScreen implements Screen {
    /* TODO: move WORLD_WIDTH and WORLD_HEIGHT to MainGame and don't make them 1920 by 1080
    also differentiate between world coordinate and screen/gui coordinates by making separate viewports/cameras
     */
    public static final int WORLD_WIDTH = 1920;
    public static final int WORLD_HEIGHT = 1080;

    private final MainGame game;
    private final SpriteBatch batch;
    private final ShapeRenderer shapeBatch;

    private Menu mainMenu;

    private Camera camera;

    private Viewport viewport;

    private BitmapFont titleFont, mainFont;

    public MainMenuScreen(MainGame game) {
        // pass parameters
        this.game = game;
        batch = MainGame.batch;
        shapeBatch = MainGame.shapeBatch;

        // construct objects
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        camera.position.x = WORLD_WIDTH / 2f;
        camera.position.y = WORLD_HEIGHT / 2f;
        batch.setProjectionMatrix(viewport.getCamera().combined);
        shapeBatch.setProjectionMatrix(viewport.getCamera().combined);

        // generate fonts
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 148;
        titleFont = MainGame.fontManager.generateFont(FontManager.FontType.Title, param);
        titleFont.getData().setScale(1/2.0f);
        titleFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 108;
        mainFont = MainGame.fontManager.generateFont(FontManager.FontType.Main, param);
        mainFont.getData().setScale(1/2.0f);
        mainFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        mainMenu = new Menu(mainFont, 78, WORLD_HEIGHT - 300, (108 / 2f) * 1.5f, batch, shapeBatch, viewport);
        mainMenu.addMenuItem(new PlayGameAction(game), "Play Game");
        mainMenu.addMenuItem(new SettingsAction(), "Settings");
        mainMenu.addMenuItem(new ExitGameAction(), "Exit");

        // set input processor to the menu
        Gdx.input.setInputProcessor(mainMenu);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // do calculations first
        mainMenu.run(delta);

        // clear background
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // render
        viewport.apply(); // does this even do anything?
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        mainMenu.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height); // inform viewport on window size change.
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
        // don't dispose fonts because they don't take a lot of memory and are likely to be used somewhere else
    }
}
