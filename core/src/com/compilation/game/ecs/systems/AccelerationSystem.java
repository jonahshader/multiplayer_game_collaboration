package com.compilation.game.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.compilation.game.ecs.components.Acceleration;
import com.compilation.game.ecs.components.Velocity;

import static com.compilation.game.ecs.Mappers.accelerationMpr;
import static com.compilation.game.ecs.Mappers.velocityMpr;

/**
 * this system applies acceleration to position
 */
public class AccelerationSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(Velocity.class, Acceleration.class).get());
    }

    public void update(float deltaTime) {
        for (Entity entity : entities) {
            Velocity velocity = velocityMpr.get(entity);
            Acceleration acceleration = accelerationMpr.get(entity);

            velocity.x += acceleration.x * deltaTime;
            velocity.y += acceleration.y * deltaTime;
        }
    }
}
