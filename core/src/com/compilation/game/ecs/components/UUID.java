package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class UUID implements Component, Pool.Poolable {
    public long uuid;

    @Override
    public void reset() {

    }
}
