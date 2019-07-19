package com.compilation.game.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.compilation.game.ecs.components.MaxSpeed;
import com.compilation.game.ecs.components.Velocity;

import static com.compilation.game.ecs.Mappers.*;

/**
 * this system limits the velocity of entities with a Velocity and MaxSpeed component
 */
public class SpeedLimitSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(Velocity.class, MaxSpeed.class).get());
    }

    public void update(float deltaTime) {
        for (Entity entity : entities) {
            Velocity velocity = velocityMpr.get(entity);
            MaxSpeed maxSpeed = maxSpeedMpr.get(entity);

            var currentSpeed = Math.sqrt((velocity.x * velocity.x) + (velocity.y * velocity.y));
            if (currentSpeed > maxSpeed.speed) {
                velocity.x *= maxSpeed.speed / currentSpeed;
                velocity.y *= maxSpeed.speed / currentSpeed;
            }
        }
    }
}
