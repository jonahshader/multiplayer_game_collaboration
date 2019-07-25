package com.compilation.game.ecs;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.compilation.game.ecs.systems.*;

public class ECSEngine extends PooledEngine {
    private AccelerationSystem accelerationSystem;
    private PlayerControlledSystem playerControlledSystem;
    private RenderSystem renderSystem;
    private SpectatingSystem spectatingSystem;
    private SpeedLimitSystem speedLimitSystem;
    private VelocitySystem velocitySystem;

    public ECSEngine(OrthographicCamera cam) {
        super();
        accelerationSystem = new AccelerationSystem();
        playerControlledSystem = new PlayerControlledSystem();
        renderSystem = new RenderSystem(cam);
        spectatingSystem = new SpectatingSystem(cam);
        speedLimitSystem = new SpeedLimitSystem();
        velocitySystem = new VelocitySystem();

        // set priorities
        playerControlledSystem.priority = 0; // highest priority
        accelerationSystem.priority = 1;
        speedLimitSystem.priority = 2;
        velocitySystem.priority = 3;
        spectatingSystem.priority = 4;
        renderSystem.priority = 5;

        // add systems to engine
        addSystem(playerControlledSystem);
        addSystem(accelerationSystem);
        addSystem(speedLimitSystem);
        addSystem(velocitySystem);
        addSystem(spectatingSystem);
        addSystem(renderSystem);
    }
}
