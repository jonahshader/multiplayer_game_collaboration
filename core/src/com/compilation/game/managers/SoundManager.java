package com.compilation.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
    public enum AudioFile {
        MenuHover,
        MenuClick,
        MenuRelease
    }

    private static Sound menuHover = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/menu_tick_over.ogg"));
    private static Sound menuClick = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/menu_tick_in.ogg"));
    private static Sound menuRelease = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/menu_tick_out.ogg"));

    private static float masterVolume = 0.8f;

    public static void playAudio(AudioFile audioFile, float volume) {
        float vol = 1.0f;
        Sound sound = null;
        switch (audioFile) {
            case MenuHover:
                sound = menuHover;
                vol = 0.3f;
                break;
            case MenuClick:
                sound = menuClick;
                break;
            case MenuRelease:
                sound = menuRelease;
                break;
            default:
                break;
        }
        if (sound != null)
            sound.play(vol * volume * masterVolume);
    }



}
