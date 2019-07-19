package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class MaxSpeed implements Component, Pool.Poolable {
    public float xVelocity = 0;
    public float yVelocity = 0;

    public MaxSpeed(float xVelocity, float yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public MaxSpeed() {
    }

    @Override
    public void reset() {
        xVelocity = 0f;
        yVelocity = 0f;
    }
}
