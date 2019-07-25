package com.compilation.game.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.InputProcessor;
import com.compilation.game.ecs.components.Acceleration;
import com.compilation.game.ecs.components.PlayerControlled;

import static com.compilation.game.ecs.Mappers.accelerationMpr;

public class PlayerControlledSystem extends EntitySystem implements InputProcessor {
    private ImmutableArray<Entity> entities;

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PlayerControlled.class, Acceleration.class).get());
    }

    public void update(float deltaTime) {
        for (Entity entity : entities) {
            Acceleration acceleration = accelerationMpr.get(entity);
            //TODO: figure out how to get multiplexer involved
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (entities.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (entities.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if (entities.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
