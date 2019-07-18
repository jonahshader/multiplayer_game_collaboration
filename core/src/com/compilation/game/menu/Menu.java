package com.compilation.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.compilation.game.managers.SoundManager;

import java.util.ArrayList;

import static com.compilation.game.Utils.interpolate;


public class Menu implements InputProcessor {
    private ArrayList<MenuItem> menuItems;
    private BitmapFont font;

    final SpriteBatch batch;
    final ShapeRenderer shapeRenderer;
    final Viewport viewport;

    private float firstX;
    private float firstY;
    private float itemHeight;

    private int index = -1;

    private boolean arrowKeysLast;

    public Menu(BitmapFont font, float firstX, float firstY, float itemHeight, SpriteBatch batch, ShapeRenderer shapeRenderer, Viewport viewport) {
        this.font = font;
        this.firstX = firstX;
        this.firstY = firstY;
        this.itemHeight = itemHeight;
        this.batch = batch;
        this.shapeRenderer = shapeRenderer;
        this.viewport = viewport;
        arrowKeysLast = false;

        menuItems = new ArrayList<MenuItem>();
    }

    public void addMenuItem(MenuAction menuAction, String label) {
        MenuItem newItem = new MenuItem(menuAction, label, menuItems.size());
        menuItems.add(newItem);
    }

    public void run(float delta) {
        for (MenuItem menuItem : menuItems) {
            menuItem.run(delta);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
//        if (index < 0)
//            index = 0;
        if (keycode == Input.Keys.UP) {
            index--;
            arrowKeysLast = true;
            SoundManager.playAudio(SoundManager.AudioFile.MenuHover, 1.0f);
        } else if (keycode == Input.Keys.DOWN) {
            index++;
            arrowKeysLast = true;
            SoundManager.playAudio(SoundManager.AudioFile.MenuHover, 1.0f);
        } else if (keycode == Input.Keys.ENTER) {
            if (index > -1) {
                menuItems.get(index).menuAction.executeAction();
                SoundManager.playAudio(SoundManager.AudioFile.MenuClick, 1.0f);
            }

        } else {
            return false; // keycode unused
        }

        if (arrowKeysLast) {
            if (index < 0) {
                index = menuItems.size() - 1;
            } else if (index >= menuItems.size()) {
                index = 0;
            }
        }

        return true; // keycode used
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mouseMoved(screenX, screenY);
        if (index > -1) {
            SoundManager.playAudio(SoundManager.AudioFile.MenuClick, 1.0f);
            menuItems.get(index).menuAction.executeAction();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.isMouseOver(viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY())))) {
                if (index != menuItem.id)
                    SoundManager.playAudio(SoundManager.AudioFile.MenuHover, 1.0f);
                index = menuItem.id;
                arrowKeysLast = false;
                return true;
            }
        }
        if (!arrowKeysLast)
            index = -1;
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void draw() {
        for (MenuItem item : menuItems) {
            item.draw();
        }
    }


    public class MenuItem {
        private MenuAction menuAction;
        private String label;
        private int id;
        private boolean selected = false;
        private float x;

        private static final float X_SELECTED_EXTENTION = 24;

        public MenuItem(MenuAction menuAction, String label, int id) {
            this.menuAction = menuAction;
            this.label = label;
            this.id = id;
            x = firstX;
        }

        public void run(float delta) {
//            x = (float) interpolate(delta, x, firstX + (selected ? X_SELECTED_EXTENTION : 0.0));
            x = (float) interpolate(delta * 8.0, x, firstX + (id == index ? X_SELECTED_EXTENTION : 0.0));
        }

        public void draw() {
            font.draw(batch, label, x, firstY - id * itemHeight);
        }

        public boolean isMouseOver(Vector2 pos) {
            return (pos.x >= firstX && pos.y < firstY - (id - 0.25) * itemHeight && pos.y > firstY - (id + 0.75) * itemHeight);
        }
    }
}
