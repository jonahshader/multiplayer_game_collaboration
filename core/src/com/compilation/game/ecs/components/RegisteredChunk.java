package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class RegisteredChunk implements Component, Pool.Poolable {
    public int x, y;
    @Override
    public void reset() {
        x = 0;
        y = 0;
    }
}
