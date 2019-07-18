package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;

public class CollisionBox implements Component, Pool.Poolable {
    public Rectangle bounds;

    public CollisionBox(Rectangle bounds) {
        this.bounds = bounds;
    }

    @Override
    public void reset() {
        bounds = null;
    }
}
