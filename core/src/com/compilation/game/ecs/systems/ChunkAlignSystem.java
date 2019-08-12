package com.compilation.game.ecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.compilation.game.ecs.components.Position;
import com.compilation.game.ecs.components.RegisteredChunk;
import com.compilation.game.ecs.components.UUID;
import com.compilation.game.ecs.components.Velocity;
import com.compilation.game.world.World;
import com.compilation.game.world.WorldChunk;

import static com.compilation.game.ecs.Mappers.*;

public class ChunkAlignSystem extends EntitySystem implements EntityListener {
    private ImmutableArray<Entity> dynamicEntities;
    private ImmutableArray<Entity> staticEntities;
    private World world;
    // when more systems exists that can manipulate position (e.g. teleportation system), add them here to be processed

    private Family dynamicFamily = Family.all(Position.class, Velocity.class, RegisteredChunk.class, UUID.class).get();
    private Family staticFamily = Family.all(Position.class).exclude(Velocity.class).get();

    public ChunkAlignSystem(World world) {
        this.world = world;
    }

    public void addedToEngine(Engine engine) {
        dynamicEntities = engine.getEntitiesFor(dynamicFamily);
        staticEntities = engine.getEntitiesFor(staticFamily);

        // add this system as a listener to listen for static family changes
        engine.addEntityListener(staticFamily, this);
    }

    public void update(float deltaTime) {
        for (Entity entity : dynamicEntities) {
            alignEntityToChunk(entity);
        }
    }

    // method called when static entity joins the static family
    @Override
    public void entityAdded(Entity entity) {
        alignEntityToChunk(entity);
    }

    // method called when static entity leaves the static family
    @Override
    public void entityRemoved(Entity entity) {

    }

    private void alignEntityToChunk(Entity entity) {
        Position position = positionMpr.get(entity);
        RegisteredChunk regChunk = registeredChunkMpr.get(entity);
        UUID uuid = uuidMpr.get(entity);

        // check if the current registerd chunk doesn't match the current actual chunk
        int chunkX = (int) Math.floor(position.x / WorldChunk.CHUNK_SIZE);
        int chunkY = (int) Math.floor(position.y / WorldChunk.CHUNK_SIZE);

        if (chunkX != regChunk.x || chunkY != regChunk.y) {
            // entity moved to a new chunk. unregister from previous chunk and add to new chunk
            //world.getChunk(regChunk.x, regChunk.y).getEntityDictionary().remove()
            //TODO: where i left off. huge mess to deal with. need to replicate this code for entity families with UUID and local ID.
            // although idk if local id will behave the way i originally thought it would. now NetworkTransmitID
        }


//        position.chunkX += Math.floor(position.x / WorldChunk.CHUNK_SIZE);
//        position.chunkY += Math.floor(position.y / WorldChunk.CHUNK_SIZE);
//        position.x -= Math.floor(position.x / WorldChunk.CHUNK_SIZE) * WorldChunk.CHUNK_SIZE;
//        position.y -= Math.floor(position.y / WorldChunk.CHUNK_SIZE) * WorldChunk.CHUNK_SIZE;
    }
}
