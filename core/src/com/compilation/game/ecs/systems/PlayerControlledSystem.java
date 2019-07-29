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
import com.compilation.game.ecs.components.Spectating;

import static com.compilation.game.ecs.Mappers.*;

public class PlayerControlledSystem extends EntitySystem implements InputProcessor {
    private static final float invSqrt2 = (float) (1.0/Math.sqrt(2)); // cache value for frequent use
    private ImmutableArray<Entity> accelerationEntities; // entities with Acceleration (and PlayerControlled)
    private ImmutableArray<Entity> spectatingEntities;   // entities with Spectating (and PlayerControlled)


    public PlayerControlledSystem() {
        super();
        MainGame.multiplexer.addProcessor(this);
    }

    public void addedToEngine(Engine engine) {
        accelerationEntities = engine.getEntitiesFor(Family.all(PlayerControlled.class, Acceleration.class).get());
        spectatingEntities = engine.getEntitiesFor(Family.all(PlayerControlled.class, Spectating.class).get());
    }

    public void update(float deltaTime) {
        // nothing :) (all handled by keyDown and keyUp)
    }

    @Override
    public boolean keyDown(int keycode) {
        return handleKeyInput(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return handleKeyInput(keycode);
    }

    @Override
    public boolean keyTyped(char character) {
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

    private boolean handleKeyInput(int keycode) {
        boolean handled = false;

        for (Entity entity : accelerationEntities) {
            PlayerControlled playerControlled = playerControlledMpr.get(entity);
            Acceleration acceleration = accelerationMpr.get(entity);

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

        for (Entity entity : spectatingEntities) {
            PlayerControlled playerControlled = playerControlledMpr.get(entity);
            Spectating spectating = spectatingMpr.get(entity);

            if (Gdx.input.isKeyPressed(playerControlled.zoomIn)) {
                if (keycode == playerControlled.zoomIn) {
                    spectating.zoom /= 1.5;
                    handled = true;
                }
            }
            if (Gdx.input.isKeyPressed(playerControlled.zoomOut)) {
                if (keycode == playerControlled.zoomOut) {
                    spectating.zoom *= 1.5;
                    handled = true;
                }
            }
        }

        return handled;
    }
}
