package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Pool;

public class PlayerControlled implements Component, Pool.Poolable {
    public float acceleration = 2048f;

    public int moveUp = Input.Keys.W;
    public int moveDown = Input.Keys.S;
    public int moveLeft = Input.Keys.A;
    public int moveRight = Input.Keys.D;

    public int zoomIn = Input.Keys.EQUALS;
    public int zoomOut = Input.Keys.MINUS;

    @Override
    public void reset() {
        acceleration = 2048f;

        moveUp = Input.Keys.W;
        moveDown = Input.Keys.S;
        moveLeft = Input.Keys.A;
        moveRight = Input.Keys.D;

        zoomIn = Input.Keys.PLUS;
        zoomOut = Input.Keys.MINUS;
    }
}
