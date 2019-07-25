package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class MaxSpeed implements Component, Pool.Poolable {
    public float speed = 1024f;

    public MaxSpeed(float speed) {
        this.speed = speed;
    }

    public MaxSpeed() {
    }

    @Override
    public void reset() {
        speed = 1024f;
    }
}
