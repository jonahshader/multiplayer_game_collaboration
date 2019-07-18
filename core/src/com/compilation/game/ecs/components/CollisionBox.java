package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

public class CollisionBox implements Component {
    public Rectangle bounds;

    public CollisionBox(Rectangle bounds) {
        this.bounds = bounds;
    }
}
