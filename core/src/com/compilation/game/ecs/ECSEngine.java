package com.compilation.game.ecs;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.compilation.game.ecs.systems.*;
import com.compilation.game.world.World;

public class ECSEngine extends PooledEngine {
    private AccelerationSystem accelerationSystem;
    private ChunkAlignSystem chunkAlignSystem;
    private PlayerControlledSystem playerControlledSystem;
    private RenderSystem renderSystem;
    private SpectatingSystem spectatingSystem;
    private SpeedLimitSystem speedLimitSystem;
    private VelocitySystem velocitySystem;

    public ECSEngine(OrthographicCamera cam, World world) {
        super();
        accelerationSystem = new AccelerationSystem();
        chunkAlignSystem = new ChunkAlignSystem();
        playerControlledSystem = new PlayerControlledSystem();
        renderSystem = new RenderSystem(cam);
        spectatingSystem = new SpectatingSystem(cam, world);
        speedLimitSystem = new SpeedLimitSystem();
        velocitySystem = new VelocitySystem();

        // set priorities
        playerControlledSystem.priority = 0; // highest priority
        accelerationSystem.priority = 1;
        speedLimitSystem.priority = 2;
        velocitySystem.priority = 3;
        chunkAlignSystem.priority = 4;
        spectatingSystem.priority = 5;
        renderSystem.priority = 6;

        // add systems to engine
        addSystem(playerControlledSystem);
        addSystem(accelerationSystem);
        addSystem(speedLimitSystem);
        addSystem(velocitySystem);
        addSystem(chunkAlignSystem);
        addSystem(spectatingSystem);
        addSystem(renderSystem);
    }
}
