package com.compilation.game.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.compilation.game.ecs.components.NetworkTransmitID;
import com.compilation.game.ecs.systems.*;
import com.compilation.game.world.World;

import java.util.HashMap;
import java.util.Random;
import java.util.SplittableRandom;

public class ECSEngine extends PooledEngine {
    /*
    TODO: writing some notes here. will remove later.
    make two ECSEngine instances, one that is for client generated entities that are trying to transfer ownership
    to the server. the other instance receives entities from the server, where evey entity should end up.

    for the client to create an entity, it first creates it in the client side ECSEngine with a component
    that identifies this user/computer (probably by account or IP). a system then sends this new entity to the
    server. when the server receives this entity, it adds a UUID component to it, removes the id component,
    and returns it to the client. the client then adds it to its server ECSEngine.

    as for the ID component, identifying the player via ip may be better than via login, because login can
    be variable whereas ip is roughly the same length. if we did it via username, a user with a long username
    might be put at a disadvantage.

    however, this could be a security issue. username and password might be needed for all communications so that
    the server knows it is giving out information to the correct player and isn't giving a hacker information it
    shouldn't have.
     */

    public final static Random random = new Random();

    private HashMap<Long, Entity> uuidToEntityMap;
    private HashMap<Integer, Entity> localIDToEntityMap;

    public ECSEngine(OrthographicCamera cam, World world) {
        super();
        uuidToEntityMap = new HashMap<>();
        localIDToEntityMap = new HashMap<>();
        AccelerationSystem accelerationSystem = new AccelerationSystem();
        NetworkTransmitIDSystem nidSystem = new NetworkTransmitIDSystem(localIDToEntityMap);
        PlayerControlledSystem playerControlledSystem = new PlayerControlledSystem();
        RenderSystem renderSystem = new RenderSystem(cam);
        SpectatingSystem spectatingSystem = new SpectatingSystem(cam, world);
        SpeedLimitSystem speedLimitSystem = new SpeedLimitSystem();
        VelocitySystem velocitySystem = new VelocitySystem();

        // set priorities
        int priority = 0;
        // highest priority
        playerControlledSystem.priority = priority++;
        accelerationSystem.priority = priority++;
        speedLimitSystem.priority = priority++;
        velocitySystem.priority = priority++;
        spectatingSystem.priority = priority++;
        renderSystem.priority = priority++;
        nidSystem.priority = priority++; // not sure if priority matters on this one
        // lowest priority (higher number = happens later, lower priority)

        // add systems to engine
        addSystem(playerControlledSystem);
        addSystem(accelerationSystem);
        addSystem(speedLimitSystem);
        addSystem(velocitySystem);
        addSystem(spectatingSystem);
        addSystem(renderSystem);
        addSystem(nidSystem);
    }

//    public void addEntityQueuedForServer(Entity entity) {
//        NetworkTransmitID id = createComponent(NetworkTransmitID.class);
//        id.localID = generateIntUUID(localIDToEntityMap);
//        entity.add(id);
//    }

    public static int generateIntUUID(HashMap<Integer, Entity> container) {
        int id = 0;
        while (container.containsKey(id)) {
            id = random.nextInt();
        }
        return id;
    }

    public static long generateLongUUID(HashMap<Long, Entity> container) {
        long id = 0;
        while (container.containsKey(id)) {
            id = random.nextLong();
        }
        return id;
    }
}
