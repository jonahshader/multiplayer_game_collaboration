package com.compilation.game.ecs;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.compilation.game.ecs.systems.*;
import com.compilation.game.world.World;

public class ECSEngine extends PooledEngine {

    public ECSEngine(OrthographicCamera cam, World world) {
        super();
        AccelerationSystem accelerationSystem = new AccelerationSystem();
        PlayerControlledSystem playerControlledSystem = new PlayerControlledSystem();
        RenderSystem renderSystem = new RenderSystem(cam);
        SpectatingSystem spectatingSystem = new SpectatingSystem(cam, world);
        SpeedLimitSystem speedLimitSystem = new SpeedLimitSystem();
        VelocitySystem velocitySystem = new VelocitySystem();

        // set priorities
        int priority = 0;
        playerControlledSystem.priority = priority++; // highest priority
        accelerationSystem.priority = priority++;
        speedLimitSystem.priority = priority++;
        velocitySystem.priority = priority++;
        spectatingSystem.priority = priority++;
        renderSystem.priority = priority++; // lowest priority


        // add systems to engine
        addSystem(playerControlledSystem);
        addSystem(accelerationSystem);
        addSystem(speedLimitSystem);
        addSystem(velocitySystem);
        addSystem(spectatingSystem);
        addSystem(renderSystem);
    }
}
