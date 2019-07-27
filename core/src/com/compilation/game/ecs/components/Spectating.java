package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class Spectating implements Component, Pool.Poolable {
    public float zoom = 1f;
    // flag component
    //TODO: if local multiplayer is a thing, the player spectating the entity would need to be stored here (for split screen).
    @Override
    public void reset() {
        zoom = 1;
    }
}
