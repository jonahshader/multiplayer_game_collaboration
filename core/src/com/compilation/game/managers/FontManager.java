package com.compilation.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Disposable;

public class FontManager implements Disposable {
    public enum FontType {
        Main,
        Console,
        Title
    }

    private FreeTypeFontGenerator mainFont = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Itim-Regular.ttf"));
    private FreeTypeFontGenerator consoleFont = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Pianaforma.ttf"));
    private FreeTypeFontGenerator titleFont = new FreeTypeFontGenerator(Gdx.files.internal("fonts/RifficFree-Bold.ttf"));

    public BitmapFont generateFont(FontType fontType, FreeTypeFontParameter fontSettings) {
        FreeTypeFontGenerator font;
        switch (fontType) {
            case Main:
                font = mainFont;
                break;
            case Console:
                font = consoleFont;
                break;
            case Title:
                font = titleFont;
                break;
            default:
                return null;
        }

        FreeTypeFontGenerator.setMaxTextureSize(FreeTypeFontGenerator.NO_MAXIMUM);
        return font.generateFont(fontSettings);
    }

    @Override
    public void dispose() {
        mainFont.dispose();
        consoleFont.dispose();
        titleFont.dispose();
    }
}
