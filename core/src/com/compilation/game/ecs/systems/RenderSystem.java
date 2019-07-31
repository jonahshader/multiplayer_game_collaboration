package com.compilation.game.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.compilation.game.MainGame;
import com.compilation.game.ecs.components.Graphic;
import com.compilation.game.ecs.components.Position;
import com.compilation.game.world.WorldChunk;

import static com.compilation.game.ecs.Mappers.graphicMpr;
import static com.compilation.game.ecs.Mappers.positionMpr;

public class RenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private OrthographicCamera cam;

    public RenderSystem(OrthographicCamera cam) {
        this.cam = cam;
    }

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(Position.class, Graphic.class).get());
    }

    public void update(float deltaTime) {
        MainGame.batch.begin();
        cam.update();
        MainGame.batch.setProjectionMatrix(cam.combined);
        for (Entity entity : entities) {
            Position position = positionMpr.get(entity);
            Graphic graphic = graphicMpr.get(entity);

            graphic.sprite.setOriginBasedPosition(
                    position.x + (position.chunkX * WorldChunk.CHUNK_SIZE),
                    position.y + (position.chunkY * WorldChunk.CHUNK_SIZE));
            graphic.sprite.draw(MainGame.batch);
        }
        MainGame.batch.end();
    }
}
