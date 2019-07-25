package com.compilation.game.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.compilation.game.MainGame;
import com.compilation.game.ecs.components.Acceleration;
import com.compilation.game.ecs.components.PlayerControlled;

import static com.compilation.game.ecs.Mappers.accelerationMpr;
import static com.compilation.game.ecs.Mappers.playerControlledMpr;

public class PlayerControlledSystem extends EntitySystem implements InputProcessor {
    public static final float invSqrt2 = (float) (1.0/Math.sqrt(2));
    private ImmutableArray<Entity> entities;

    public PlayerControlledSystem() {
        super();
        MainGame.multiplexer.addProcessor(this);
    }

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
        boolean handled = false;
        for (Entity entity : entities) {
            Acceleration acceleration = accelerationMpr.get(entity);
            PlayerControlled playerControlled = playerControlledMpr.get(entity);

            int leftRight = 0;
            int upDown = 0;

            if (Gdx.input.isKeyPressed(playerControlled.moveLeft)) {
                if (keycode == playerControlled.moveLeft)
                    handled = true;
                leftRight--;
            }
            if (Gdx.input.isKeyPressed(playerControlled.moveRight)) {
                if (keycode == playerControlled.moveRight)
                    handled = true;
                leftRight++;
            }
            if (Gdx.input.isKeyPressed(playerControlled.moveUp)) {
                if (keycode == playerControlled.moveUp)
                    handled = true;
                upDown++;
            }
            if (Gdx.input.isKeyPressed(playerControlled.moveDown)) {
                if (keycode == playerControlled.moveDown)
                    handled = true;
                upDown--;
            }

            if (leftRight != 0 && upDown != 0) {
                acceleration.x = leftRight * playerControlled.acceleration * invSqrt2;
                acceleration.y = upDown * playerControlled.acceleration * invSqrt2;
            } else {
                acceleration.x = leftRight * playerControlled.acceleration;
                acceleration.y = upDown * playerControlled.acceleration;
            }
        }
        return handled;
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
