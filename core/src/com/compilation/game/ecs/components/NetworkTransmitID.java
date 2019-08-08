package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class NetworkTransmitID implements Component, Pool.Poolable {
    public int localID;

    public NetworkTransmitID() {
    }

    @Override
    public void reset() {
        localID = 0;
    }
}
