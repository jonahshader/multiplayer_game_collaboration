package com.compilation.game.ecs.components;

import com.badlogic.ashley.core.Component;

public class GridPosition implements Component {
    public int x = 0;
    public int y = 0;

    public GridPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
