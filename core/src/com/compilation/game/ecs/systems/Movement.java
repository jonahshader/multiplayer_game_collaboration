package com.compilation.game.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.utils.ImmutableArray;

public class Movement extends EntitySystem {
    private ImmutableArray<Entity> entities;

    public void addedToEngine(Engine engine) {
//        entities = engine.getEntitiesFor(Family.all(ChunkPosition))
    }

}
