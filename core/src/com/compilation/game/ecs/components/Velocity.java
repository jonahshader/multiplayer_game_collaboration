package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class Velocity implements Component, Pool.Poolable {
    public float x = 0f;
    public float y = 0f;

    public Velocity(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Velocity() {
    }

    @Override
    public void reset() {
        x = 0f;
        y = 0f;
    }
}
