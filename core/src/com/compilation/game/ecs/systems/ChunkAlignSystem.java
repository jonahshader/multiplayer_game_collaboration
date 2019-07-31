package com.compilation.game.ecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.compilation.game.ecs.components.Position;
import com.compilation.game.ecs.components.Velocity;
import com.compilation.game.world.WorldChunk;

import static com.compilation.game.ecs.Mappers.positionMpr;

public class ChunkAlignSystem extends EntitySystem implements EntityListener {
    private ImmutableArray<Entity> dynamicEntities;
    private ImmutableArray<Entity> staticEntities;

    private Family dynamicFamily = Family.all(Position.class, Velocity.class).get();
    private Family staticFamily = Family.all(Position.class).exclude(Velocity.class).get();

    public void addedToEngine(Engine engine) {
        dynamicEntities = engine.getEntitiesFor(dynamicFamily);
        staticEntities = engine.getEntitiesFor(staticFamily);

        // add this system as a listener to listen for static family changes
        engine.addEntityListener(staticFamily, this);
    }

    public void update(float deltaTime) {
        for (Entity entity : dynamicEntities) {
            alignPositionToChunk(positionMpr.get(entity));
        }
    }

    // method called when static entity joins the static family
    @Override
    public void entityAdded(Entity entity) {
        alignPositionToChunk(positionMpr.get(entity));
    }

    // method called when static entity leaves the static family
    @Override
    public void entityRemoved(Entity entity) {

    }

    private void alignPositionToChunk(Position position) {
//        position.chunkX += Math.floor(position.x / WorldChunk.CHUNK_SIZE);
//        position.chunkY += Math.floor(position.y / WorldChunk.CHUNK_SIZE);
//        position.x -= Math.floor(position.x / WorldChunk.CHUNK_SIZE) * WorldChunk.CHUNK_SIZE;
//        position.y -= Math.floor(position.y / WorldChunk.CHUNK_SIZE) * WorldChunk.CHUNK_SIZE;
    }
}
