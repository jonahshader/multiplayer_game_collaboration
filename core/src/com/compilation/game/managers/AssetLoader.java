package com.compilation.game.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetLoader {

    private static final AssetManager manager = new AssetManager();

    // Textures
    private final static String terrainPack = "textures/terrain_64x64.pack";

    public static void loadImages() {
        manager.load(terrainPack, TextureAtlas.class);
    }

    // Menu sounds
    private static final String menuHoverSound = "audio/sounds/menu_tick_over.ogg";
    private static final String menuClickSound = "audio/sounds/menu_tick_in.ogg";
    private static final String menuRelease = "audio/sounds/menu_tick_out.ogg";

    public static void loadMenuSounds() {
        manager.load(menuHoverSound, Sound.class);
        manager.load(menuClickSound, Sound.class);
        manager.load(menuRelease, Sound.class);
    }
}
