package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class MaxSpeed implements Component, Pool.Poolable {
    public float speed = 0f;

    public MaxSpeed(float speed, float y) {
        this.speed = speed;
    }

    public MaxSpeed() {
    }

    @Override
    public void reset() {
        speed = 0f;
    }
}
