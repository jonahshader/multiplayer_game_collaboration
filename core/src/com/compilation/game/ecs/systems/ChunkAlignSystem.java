package com.compilation.game.ecs.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.compilation.game.ecs.components.Position;
import com.compilation.game.ecs.components.Velocity;

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

    // method called when static entity joins the static family
    @Override
    public void entityAdded(Entity entity) {

    }

    // method called when static entity leaves the static family
    @Override
    public void entityRemoved(Entity entity) {

    }
}

//TODO: figure out entity chunk relationship.... will be complicated