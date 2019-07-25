package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Pool;

public class PlayerControlled implements Component, Pool.Poolable {
    public float acceleration = 32.0f;

    public int moveUp = Input.Keys.W;
    public int moveDown = Input.Keys.S;
    public int moveLeft = Input.Keys.A;
    public int moveRight = Input.Keys.D;

    @Override
    public void reset() {
        acceleration = 32.0f;

        moveUp = Input.Keys.W;
        moveDown = Input.Keys.S;
        moveLeft = Input.Keys.A;
        moveRight = Input.Keys.D;
    }
}
