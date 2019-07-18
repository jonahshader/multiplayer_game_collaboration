package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;

public class ChunkPosition implements Component {
    public int x = 0;
    public int y = 0;

    public ChunkPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
