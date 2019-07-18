package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class PlayerControlled implements Component, Pool.Poolable {
    //flag component
    //TODO: if local multiplayer is a thing, the player controlling the entity would need to be stored here.
    @Override
    public void reset() {

    }
}
