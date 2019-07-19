package com.compilation.game.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.compilation.game.ecs.components.Position;
import com.compilation.game.ecs.components.Velocity;

import static com.compilation.game.ecs.Mappers.positionMpr;
import static com.compilation.game.ecs.Mappers.velocityMpr;

/**
 * this system applies velocity to position
 */
public class VelocitySystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(Position.class, Velocity.class).get());
    }

    public void update(float deltaTime) {
        for (Entity entity : entities) {
            Position position = positionMpr.get(entity);
            Velocity velocity = velocityMpr.get(entity);

            position.x += velocity.x * deltaTime;
            position.y += velocity.y * deltaTime;
        }
    }
}
