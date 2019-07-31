package com.compilation.game.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.compilation.game.ecs.components.Position;
import com.compilation.game.ecs.components.Spectating;
import com.compilation.game.world.World;
import com.compilation.game.world.WorldChunk;

import static com.compilation.game.ecs.Mappers.positionMpr;
import static com.compilation.game.ecs.Mappers.spectatingMpr;

public class SpectatingSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private OrthographicCamera cam;
    private World world;

    public SpectatingSystem(OrthographicCamera cam, World world) {
        this.cam = cam;
        this.world = world;
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
            Spectating spectating = spectatingMpr.get(entity);
            cam.position.set((float)position.x, (float)position.y, 1f);
            cam.zoom = spectating.zoom;
            // notify world that this should be the center loaded chunk
            world.updateCenterChunk(position.x, position.y);
        }
    }

    public void setCam(OrthographicCamera cam) {
        this.cam = cam;
    }
}
