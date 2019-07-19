package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class Acceleration implements Component, Pool.Poolable {
    public float xVelocity = 0f;
    public float yVelocity = 0f;

    public Acceleration(float xVelocity, float yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public Acceleration() {
    }

    @Override
    public void reset() {
        xVelocity = 0f;
        yVelocity = 0f;
    }
}
