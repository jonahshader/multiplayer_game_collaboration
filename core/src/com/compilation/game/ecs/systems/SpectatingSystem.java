package com.compilation.game.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.compilation.game.ecs.components.Position;
import com.compilation.game.ecs.components.Spectating;

import static com.compilation.game.ecs.Mappers.positionMpr;

public class SpectatingSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private OrthographicCamera cam;

    public SpectatingSystem(OrthographicCamera cam) {
        this.cam = cam;
    }

    public void addedToEngine(Engine engine) {
        // get all entities that have both Spectating and Position components
        entities = engine.getEntitiesFor(Family.all(Spectating.class, Position.class).get());
        //TODO: when there are UI components/systems in the future, filter out those here
    }

    public void update(float deltaTime) {
        if (entities.size() > 1) {
            System.out.println("WARNING: Multiple entities have a spectating component!");
        } else if (entities.size() == 1) {
            Entity entity = entities.get(0);
            Position position = positionMpr.get(entity);
            cam.position.set(position.x, position.y, 1f);
        }
    }

    public void setCam(OrthographicCamera cam) {
        this.cam = cam;
    }
}
