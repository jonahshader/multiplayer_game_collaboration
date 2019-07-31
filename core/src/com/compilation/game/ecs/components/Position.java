package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class Position implements Component, Pool.Poolable {
    public double x = 0;
    public double y = 0;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
    }

    @Override
    public void reset() {
        x = 0;
        y = 0;
    }

}
