package com.compilation.game.ecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.compilation.game.ecs.ECSEngine;
import com.compilation.game.ecs.components.NetworkTransmitID;
import com.compilation.game.ecs.components.UUID;

import java.util.HashMap;

import static com.compilation.game.ecs.Mappers.networkTransmitIDMpr;

public class NetworkTransmitIDSystem extends EntitySystem implements EntityListener {
    private ImmutableArray<Entity> entities;
    private Family networkTransmitIDFamily = Family.all(NetworkTransmitID.class).exclude(UUID.class).get();
    private HashMap<Integer, Entity> localEntities;

    public NetworkTransmitIDSystem(HashMap<Integer, Entity> localEntities) {
        this.localEntities = localEntities;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(networkTransmitIDFamily);

        // add this system as a listener to listen for static family changes
        engine.addEntityListener(networkTransmitIDFamily, this);
    }

    @Override
    public void entityAdded(Entity entity) {
        NetworkTransmitID id = networkTransmitIDMpr.get(entity);
        while (localEntities.containsKey(id.localID)) { // keep looking for an unused key
            id.localID = ECSEngine.random.nextInt();
        }
        // add it to the localEntities collection
        localEntities.put(id.localID, entity);
    }

    @Override
    public void entityRemoved(Entity entity) {
        NetworkTransmitID id = networkTransmitIDMpr.get(entity);
        localEntities.remove(id.localID);
    }

    @Override
    public void update(float deltaTime) {
        // none
    }
}
