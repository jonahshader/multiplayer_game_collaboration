package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class NetworkTransmitID implements Component, Pool.Poolable {
    public String username, password;
    public long localID;

    @Override
    public void reset() {
        username = null;
        password = null;
    }
}
