package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class Position implements Component, Pool.Poolable {
    public float x = 0;
    public float y = 0;
    public int chunkX = 0;
    public int chunkY = 0;

    public Position(float x, float y, int chunkX, int chunkY) {
        this.x = x;
        this.y = y;
        this.chunkX = chunkX;
        this.chunkY = chunkY;
    }

    public Position() {
    }

    @Override
    public void reset() {
        x = 0;
        y = 0;
        chunkX = 0;
        chunkY = 0;
    }

}
