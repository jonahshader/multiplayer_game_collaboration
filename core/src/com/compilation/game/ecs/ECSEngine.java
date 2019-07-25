package com.compilation.game.ecs;

import com.badlogic.ashley.core.PooledEngine;
import com.compilation.game.ecs.systems.AccelerationSystem;
import com.compilation.game.ecs.systems.VelocitySystem;

public class ECSEngine extends PooledEngine {
    private AccelerationSystem accelerationSystem;

    public ECSEngine() {
    }
}
