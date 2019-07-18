package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Pool;

public class Graphic implements Component, Pool.Poolable {
    public Sprite sprite;   // sprite has additional functionality that will come in handy

    public Graphic(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void reset() {
        sprite = null;
    }
}