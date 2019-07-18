package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;

public class ExactPosition implements Component {
    public float x = 0.0f;
    public float y = 0.0f;

    public ExactPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
